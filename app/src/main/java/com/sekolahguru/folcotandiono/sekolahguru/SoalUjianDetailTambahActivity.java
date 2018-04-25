package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetail;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetailResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetailTambahResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoalUjianDetailTambahActivity extends AppCompatActivity {

    private static final int GALLERY_SOAL_GAMBAR = 0;
    private static final int CAMERA_SOAL_GAMBAR = 1;
    private static final int GALLERY_PILIHAN_JAWABAN_A_GAMBAR = 2;
    private static final int CAMERA_PILIHAN_JAWABAN_A_GAMBAR = 3;
    private static final int GALLERY_PILIHAN_JAWABAN_B_GAMBAR = 4;
    private static final int CAMERA_PILIHAN_JAWABAN_B_GAMBAR = 5;
    private static final int GALLERY_PILIHAN_JAWABAN_C_GAMBAR = 6;
    private static final int CAMERA_PILIHAN_JAWABAN_C_GAMBAR = 7;
    private static final int GALLERY_PILIHAN_JAWABAN_D_GAMBAR = 8;
    private static final int CAMERA_PILIHAN_JAWABAN_D_GAMBAR = 9;
    private static final int GALLERY_PILIHAN_JAWABAN_E_GAMBAR = 10;
    private static final int CAMERA_PILIHAN_JAWABAN_E_GAMBAR = 11;

    public static String SOAL_UJIAN_DETAIL_TAMBAH = "soal_ujian_detail_tambah";
    public static String ID_SOAL_UJIAN = "id_soal_ujian";
    public static String NAMA_SOAL_UJIAN = "nama_soal_ujian";
    public static String ID_JENIS_SOAL_UJIAN_DETAIL = "id_jenis_soal_ujian_detail";
    public static String NAMA_JENIS_SOAL_UJIAN_DETAIL = "nama_jenis_soal_ujian_detail";

    private Toolbar toolbar;
    private EditText idSoalUjian;
    private EditText namaSoalUjian;
    private Button pilihSoalUjian;
    private EditText idJenisSoalUjianDetail;
    private EditText namaJenisSoalUjianDetail;
    private Button pilihJenisSoalUjianDetail;

    private EditText soalTulisan;
    private Button soalGambarPilih;
    private ImageView soalGambar;

    private EditText pilihanJawabanATulisan;
    private Button pilihanJawabanAGambarPilih;
    private ImageView pilihanJawabanAGambar;

    private EditText pilihanJawabanBTulisan;
    private Button pilihanJawabanBGambarPilih;
    private ImageView pilihanJawabanBGambar;

    private EditText pilihanJawabanCTulisan;
    private Button pilihanJawabanCGambarPilih;
    private ImageView pilihanJawabanCGambar;

    private EditText pilihanJawabanDTulisan;
    private Button pilihanJawabanDGambarPilih;
    private ImageView pilihanJawabanDGambar;

    private EditText pilihanJawabanETulisan;
    private Button pilihanJawabanEGambarPilih;
    private ImageView pilihanJawabanEGambar;

    private EditText kunciJawaban;

    private Button tambah;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian_detail_tambah);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        idSoalUjian = findViewById(R.id.id_soal_ujian);
        namaSoalUjian = findViewById(R.id.nama_soal_ujian);
        pilihSoalUjian = findViewById(R.id.pilih_soal_ujian);
        idJenisSoalUjianDetail = findViewById(R.id.id_jenis_soal_ujian_detail);
        namaJenisSoalUjianDetail = findViewById(R.id.nama_jenis_soal_ujian_detail);
        pilihJenisSoalUjianDetail = findViewById(R.id.pilih_jenis_soal_ujian_detail);

        soalTulisan = findViewById(R.id.soal_tulisan);
        soalGambarPilih = findViewById(R.id.soal_gambar_pilih);
        soalGambar = findViewById(R.id.soal_gambar);

        pilihanJawabanATulisan = findViewById(R.id.pilihan_jawaban_a_tulisan);
        pilihanJawabanAGambarPilih = findViewById(R.id.pilihan_jawaban_a_gambar_pilih);
        pilihanJawabanAGambar = findViewById(R.id.pilihan_jawaban_a_gambar);

        pilihanJawabanBTulisan = findViewById(R.id.pilihan_jawaban_b_tulisan);
        pilihanJawabanBGambarPilih = findViewById(R.id.pilihan_jawaban_b_gambar_pilih);
        pilihanJawabanBGambar = findViewById(R.id.pilihan_jawaban_b_gambar);

        pilihanJawabanCTulisan = findViewById(R.id.pilihan_jawaban_c_tulisan);
        pilihanJawabanCGambarPilih = findViewById(R.id.pilihan_jawaban_c_gambar_pilih);
        pilihanJawabanCGambar = findViewById(R.id.pilihan_jawaban_c_gambar);

        pilihanJawabanDTulisan = findViewById(R.id.pilihan_jawaban_d_tulisan);
        pilihanJawabanDGambarPilih = findViewById(R.id.pilihan_jawaban_d_gambar_pilih);
        pilihanJawabanDGambar = findViewById(R.id.pilihan_jawaban_d_gambar);

        pilihanJawabanETulisan = findViewById(R.id.pilihan_jawaban_e_tulisan);
        pilihanJawabanEGambarPilih = findViewById(R.id.pilihan_jawaban_e_gambar_pilih);
        pilihanJawabanEGambar = findViewById(R.id.pilihan_jawaban_e_gambar);

        kunciJawaban = findViewById(R.id.kunci_jawaban);

        tambah = findViewById(R.id.tambah);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Soal Ujian Detail");
    }

    private void initListener() {
        pilihSoalUjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SoalUjianDetailTambahSoalUjianActivity.class);
                startActivity(intent);
            }
        });
        pilihJenisSoalUjianDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SoalUjianDetailTambahJenisSoalUjianDetailActivity.class);
                startActivity(intent);
            }
        });
        soalGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSoalGambarPictureDialog();
            }
        });
        pilihanJawabanAGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanAGambarPictureDialog();
            }
        });
        pilihanJawabanBGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanBGambarPictureDialog();
            }
        });
        pilihanJawabanCGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanCGambarPictureDialog();
            }
        });
        pilihanJawabanDGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanDGambarPictureDialog();
            }
        });
        pilihanJawabanEGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanEGambarPictureDialog();
            }
        });
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idSoalUjian.getText().toString().isEmpty()) {
                    Toast.makeText(SoalUjianDetailTambahActivity.this, "Soal ujian kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (idJenisSoalUjianDetail.getText().toString().isEmpty()) {
                    Toast.makeText(SoalUjianDetailTambahActivity.this, "Jenis soal ujian detail kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (soalTulisan.getText().toString().isEmpty()) {
                    Toast.makeText(SoalUjianDetailTambahActivity.this, "Soal kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                SoalUjianDetail soalUjianDetail = new SoalUjianDetail();
                soalUjianDetail.setIdSoalUjian(idSoalUjian.getText().toString());
                soalUjianDetail.setIdJenisSoalUjianDetail(idJenisSoalUjianDetail.getText().toString());
                soalUjianDetail.setSoalTulisan(soalTulisan.getText().toString());

                List<String> listSoalGambar = new ArrayList<>();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ((BitmapDrawable) soalGambar.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                listSoalGambar.add(encodedImage);
                soalUjianDetail.setSoalGambar(listSoalGambar.toString());

                List<String> listPilihanJawabanTulisan = new ArrayList<>();
                listPilihanJawabanTulisan.add(pilihanJawabanATulisan.getText().toString());
                listPilihanJawabanTulisan.add(pilihanJawabanBTulisan.getText().toString());
                listPilihanJawabanTulisan.add(pilihanJawabanCTulisan.getText().toString());
                listPilihanJawabanTulisan.add(pilihanJawabanDTulisan.getText().toString());
                listPilihanJawabanTulisan.add(pilihanJawabanETulisan.getText().toString());
                soalUjianDetail.setPilihanJawabanTulisan(listPilihanJawabanTulisan.toString());

                List<List<String>> listPilihanJawabanGambar = new ArrayList<>();

                List<String> listPilihanJawabanAGambar = new ArrayList<>();
                baos = new ByteArrayOutputStream();
                ((BitmapDrawable) pilihanJawabanAGambar.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                listPilihanJawabanAGambar.add(encodedImage);

                listPilihanJawabanGambar.add(listPilihanJawabanAGambar);

                List<String> listPilihanJawabanBGambar = new ArrayList<>();
                baos = new ByteArrayOutputStream();
                ((BitmapDrawable) pilihanJawabanBGambar.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                listPilihanJawabanBGambar.add(encodedImage);

                listPilihanJawabanGambar.add(listPilihanJawabanBGambar);

                List<String> listPilihanJawabanCGambar = new ArrayList<>();
                baos = new ByteArrayOutputStream();
                ((BitmapDrawable) pilihanJawabanCGambar.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                listPilihanJawabanCGambar.add(encodedImage);

                listPilihanJawabanGambar.add(listPilihanJawabanCGambar);

                List<String> listPilihanJawabanDGambar = new ArrayList<>();
                baos = new ByteArrayOutputStream();
                ((BitmapDrawable) pilihanJawabanDGambar.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                listPilihanJawabanDGambar.add(encodedImage);

                listPilihanJawabanGambar.add(listPilihanJawabanDGambar);

                List<String> listPilihanJawabanEGambar = new ArrayList<>();
                baos = new ByteArrayOutputStream();
                ((BitmapDrawable) pilihanJawabanEGambar.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                listPilihanJawabanEGambar.add(encodedImage);

                listPilihanJawabanGambar.add(listPilihanJawabanEGambar);
                soalUjianDetail.setPilihanJawabanGambar(listPilihanJawabanGambar.toString());

                soalUjianDetail.setKunciJawaban(kunciJawaban.getText().toString());

                Call<SoalUjianDetailTambahResponse> call = apiInterface.tambahSoalUjianDetail(soalUjianDetail);
                call.enqueue(new Callback<SoalUjianDetailTambahResponse>() {
                    @Override
                    public void onResponse(Call<SoalUjianDetailTambahResponse> call, Response<SoalUjianDetailTambahResponse> response) {
                        Toast.makeText(SoalUjianDetailTambahActivity.this, "Soal ujian detail sudah ditambah", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SoalUjianDetailTambahResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void showSoalGambarPictureDialog() {
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
                                chooseSoalGambarFromGallery();
                                break;
                            case 1:
                                takeSoalGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void chooseSoalGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_SOAL_GAMBAR);
    }

    private void takeSoalGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_SOAL_GAMBAR);
    }

    private void showPilihanJawabanAGambarPictureDialog() {
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
                                choosePilihanJawabanAGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanAGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanAGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_A_GAMBAR);
    }

    private void takePilihanJawabanAGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_A_GAMBAR);
    }

    private void showPilihanJawabanBGambarPictureDialog() {
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
                                choosePilihanJawabanBGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanBGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanBGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_B_GAMBAR);
    }

    private void takePilihanJawabanBGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_B_GAMBAR);
    }

    private void showPilihanJawabanCGambarPictureDialog() {
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
                                choosePilihanJawabanCGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanCGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanCGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_C_GAMBAR);
    }

    private void takePilihanJawabanCGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_C_GAMBAR);
    }

    private void showPilihanJawabanDGambarPictureDialog() {
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
                                choosePilihanJawabanDGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanDGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanDGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_D_GAMBAR);
    }

    private void takePilihanJawabanDGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_D_GAMBAR);
    }

    private void showPilihanJawabanEGambarPictureDialog() {
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
                                choosePilihanJawabanEGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanEGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanEGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_E_GAMBAR);
    }

    private void takePilihanJawabanEGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_E_GAMBAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY_SOAL_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    soalGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_SOAL_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            soalGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY_PILIHAN_JAWABAN_A_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    pilihanJawabanAGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_PILIHAN_JAWABAN_A_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pilihanJawabanAGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY_PILIHAN_JAWABAN_B_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    pilihanJawabanBGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_PILIHAN_JAWABAN_B_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pilihanJawabanBGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY_PILIHAN_JAWABAN_C_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    pilihanJawabanCGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_PILIHAN_JAWABAN_C_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pilihanJawabanCGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY_PILIHAN_JAWABAN_D_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    pilihanJawabanDGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_PILIHAN_JAWABAN_D_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pilihanJawabanDGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY_PILIHAN_JAWABAN_E_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    pilihanJawabanEGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_PILIHAN_JAWABAN_E_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pilihanJawabanEGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        sharedPreferences = getSharedPreferences(SOAL_UJIAN_DETAIL_TAMBAH, 0);
        if (sharedPreferences.getString(ID_SOAL_UJIAN, null) != null) {
            idSoalUjian.setText(sharedPreferences.getString(ID_SOAL_UJIAN, null));
        }
        if (sharedPreferences.getString(NAMA_SOAL_UJIAN, null) != null) {
            namaSoalUjian.setText(sharedPreferences.getString(NAMA_SOAL_UJIAN, null));
        }
        if (sharedPreferences.getString(ID_JENIS_SOAL_UJIAN_DETAIL, null) != null) {
            idJenisSoalUjianDetail.setText(sharedPreferences.getString(ID_JENIS_SOAL_UJIAN_DETAIL, null));
        }
        if (sharedPreferences.getString(NAMA_JENIS_SOAL_UJIAN_DETAIL, null) != null) {
            namaJenisSoalUjianDetail.setText(sharedPreferences.getString(NAMA_JENIS_SOAL_UJIAN_DETAIL, null));
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
