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

import com.sekolahguru.folcotandiono.sekolahguru.adapter.SoalUjianDetailAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetail;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetailGet;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetailResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.ID;
import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.LOGIN;

public class SoalUjianDetailActivity extends AppCompatActivity {

    private Toolbar soalUjianDetailToolbar;
    private Button soalUjianDetailTambah;
    private RecyclerView soalUjianDetailRecyclerView;

    private SharedPreferences sharedPreferences;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian_detail);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        soalUjianDetailToolbar = findViewById(R.id.soal_ujian_detail_toolbar);
        soalUjianDetailTambah = findViewById(R.id.soal_ujian_detail_tambah);
        soalUjianDetailRecyclerView = findViewById(R.id.soal_ujian_detail_recycler_view);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);

        setSupportActionBar(soalUjianDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Soal Ujian Detail");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        soalUjianDetailRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager soalUjianDetailLayoutManager = new LinearLayoutManager(this);
        soalUjianDetailRecyclerView.setLayoutManager(soalUjianDetailLayoutManager);


    }

    private void initListener() {
        soalUjianDetailTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SoalUjianDetailTambahActivity.class);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
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

        Call<SoalUjianDetailResponse> call = apiInterface.getDataSoalUjianDetail(param);
        call.enqueue(new Callback<SoalUjianDetailResponse>() {
            @Override
            public void onResponse(Call<SoalUjianDetailResponse> call, Response<SoalUjianDetailResponse> response) {
                List<SoalUjianDetailGet> listSoalUjianDetail = response.body().getListSoalUjianDetail();
                // specify an adapter (see also next example)
                SoalUjianDetailAdapter soalUjianDetailAdapter = new SoalUjianDetailAdapter(listSoalUjianDetail);
                soalUjianDetailRecyclerView.setAdapter(soalUjianDetailAdapter);

            }

            @Override
            public void onFailure(Call<SoalUjianDetailResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
