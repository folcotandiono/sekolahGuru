package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sekolahguru.folcotandiono.sekolahguru.adapter.JenisSoalUjianDetailAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.adapter.SoalUjianAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.JenisSoalUjianDetail;
import com.sekolahguru.folcotandiono.sekolahguru.model.JenisSoalUjianDetailResponse;
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

public class SoalUjianDetailTambahJenisSoalUjianDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ApiInterface apiInterface;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian_detail_tambah_jenis_soal_ujian_detail);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences(LOGIN, 0);
        editor = sharedPreferences.edit();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pilih jenis soal ujian detail");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        Map<String, String> param = new HashMap<>();
        param.put(ID, sharedPreferences.getString(ID, null));

        Call<JenisSoalUjianDetailResponse> call = apiInterface.getDataJenisSoalUjianDetail(param);
        call.enqueue(new Callback<JenisSoalUjianDetailResponse>() {
            @Override
            public void onResponse(Call<JenisSoalUjianDetailResponse> call, Response<JenisSoalUjianDetailResponse> response) {
                // specify an adapter (see also next example)
                List<JenisSoalUjianDetail> listJenisSoalUjianDetail = response.body().getListJenisSoalUjianDetail();
                JenisSoalUjianDetailAdapter jenisSoalUjianDetailAdapter = new JenisSoalUjianDetailAdapter(listJenisSoalUjianDetail);
                recyclerView.setAdapter(jenisSoalUjianDetailAdapter);
            }

            @Override
            public void onFailure(Call<JenisSoalUjianDetailResponse> call, Throwable t) {
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
