package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoalUjianActivity extends AppCompatActivity {

    private Button soalUjianTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        soalUjianTambah = findViewById(R.id.soal_ujian_tambah);
    }

    private void initObject() {
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
}
