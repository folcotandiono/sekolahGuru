package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sekolahguru.folcotandiono.sekolahguru.adapter.MataPelajaranPilihAdapter;
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

public class MateriPelajaranTambahMataPelajaranActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ApiInterface apiInterface;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String idMataPelajaran;
    private String namaMataPelajaran;
    private String nama;
    private String guru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_pelajaran_tambah_mata_pelajaran);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences(LOGIN, 0);
        editor = sharedPreferences.edit();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pilih mata pelajaran");

        idMataPelajaran = getIntent().getStringExtra("id_mata_pelajaran");
        namaMataPelajaran = getIntent().getStringExtra("nama_mata_pelajaran");
        nama = getIntent().getStringExtra("nama");
        guru = getIntent().getStringExtra("guru");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        Map<String, String> param = new HashMap<>();
        param.put(ID, sharedPreferences.getString(ID, null));

        Call<MataPelajaranResponse> call = apiInterface.getDataMataPelajaran(param);
        call.enqueue(new Callback<MataPelajaranResponse>() {
            @Override
            public void onResponse(Call<MataPelajaranResponse> call, Response<MataPelajaranResponse> response) {
                // specify an adapter (see also next example)
                List<MataPelajaran> listMataPelajaran = response.body().getListMataPelajaran();
                MataPelajaranPilihAdapter mataPelajaranPilihAdapter = new MataPelajaranPilihAdapter(listMataPelajaran);
                recyclerView.setAdapter(mataPelajaranPilihAdapter);

            }

            @Override
            public void onFailure(Call<MataPelajaranResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initListener() {
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
