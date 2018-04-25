package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.PR;
import com.sekolahguru.folcotandiono.sekolahguru.model.PRTambahResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetail;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetailResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PRTambahActivity extends AppCompatActivity {

    private static final int GALLERY_GAMBAR = 0;
    private static final int CAMERA_GAMBAR = 1;

    public static String PR_TAMBAH = "pr_tambah";
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
        setContentView(R.layout.activity_pr_tambah);

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
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Soal Ujian Detail");
    }

    private void initListener() {
        pilihMataPelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PRTambahMataPelajaranActivity.class);
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
                    Toast.makeText(PRTambahActivity.this, "Mata pelajaran kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nama.getText().toString().isEmpty()) {
                    Toast.makeText(PRTambahActivity.this, "Nama kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (deskripsi.getText().toString().isEmpty()) {
                    Toast.makeText(PRTambahActivity.this, "Deskripsi kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                PR pr = new PR();
                pr.setIdMataPelajaran(idMataPelajaran.getText().toString());
                pr.setNama(nama.getText().toString());
                pr.setDeskripsi(deskripsi.getText().toString());

                List<String> listGambar = new ArrayList<>();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ((BitmapDrawable) gambar.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                listGambar.add(encodedImage);
                pr.setGambar(listGambar.toString());

                Call<PRTambahResponse> call = apiInterface.tambahPR(pr);
                call.enqueue(new Callback<PRTambahResponse>() {
                    @Override
                    public void onResponse(Call<PRTambahResponse> call, Response<PRTambahResponse> response) {
                        Toast.makeText(PRTambahActivity.this, "PR sudah ditambah", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PRTambahResponse> call, Throwable t) {

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
                                takeSoalGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void chooseGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_GAMBAR);
    }

    private void takeSoalGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_GAMBAR);
    }

    @Override
    protected void onResume() {
        sharedPreferences = getSharedPreferences(PR_TAMBAH, 0);
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
