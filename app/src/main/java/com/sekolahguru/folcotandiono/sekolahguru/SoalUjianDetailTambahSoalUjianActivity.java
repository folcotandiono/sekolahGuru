package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sekolahguru.folcotandiono.sekolahguru.adapter.MataPelajaranAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.adapter.SoalUjianAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.adapter.SoalUjianPilihAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaran;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaranResponse;
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

public class SoalUjianDetailTambahSoalUjianActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ApiInterface apiInterface;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian_detail_tambah_soal_ujian);

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
        getSupportActionBar().setTitle("Pilih soal ujian");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        Map<String, String> param = new HashMap<>();
        param.put(ID, sharedPreferences.getString(ID, null));

        Call<SoalUjianResponse> call = apiInterface.getDataSoalUjian(param);
        call.enqueue(new Callback<SoalUjianResponse>() {
            @Override
            public void onResponse(Call<SoalUjianResponse> call, Response<SoalUjianResponse> response) {
                // specify an adapter (see also next example)
                List<SoalUjian> listSoalUjian = response.body().getListSoalUjian();
                SoalUjianPilihAdapter soalUjianPilihAdapter = new SoalUjianPilihAdapter(listSoalUjian);
                recyclerView.setAdapter(soalUjianPilihAdapter);
            }

            @Override
            public void onFailure(Call<SoalUjianResponse> call, Throwable t) {
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
