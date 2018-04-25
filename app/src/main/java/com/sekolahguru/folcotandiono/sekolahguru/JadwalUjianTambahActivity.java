package com.sekolahguru.folcotandiono.sekolahguru;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.JadwalUjian;
import com.sekolahguru.folcotandiono.sekolahguru.model.JadwalUjianTambahResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalUjianTambahActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static String JADWAL_UJIAN_TAMBAH = "jadwal_ujian_tambah";
    public static String ID_SOAL_UJIAN = "id_soal_ujian";
    public static String NAMA_SOAL_UJIAN = "nama_soal_ujian";

    private Toolbar toolbar;
    private EditText idSoalUjian;
    private EditText namaSoalUjian;
    private Button pilihSoalUjian;
    private EditText tanggal;
    private EditText waktu;
    private EditText nama;
    private EditText durasi;
    private Button tambah;

    private Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tanggal.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ujian_tambah);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        idSoalUjian = findViewById(R.id.id_soal_ujian);
        namaSoalUjian = findViewById(R.id.nama_soal_ujian);
        pilihSoalUjian = findViewById(R.id.pilih_soal_ujian);
        tanggal = findViewById(R.id.tanggal);
        waktu = findViewById(R.id.waktu);
        nama = findViewById(R.id.nama);
        durasi = findViewById(R.id.durasi);
        tambah = findViewById(R.id.tambah);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences(JADWAL_UJIAN_TAMBAH, 0);
        editor = sharedPreferences.edit();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah soal ujian");
    }

    private void initListener() {
        tanggal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(JadwalUjianTambahActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        waktu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(JadwalUjianTambahActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        waktu.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();

            }
        });
        pilihSoalUjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JadwalUjianTambahSoalUjianActivity.class);
                startActivity(intent);
            }
        });
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idSoalUjian.getText().toString().isEmpty()) {
                    Toast.makeText(JadwalUjianTambahActivity.this, "Pilih soal ujian", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(tanggal.getText().toString().isEmpty()) {
                    Toast.makeText(JadwalUjianTambahActivity.this, "Pilih tanggal", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (waktu.getText().toString().isEmpty()) {
                    Toast.makeText(JadwalUjianTambahActivity.this, "Pilih waktu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nama.getText().toString().isEmpty()) {
                    Toast.makeText(JadwalUjianTambahActivity.this, "Pilih nama", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (durasi.getText().toString().isEmpty()) {
                    Toast.makeText(JadwalUjianTambahActivity.this, "Pilih durasi", Toast.LENGTH_SHORT).show();
                    return;
                }

                JadwalUjian jadwalUjian = new JadwalUjian();
                jadwalUjian.setIdSoalUjian(idSoalUjian.getText().toString());
                jadwalUjian.setTanggal(tanggal.getText().toString() + " " + waktu.getText().toString());
                jadwalUjian.setNama(nama.getText().toString());
                jadwalUjian.setDurasi(durasi.getText().toString());

                Call<JadwalUjianTambahResponse> call = apiInterface.tambahJadwalUjian(jadwalUjian);
                call.enqueue(new Callback<JadwalUjianTambahResponse>() {
                    @Override
                    public void onResponse(Call<JadwalUjianTambahResponse> call, Response<JadwalUjianTambahResponse> response) {
                        Toast.makeText(JadwalUjianTambahActivity.this, "Jadwal ujian telah ditambah", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<JadwalUjianTambahResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        sharedPreferences = getSharedPreferences(JADWAL_UJIAN_TAMBAH, 0);
        if (sharedPreferences.getString(ID_SOAL_UJIAN, null) != null) {
            idSoalUjian.setText(sharedPreferences.getString(ID_SOAL_UJIAN, null));
        }
        if (sharedPreferences.getString(NAMA_SOAL_UJIAN, null) != null) {
            namaSoalUjian.setText(sharedPreferences.getString(NAMA_SOAL_UJIAN, null));
        }
        super.onResume();
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
