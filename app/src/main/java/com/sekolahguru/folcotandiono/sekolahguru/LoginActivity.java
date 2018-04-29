package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.Guru;
import com.sekolahguru.folcotandiono.sekolahguru.model.GuruLoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText loginId;
    private EditText loginPassword;
    private Button loginLogin;
    private ApiInterface apiInterface;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static String LOGIN = "login";
    public static String ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initObject();
        initListener();

        sharedPreferences = getSharedPreferences(LOGIN, 0);
        if (sharedPreferences.getString(ID, null) != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        loginId = findViewById(R.id.login_id);
        loginPassword = findViewById(R.id.login_password);
        loginLogin = findViewById(R.id.login_login);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences(LOGIN, 0);
        editor = sharedPreferences.edit();
    }

    private void initListener() {
        loginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = loginId.getText().toString();
                String password = loginPassword.getText().toString();

                if (id.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Id kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                Guru guru = new Guru();
                guru.setId(id);
                guru.setPassword(password);

                Call<GuruLoginResponse> call = apiInterface.login(guru);
                call.enqueue(new Callback<GuruLoginResponse>() {
                    @Override
                    public void onResponse(Call<GuruLoginResponse> call, Response<GuruLoginResponse> response) {
                        List<Guru> guru = response.body().getListGuru();

                        if (guru.size() == 0) {
                            Toast.makeText(LoginActivity.this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            editor.putString(ID, id);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<GuruLoginResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
