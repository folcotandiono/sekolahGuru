package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.sekolahguru.folcotandiono.sekolahguru.adapter.MataPelajaranAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaran;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaranResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjian;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianTambahResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.ID;
import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.LOGIN;

public class SoalUjianTambahActivity extends AppCompatActivity {

    private Toolbar soalUjianTambahToolbar;
    private EditText soalUjianTambahIdMataPelajaran;
    private EditText soalUjianTambahNamaMataPelajaran;
    private Button soalUjianTambahMataPelajaranPilih;
    private EditText soalUjianTambahNama;
    private Button soalUjianTambahTambah;

    private ApiInterface apiInterface;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String idMataPelajaran;
    private String namaMataPelajaran;
    private String nama;

    public static String SOAL_UJIAN_TAMBAH = "soal_ujian_tambah";
    public static String ID_MATA_PELAJARAN = "id_mata_pelajaran";
    public static String NAMA_MATA_PELAJARAN = "nama_mata_pelajaran";
    public static String NAMA = "nama";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian_tambah);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        soalUjianTambahToolbar = findViewById(R.id.soal_ujian_tambah_toolbar);
        soalUjianTambahIdMataPelajaran = findViewById(R.id.soal_ujian_tambah_id_mata_pelajaran);
        soalUjianTambahNamaMataPelajaran = findViewById(R.id.soal_ujian_tambah_nama_mata_pelajaran);
        soalUjianTambahMataPelajaranPilih = findViewById(R.id.soal_ujian_tambah_mata_pelajaran_pilih);
        soalUjianTambahNama = findViewById(R.id.soal_ujian_tambah_nama);
        soalUjianTambahTambah = findViewById(R.id.soal_ujian_tambah_tambah);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences(SOAL_UJIAN_TAMBAH, 0);
        editor = sharedPreferences.edit();

        setSupportActionBar(soalUjianTambahToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah soal ujian");
    }

    private void initListener() {
        soalUjianTambahMataPelajaranPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SoalUjianTambahMataPelajaranActivity.class);
                startActivity(intent);

                sharedPreferences = getApplicationContext().getSharedPreferences(SOAL_UJIAN_TAMBAH, 0);
                editor = sharedPreferences.edit();
                editor.putString(ID_MATA_PELAJARAN, idMataPelajaran);
                editor.putString(NAMA_MATA_PELAJARAN, namaMataPelajaran);
                editor.putString(NAMA, nama);
                editor.commit();
            }
        });
        soalUjianTambahTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idMataPelajaran = soalUjianTambahIdMataPelajaran.getText().toString();
                nama = soalUjianTambahNama.getText().toString();
                if (idMataPelajaran == null || idMataPelajaran.isEmpty()) {
                    Toast.makeText(SoalUjianTambahActivity.this, "Pilih mata pelajaran", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nama == null || nama.isEmpty()) {
                    Toast.makeText(SoalUjianTambahActivity.this, "Nama kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                SoalUjian soalUjian = new SoalUjian();
                soalUjian.setIdMataPelajaran(idMataPelajaran);
                sharedPreferences = getApplicationContext().getSharedPreferences(LOGIN, 0);
                soalUjian.setNama(nama);

                Call<SoalUjianTambahResponse> call = apiInterface.tambahSoalUjian(soalUjian);
                call.enqueue(new Callback<SoalUjianTambahResponse>() {
                    @Override
                    public void onResponse(Call<SoalUjianTambahResponse> call, Response<SoalUjianTambahResponse> response) {
                        Toast.makeText(SoalUjianTambahActivity.this, "Soal ujian sudah ditambah", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SoalUjianTambahResponse> call, Throwable t) {

                    }
                });
            }
        });
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

    @Override
    protected void onResume() {
        sharedPreferences = getApplicationContext().getSharedPreferences(SOAL_UJIAN_TAMBAH, 0);
        String idMataPelajaran = sharedPreferences.getString(ID_MATA_PELAJARAN, null);
        String namaMataPelajaran = sharedPreferences.getString(NAMA_MATA_PELAJARAN, null);
        String nama = sharedPreferences.getString(NAMA, null);

        if (idMataPelajaran != null) {
            soalUjianTambahIdMataPelajaran.setText(idMataPelajaran);
        }
        if (namaMataPelajaran != null) {
            soalUjianTambahNamaMataPelajaran.setText(namaMataPelajaran);
        }
        if (nama != null) {
            soalUjianTambahNama.setText(nama);
        }
        super.onResume();
    }
}
