package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.sekolahguru.folcotandiono.sekolahguru.adapter.MateriPelajaranAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.adapter.PRAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.adapter.SoalUjianAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.MateriPelajaranGet;
import com.sekolahguru.folcotandiono.sekolahguru.model.MateriPelajaranResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.PR;
import com.sekolahguru.folcotandiono.sekolahguru.model.PRGet;
import com.sekolahguru.folcotandiono.sekolahguru.model.PRResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjian;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.ID;
import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.LOGIN;

public class MateriPelajaranActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button tambah;
    private RecyclerView recyclerView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_pelajaran);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        tambah = findViewById(R.id.tambah);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initObject() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Materi Pelajaran");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
    }

    private void initListener() {
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MateriPelajaranTambahActivity.class);
                startActivity(intent);
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
        super.onResume();

        sharedPreferences = getSharedPreferences(LOGIN, 0);

        Map<String, String> param = new HashMap<>();
        param.put(ID, sharedPreferences.getString(ID, null));

        Call<MateriPelajaranResponse> call = apiInterface.getDataMateriPelajaran(param);
        call.enqueue(new Callback<MateriPelajaranResponse>() {
            @Override
            public void onResponse(Call<MateriPelajaranResponse> call, Response<MateriPelajaranResponse> response) {
                List<MateriPelajaranGet> listMateriPelajaran = response.body().getListMateriPelajaran();
                MateriPelajaranAdapter materiPelajaranAdapter = new MateriPelajaranAdapter(listMateriPelajaran);
                recyclerView.setAdapter(materiPelajaranAdapter);
            }

            @Override
            public void onFailure(Call<MateriPelajaranResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
