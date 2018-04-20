package com.sekolahguru.folcotandiono.sekolahguru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sekolahguru.folcotandiono.sekolahguru.adapter.MataPelajaranAdapter;

public class SoalUjianTambahMataPelajaranActivity extends AppCompatActivity {

    private Toolbar soalUjianTambahMataPelajaranToolbar;
    private RecyclerView soalUjianTambahMataPelajaranRecyclerView;

    private String idMataPelajaran;
    private String namaMataPelajaran;
    private String nama;
    private String guru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian_tambah_mata_pelajaran);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        soalUjianTambahMataPelajaranToolbar = findViewById(R.id.toolbar);
        soalUjianTambahMataPelajaranRecyclerView = findViewById(R.id.soal_ujian_tambah_mata_pelajaran_recycler_view);
    }

    private void initObject() {
        setSupportActionBar(soalUjianTambahMataPelajaranToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idMataPelajaran = getIntent().getStringExtra("id_mata_pelajaran");
        namaMataPelajaran = getIntent().getStringExtra("nama_mata_pelajaran");
        nama = getIntent().getStringExtra("nama");
        guru = getIntent().getStringExtra("guru");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        soalUjianTambahMataPelajaranRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        soalUjianTambahMataPelajaranRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        MataPelajaranAdapter mataPelajaranAdapter = new MataPelajaranAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
    }
}
