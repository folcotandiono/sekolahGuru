package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.sekolahguru.folcotandiono.sekolahguru.adapter.MataPelajaranAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaran;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaranResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.ID;
import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.LOGIN;

public class SoalUjianTambahActivity extends AppCompatActivity {

    private EditText soalUjianTambahMataPelajaran;
    private Button soalUjianTambahMataPelajaranPilih;
    private EditText soalUjianTambahNama;
    private ApiInterface apiInterface;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian_tambah);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        soalUjianTambahMataPelajaran = findViewById(R.id.soal_ujian_tambah_mata_pelajaran);
        soalUjianTambahMataPelajaranPilih = findViewById(R.id.soal_ujian_tambah_mata_pelajaran_pilih);
        soalUjianTambahNama = findViewById(R.id.soal_ujian_tambah_nama);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences(LOGIN, 0);
        editor = sharedPreferences.edit();
    }

    private void initListener() {
        soalUjianTambahMataPelajaranPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> param = new HashMap<>();
                param.put("id", sharedPreferences.getString(ID, null));
                Call<MataPelajaranResponse> call = apiInterface.getDataMataPelajaran(param);
                call.enqueue(new Callback<MataPelajaranResponse>() {
                    @Override
                    public void onResponse(Call<MataPelajaranResponse> call, Response<MataPelajaranResponse> response) {
                        final List<MataPelajaran> listMataPelajaran = response.body().getListMataPelajaran();

                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getApplicationContext());

                        final MataPelajaranAdapter mataPelajaranAdapter = new MataPelajaranAdapter(listMataPelajaran);

                        builderSingle.setAdapter((ListAdapter) mataPelajaranAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String namaMataPelajaran = listMataPelajaran.get(which).getNama();

                            }
                        });
                        builderSingle.show();
                    }

                    @Override
                    public void onFailure(Call<MataPelajaranResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
