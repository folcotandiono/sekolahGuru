package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.sekolahguru.folcotandiono.sekolahguru.adapter.JadwalUjianAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.adapter.SoalUjianAdapter;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.JadwalUjian;
import com.sekolahguru.folcotandiono.sekolahguru.model.JadwalUjianResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjian;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.ID;
import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.LOGIN;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout homeDrawerLayout;
    private NavigationView homeNavigationView;
    private Button tambahJadwal;
    private RecyclerView recyclerView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        homeDrawerLayout = findViewById(R.id.drawer_layout);
        homeNavigationView = findViewById(R.id.nav_view);
        tambahJadwal = findViewById(R.id.tambah_jadwal);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initObject() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Jadwal ujian");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    private void initListener() {
        homeNavigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.soal_ujian) {
                        Intent intent = new Intent(getApplicationContext(), SoalUjianActivity.class);
                        startActivity(intent);
                    }
                    else if (menuItem.getItemId() == R.id.soal_ujian_detail) {
                        Intent intent = new Intent(getApplicationContext(), SoalUjianDetailActivity.class);
                        startActivity(intent);
                    }
                    else if (menuItem.getItemId() == R.id.pr) {
                        Intent intent = new Intent(getApplicationContext(), PRActivity.class);
                        startActivity(intent);
                    }
                    else if (menuItem.getItemId() == R.id.materi_pelajaran) {
                        Intent intent = new Intent(getApplicationContext(), MateriPelajaranActivity.class);
                        startActivity(intent);
                    }
                    else if (menuItem.getItemId() == R.id.log_out) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                        sharedPreferences = getSharedPreferences(LOGIN, 0);
                        editor = sharedPreferences.edit();
                        editor.putString(ID, null);
                        editor.commit();

                        startActivity(intent);
                    }
                    homeDrawerLayout.closeDrawers();
                    return true;
                }
            }
        );
        tambahJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JadwalUjianTambahActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                homeDrawerLayout.openDrawer(GravityCompat.START);
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

        Call<JadwalUjianResponse> call = apiInterface.getDataJadwalUjian(param);
        call.enqueue(new Callback<JadwalUjianResponse>() {
            @Override
            public void onResponse(Call<JadwalUjianResponse> call, Response<JadwalUjianResponse> response) {
                List<JadwalUjian> listJadwalUjian = response.body().getListJadwalUjian();
                JadwalUjianAdapter jadwalUjianAdapter = new JadwalUjianAdapter(listJadwalUjian);
                recyclerView.setAdapter(jadwalUjianAdapter);
            }

            @Override
            public void onFailure(Call<JadwalUjianResponse> call, Throwable t) {

            }
        });
    }
}
