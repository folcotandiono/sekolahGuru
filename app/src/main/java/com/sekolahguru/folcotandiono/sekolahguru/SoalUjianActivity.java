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

import com.sekolahguru.folcotandiono.sekolahguru.adapter.SoalUjianAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
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

public class SoalUjianActivity extends AppCompatActivity {

    private Toolbar soalUjianToolbar;
    private Button soalUjianTambah;
    private RecyclerView soalUjianRecyclerView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        soalUjianToolbar = findViewById(R.id.soal_ujian_toolbar);
        soalUjianTambah = findViewById(R.id.soal_ujian_tambah);
        soalUjianRecyclerView = findViewById(R.id.soal_ujian_recycler_view);
    }

    private void initObject() {
        setSupportActionBar(soalUjianToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Soal ujian");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        soalUjianRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager soalUjianLayoutManager = new LinearLayoutManager(this);
        soalUjianRecyclerView.setLayoutManager(soalUjianLayoutManager);

        apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
    }

    private void initListener() {
        soalUjianTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SoalUjianTambahActivity.class);
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

        Call<SoalUjianResponse> call = apiInterface.getDataSoalUjian(param);
        call.enqueue(new Callback<SoalUjianResponse>() {
            @Override
            public void onResponse(Call<SoalUjianResponse> call, Response<SoalUjianResponse> response) {
                List<SoalUjian> listSoalUjian = response.body().getListSoalUjian();
                SoalUjianAdapter soalUjianAdapter = new SoalUjianAdapter(listSoalUjian);
                soalUjianRecyclerView.setAdapter(soalUjianAdapter);
            }

            @Override
            public void onFailure(Call<SoalUjianResponse> call, Throwable t) {

            }
        });
    }
}
