package com.sekolahguru.folcotandiono.sekolahguru;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.MateriPelajaran;
import com.sekolahguru.folcotandiono.sekolahguru.model.MateriPelajaranTambahResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.PR;
import com.sekolahguru.folcotandiono.sekolahguru.model.PRTambahResponse;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateriPelajaranTambahActivity extends AppCompatActivity {

    private static final int GALLERY_GAMBAR = 0;
    private static final int CAMERA_GAMBAR = 1;
    private static final int CAMERA_PERMISSION_GAMBAR = 2;

    public static String MATERI_PELAJARAN_TAMBAH = "materi_pelajaran_tambah";
    public static String ID_MATA_PELAJARAN = "id_mata_pelajaran";
    public static String NAMA_MATA_PELAJARAN = "nama_mata_pelajaran";

    private Toolbar toolbar;
    private EditText nama;
    private EditText deskripsi;
    private Button gambarPilih;
    private ImageView gambar;
    private EditText idMataPelajaran;
    private EditText namaMataPelajaran;
    private Button pilihMataPelajaran;

    private Button tambah;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_pelajaran_tambah);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        idMataPelajaran = findViewById(R.id.id_mata_pelajaran);
        namaMataPelajaran = findViewById(R.id.nama_mata_pelajaran);
        pilihMataPelajaran = findViewById(R.id.pilih_mata_pelajaran);
        nama = findViewById(R.id.nama);
        deskripsi = findViewById(R.id.deskripsi);
        gambarPilih = findViewById(R.id.gambar_pilih);
        gambar = findViewById(R.id.gambar);

        tambah = findViewById(R.id.tambah);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Materi Pelajaran");
    }

    private void initListener() {
        pilihMataPelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MateriPelajaranTambahMataPelajaranActivity.class);
                startActivity(intent);
            }
        });
        gambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGambarPictureDialog();
            }
        });
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idMataPelajaran.getText().toString().isEmpty()) {
                    Toast.makeText(MateriPelajaranTambahActivity.this, "Mata pelajaran kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nama.getText().toString().isEmpty()) {
                    Toast.makeText(MateriPelajaranTambahActivity.this, "Nama kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (deskripsi.getText().toString().isEmpty()) {
                    Toast.makeText(MateriPelajaranTambahActivity.this, "Deskripsi kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                MateriPelajaran materiPelajaran = new MateriPelajaran();
                materiPelajaran.setIdMataPelajaran(idMataPelajaran.getText().toString());
                materiPelajaran.setNama(nama.getText().toString());
                materiPelajaran.setDeskripsi(deskripsi.getText().toString());

                List<String> listGambar = new ArrayList<>();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                if (gambar.getDrawable() != null) {
                    ((BitmapDrawable) gambar.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    listGambar.add(encodedImage);
                }
                else listGambar.add("");
                materiPelajaran.setGambar(listGambar);

                Call<MateriPelajaranTambahResponse> call = apiInterface.tambahMateriPelajaran(materiPelajaran);
                call.enqueue(new Callback<MateriPelajaranTambahResponse>() {
                    @Override
                    public void onResponse(Call<MateriPelajaranTambahResponse> call, Response<MateriPelajaranTambahResponse> response) {
                        Toast.makeText(MateriPelajaranTambahActivity.this, "Materi pelajaran sudah ditambah", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<MateriPelajaranTambahResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    private void showGambarPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
//        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Gallery",
                "Camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chooseGambarFromGallery();
                                break;
                            case 1:
                                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                                    Manifest.permission.CAMERA)
                                    != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(MateriPelajaranTambahActivity.this,
                                        new String[]{Manifest.permission.CAMERA},
                                        CAMERA_PERMISSION_GAMBAR);

                                } else {
                                    takeSoalGambarFromCamera();
                                }
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void chooseGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_GAMBAR);
    }

    private void takeSoalGambarFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_GAMBAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
//                    gambar.setImageBitmap(bitmap);
                    Picasso.get().load(contentURI).resize(1000, 1000).into(gambar);
                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_GAMBAR) {
//            Uri contentUri = data.getData();
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, stream);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1000, 1000);
            gambar.setLayoutParams(layoutParams);
            gambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == CAMERA_PERMISSION_GAMBAR) {
            takeSoalGambarFromCamera();
        }
    }

    @Override
    protected void onResume() {
        sharedPreferences = getSharedPreferences(MATERI_PELAJARAN_TAMBAH, 0);
        if (sharedPreferences.getString(ID_MATA_PELAJARAN, null) != null) {
            idMataPelajaran.setText(sharedPreferences.getString(ID_MATA_PELAJARAN, null));
        }
        if (sharedPreferences.getString(NAMA_MATA_PELAJARAN, null) != null) {
            namaMataPelajaran.setText(sharedPreferences.getString(NAMA_MATA_PELAJARAN, null));
        }
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
