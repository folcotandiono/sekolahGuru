package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.IP_ADDRESS;
import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.LOGIN;
import static com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient.BASE_IP_ADDRESS;

public class KonfigurasiIpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText ip;
    private Button konfigurasiIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfigurasi_ip);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        ip = findViewById(R.id.ip);
        konfigurasiIp = findViewById(R.id.konfigurasi_ip);
    }

    private void initObject() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Konfigurasi IP");
    }

    private void initListener() {
        konfigurasiIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ip.getText().toString().isEmpty()) {
                    Toast.makeText(KonfigurasiIpActivity.this, "IP address kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences sharedPreferences = getSharedPreferences(LOGIN, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(IP_ADDRESS, ip.getText().toString());
                editor.commit();
                Toast.makeText(KonfigurasiIpActivity.this, "IP address berhasil dikonfigurasi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
