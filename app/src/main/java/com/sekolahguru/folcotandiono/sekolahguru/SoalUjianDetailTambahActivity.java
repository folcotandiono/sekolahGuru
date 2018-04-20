package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;

public class SoalUjianDetailTambahActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText idSoalUjian;
    private EditText namaSoalUjian;
    private EditText idJenisSoalUjianDetail;
    private EditText namaJenisSoalUjianDetail;

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
        idJenisSoalUjianDetail = findViewById(R.id.id_jenis_soal_ujian_detail);
        namaJenisSoalUjianDetail = findViewById(R.id.nama_jenis_soal_ujian_detail);

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
    }

    private void initObject() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    private void initListener() {
    }
}
