package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sekolahguru.folcotandiono.sekolahguru.api.ApiClient;
import com.sekolahguru.folcotandiono.sekolahguru.api.ApiInterface;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetail;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetailResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoalUjianDetailTambahActivity extends AppCompatActivity {

    private static final int GALLERY_SOAL_GAMBAR = 0;
    private static final int CAMERA_SOAL_GAMBAR = 1;
    private static final int GALLERY_PILIHAN_JAWABAN_A_GAMBAR = 2;
    private static final int CAMERA_PILIHAN_JAWABAN_A_GAMBAR = 3;
    private static final int GALLERY_PILIHAN_JAWABAN_B_GAMBAR = 4;
    private static final int CAMERA_PILIHAN_JAWABAN_B_GAMBAR = 5;
    private static final int GALLERY_PILIHAN_JAWABAN_C_GAMBAR = 6;
    private static final int CAMERA_PILIHAN_JAWABAN_C_GAMBAR = 7;
    private static final int GALLERY_PILIHAN_JAWABAN_D_GAMBAR = 8;
    private static final int CAMERA_PILIHAN_JAWABAN_D_GAMBAR = 9;
    private static final int GALLERY_PILIHAN_JAWABAN_E_GAMBAR = 10;
    private static final int CAMERA_PILIHAN_JAWABAN_E_GAMBAR = 11;

    private Toolbar toolbar;
    private EditText idSoalUjian;
    private EditText namaSoalUjian;
    private EditText idJenisSoalUjianDetail;
    private EditText namaJenisSoalUjianDetail;

    private EditText soalTulisan;
    private Button soalGambarPilih;
    private ImageView soalGambar;

    private EditText pilihanJawabanATulisan;
    private Button pilihanJawabanAGambarPilih;
    private ImageView pilihanJawabanAGambar;

    private EditText pilihanJawabanBTulisan;
    private Button pilihanJawabanBGambarPilih;
    private ImageView pilihanJawabanBGambar;

    private EditText pilihanJawabanCTulisan;
    private Button pilihanJawabanCGambarPilih;
    private ImageView pilihanJawabanCGambar;

    private EditText pilihanJawabanDTulisan;
    private Button pilihanJawabanDGambarPilih;
    private ImageView pilihanJawabanDGambar;

    private EditText pilihanJawabanETulisan;
    private Button pilihanJawabanEGambarPilih;
    private ImageView pilihanJawabanEGambar;

    private Button tambah;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian_detail_tambah);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        idSoalUjian = findViewById(R.id.id_soal_ujian);
        namaSoalUjian = findViewById(R.id.nama_soal_ujian);
        idJenisSoalUjianDetail = findViewById(R.id.id_jenis_soal_ujian_detail);
        namaJenisSoalUjianDetail = findViewById(R.id.nama_jenis_soal_ujian_detail);

        soalTulisan = findViewById(R.id.soal_tulisan);
        soalGambarPilih = findViewById(R.id.soal_gambar_pilih);
        soalGambar = findViewById(R.id.soal_gambar);

        pilihanJawabanATulisan = findViewById(R.id.pilihan_jawaban_a_tulisan);
        pilihanJawabanAGambarPilih = findViewById(R.id.pilihan_jawaban_a_gambar_pilih);
        pilihanJawabanAGambar = findViewById(R.id.pilihan_jawaban_a_gambar);

        pilihanJawabanBTulisan = findViewById(R.id.pilihan_jawaban_b_tulisan);
        pilihanJawabanBGambarPilih = findViewById(R.id.pilihan_jawaban_b_gambar_pilih);
        pilihanJawabanBGambar = findViewById(R.id.pilihan_jawaban_b_gambar);

        pilihanJawabanCTulisan = findViewById(R.id.pilihan_jawaban_c_tulisan);
        pilihanJawabanCGambarPilih = findViewById(R.id.pilihan_jawaban_c_gambar_pilih);
        pilihanJawabanCGambar = findViewById(R.id.pilihan_jawaban_c_gambar);

        pilihanJawabanDTulisan = findViewById(R.id.pilihan_jawaban_d_tulisan);
        pilihanJawabanDGambarPilih = findViewById(R.id.pilihan_jawaban_d_gambar_pilih);
        pilihanJawabanDGambar = findViewById(R.id.pilihan_jawaban_d_gambar);

        pilihanJawabanETulisan = findViewById(R.id.pilihan_jawaban_e_tulisan);
        pilihanJawabanEGambarPilih = findViewById(R.id.pilihan_jawaban_e_gambar_pilih);
        pilihanJawabanEGambar = findViewById(R.id.pilihan_jawaban_e_gambar);

        tambah = findViewById(R.id.tambah);
    }

    private void initObject() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Soal Ujian Detail");

        String encodedImage = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\n" +
                "AQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEB\n" +
                "AQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCAHgAoADASIA\n" +
                "AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\n" +
                "AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\n" +
                "ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\n" +
                "p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\n" +
                "AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\n" +
                "BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\n" +
                "U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\n" +
                "uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDyeiii\n" +
                "gAooooAKKKKACv4l6/tor+JegAooooAKKKKAIxhV55K4/DJfHscjP/6zQMnOTyMc4Hy5J7d9wA+m\n" +
                "e5BpFJb6jqeO5Pb6Afn6g12HgDwD4w+J/izS/AngPSP7a8T6z/aH9m6YL/StN+1f2dYX2sXp+16x\n" +
                "f6dYQ/ZrLTLy4P2m6/5Z8A3ozNzV6+HweGrYzG1sLRwlDCxxWLxeKvhsLhcJhZVFisVisUmnhcJh\n" +
                "WnzW1T1a0d+PE4rC4HCYzHY/FYbA4PA4TFYvFYrE4v6rg8LhcK3isVisVisWnhsJhcLhof7Vi8To\n" +
                "tPe0ucZX9tFfyrf8MEftdf8ARIj/AOFx8Nf/AJtq/qO/4Sjw7/z/AI/8Br7/AOR6+W/4iP4d/wDR\n" +
                "f8F/+JZw7/8ANh8V/wARW8Kv+jm8Bf8AiZcPf/PU26KxP+Eo8O/8/wCP/Aa+/wDkej/hKPDv/P8A\n" +
                "j/wGvv8A5Ho/4iP4d/8ARf8ABf8A4lnDv/zYH/EVvCr/AKObwF/4mXD3/wA9TborE/4Sjw7/AM/4\n" +
                "/wDAa+/+R6P+Eo8O/wDP+P8AwGvv/kej/iI/h3/0X/Bf/iWcO/8AzYH/ABFbwq/6ObwF/wCJlw9/\n" +
                "89TborE/4Sjw7/z/AI/8Br7/AOR6P+Eo8O/8/wCP/Aa+/wDkej/iI/h3/wBF/wAF/wDiWcO//Ngf\n" +
                "8RW8Kv8Ao5vAX/iZcPf/AD1Nuv4l6/tN/wCEo8O/8/4/8Br7/wCR6/lx/wCGCP2uv+iRH/wuPhr/\n" +
                "APNtR/xEfw7/AOi/4L/8Szh3/wCbA/4it4Vf9HN4C/8AEy4e/wDnqfIFFfX/APwwR+11/wBEiP8A\n" +
                "4XHw1/8Am2o/4YI/a6/6JEf/AAuPhr/821H/ABEfw7/6L/gv/wASzh3/AObA/wCIreFX/RzeAv8A\n" +
                "xMuHv/nqfIFFfX//AAwR+11/0SI/+Fx8Nf8A5tqP+GCP2uv+iRH/AMLj4a//ADbUf8RH8O/+i/4L\n" +
                "/wDEs4d/+bA/4it4Vf8ARzeAv/Ey4e/+ep8gUV9f/wDDBH7XX/RIj/4XHw1/+baj/hgj9rr/AKJE\n" +
                "f/C4+Gv/AM21H/ER/Dv/AKL/AIL/APEs4d/+bA/4it4Vf9HN4C/8TLh7/wCep8gV/bRX8q3/AAwR\n" +
                "+11/0SI/+Fx8Nf8A5tq/qO/4Sjw7/wA/4/8AAa+/+R6P+Ij+Hf8A0X/Bf/iWcO//ADYH/EVvCr/o\n" +
                "5vAX/iZcPf8Az1OgUBvm6f8A62Hr/s5/H2pq4b7zZPpj3fuPZQf065zgDxRoAAxqI4z832W89SOn\n" +
                "2f3I/H8as2uu6Tf3EVra3vmyyebsAgu49/kxNMeJrfjm3H45wQQGbTC8ecCY3EUsHguNuFq2Mr4t\n" +
                "YTCYXC8QZTisZisVinLC4PCYTCfXH9bxeLaT1fNdu7TUTfCeJvhtjsXhsvwHiHwbjsbjMVg8LhcJ\n" +
                "heKeH8Ti8VisTKeEwmEwmGwubrFYvFYzEL6rhMLqm+ZX1s9uiiivrj7oKKKKACv4l6/tor+JegAo\n" +
                "oooAKKKKACiiigAr+2iv4l6/tooAKKKKACiiigAooooAK/iXr+2iv4l6ACiiigAooooAKKKKACv7\n" +
                "aK/iXr+2igAooooAKKKKACiiigAr+Jev7aK/iXoAKKKKACiiigAooooAK/tor+Jev7aKACiiigAo\n" +
                "oooAKKKKACv4l6/tor+JegAooooAKKKKAGx/d/z/AHpK+t/2Csf8Ne/C3nt457f9U18eV8kR/d/z\n" +
                "/ekr63/YK/5O++Fn08c/+q18eV8X4hf8m88Q/wDsi+Kf/Wfzz+v+Cfn3ir/yazxN/wCyA40/9Z/O\n" +
                "D+jOiiiv8oz/ABQCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAcPuyf8A/8AQq3fCX/I\n" +
                "etPrcf8ApLqFYQ+7J/wD/wBCrd8Jf8h60+tx/wCkuoV9n4a/8nB4I/7LPhL/ANaHC+fWyfyau3q/\n" +
                "u/CT/k6vhr/2XfBn/rX4Rd/891ro2/YaKKK/1dP9tQooooAK/iXr+2iv4l6ACiiigAooooAKKKKA\n" +
                "Cv7aK/iXr+2igAooooAKKKKACiiigAr+Jev7aK/iXoAKKKKACiiigAooooAK/tor+Jev7aKACiii\n" +
                "gAooooAKKKKACv4l6/tor+JegAooooAKKKKACiiigAr+2iv4l6/tooAKK/kD/wCGmf2m/wDo4343\n" +
                "f+HW+IP/AM1FH/DTP7Tf/Rxvxu/8Ot8Qf/mooA/r8or+QP8A4aZ/ab/6ON+N3/h1viD/APNRR/w0\n" +
                "z+03/wBHG/G7/wAOt8Qf/mooA/r8or+QP/hpn9pv/o4343f+HW+IP/zUUf8ADTP7Tf8A0cb8bv8A\n" +
                "w63xB/8AmooA/r8r+JevbP8Ahpn9pv8A6ON+N3/h1viD/wDNRX9R3/DM/wCzF/0bl8Ef/DU/D/8A\n" +
                "+Z6gD+QWiv6+v+GZ/wBmL/o3L4I/+Gp+H/8A8z1H/DM/7MX/AEbl8Ef/AA1Pw/8A/meoA/kFor+v\n" +
                "r/hmf9mL/o3L4I/+Gp+H/wD8z1H/AAzP+zF/0bl8Ef8Aw1Pw/wD/AJnqAP5A4/u/5/vSV9b/ALBW\n" +
                "P+Gvfhbz28c9v+qa+PK/pDj/AGZ/2Ytv/JuXwR/8NT8P/wC9J/1L1QyfAz4FeDFl8S+D/gt8LfCv\n" +
                "iHTcf2fr3hr4e+D9E1mw+1zNZ3v2PUtN0e2v7L7VZXt1p939mus3VpNc2WT9oYzfF+IX/JvPEP8A\n" +
                "7Ivin/1n88/r/gn594q/8ms8Tf8AsgONP/Wfzgq0UUV/lGf4oBRRRQAUUUUAFFFFABRRRQAUUUUA\n" +
                "FFFFABRRRQAUUUUAOH3ZP+Af+hVu+Ev+Q9afW4/9JdQrCH3ZP+Af+hV8v/tm+JvE/gv9l74l+JfB\n" +
                "3iPXPCviHTf+EN/s/X/Deq6joms2H2v4heFLO8+xalptzbX9l9psby70+8+zXX+lWk1zZZzcAz/Z\n" +
                "+Gv/ACcHgj/ss+Ev/Whwvn1sn8mrt6v7 vwk/5Or4a/8AZd8Gf+tfhF3/AM91ro2/0eor+QP/AIaZ\n" +
                "/ab/AOjjfjd/4db4g/8AzUUf8NM/tN/9HG/G7/w63xB/+aiv9XT/AG1P6/KK/kD/AOGmf2m/+jjf\n" +
                "jd/4db4g/wDzUUf8NM/tN/8ARxvxu/8ADrfEH/5qKAP6/K/iXr2z/hpn9pv/AKON+N3/AIdb4g//\n" +
                "ADUV/Ud/wzP+zF/0bl8Ef/DU/D//AOZ6gD+QWiv6+v8Ahmf9mL/o3L4I/wDhqfh//wDM9R/wzP8A\n" +
                "sxf9G5fBH/w1Pw//APmeoA/kFor+vr/hmf8AZi/6Ny+CP/hqfh//APM9R/wzP+zF/wBG5fBH/wAN\n" +
                "T8P/AP5nqAP5BaK/r6/4Zn/Zi/6Ny+CP/hqfh/8A/M9R/wAMz/sxf9G5fBH/AMNT8P8A/wCZ6gD+\n" +
                "QWv7aK8R/wCGZ/2Yv+jcvgj/AOGp+H//AMz1fy4/8NM/tN/9HG/G7/w63xB/+aigD+vyiv5A/wDh\n" +
                "pn9pv/o4343f+HW+IP8A81FH/DTP7Tf/AEcb8bv/AA63xB/+aigD+vyiv5A/+Gmf2m/+jjfjd/4d\n" +
                "b4g//NRR/wANM/tN/wDRxvxu/wDDrfEH/wCaigD+vyiv5A/+Gmf2m/8Ao4343f8Ah1viD/8ANRR/\n" +
                "w0z+03/0cb8bv/DrfEH/AOaigD+vyv4l69s/4aZ/ab/6ON+N3/h1viD/APNRX9R3/DM/7MX/AEbl\n" +
                "8Ef/AA1Pw/8A/meoA/kFor+vr/hmf9mL/o3L4I/+Gp+H/wD8z1H/AAzP+zF/0bl8Ef8Aw1Pw/wD/\n" +
                "AJnqAP5BaK/r6/4Zn/Zi/wCjcvgj/wCGp+H/AP8AM9R/wzP+zF/0bl8Ef/DU/D//AOZ6gD+QWiv6\n" +
                "+v8Ahmf9mL/o3L4I/wDhqfh//wDM9R/wzP8Asxf9G5fBH/w1Pw//APmeoA/kFr+2ivEf+GZ/2Yv+\n" +
                "jcvgj/4an4f/APzPV/Lj/wANM/tN/wDRxvxu/wDDrfEH/wCaigD+vyiv5A/+Gmf2m/8Ao4343f8A\n" +
                "h1viD/8ANRR/w0z+03/0cb8bv/DrfEH/AOaigD+vyiv5A/8Ahpn9pv8A6ON+N3/h1viD/wDNRR/w\n" +
                "0z+03/0cb8bv/DrfEH/5qKAP6/KK/kD/AOGmf2m/+jjfjd/4db4g/wDzUUf8NM/tN/8ARxvxu/8A\n" +
                "DrfEH/5qKAP6/K/iXr2z/hpn9pv/AKON+N3/AIdb4g//ADUV/Ud/wzP+zF/0bl8Ef/DU/D//AOZ6\n" +
                "gD+QWiv6+v8Ahmf9mL/o3L4I/wDhqfh//wDM9R/wzP8Asxf9G5fBH/w1Pw//APmeoA/kFor+vr/h\n" +
                "mf8AZi/6Ny+CP/hqfh//APM9R/wzP+zF/wBG5fBH/wANT8P/AP5nqAP5BaK/r6/4Zn/Zi/6Ny+CP\n" +
                "/hqfh/8A/M9R/wAMz/sxf9G5fBH/AMNT8P8A/wCZ6gD+QWv7aK8R/wCGZ/2Yv+jcvgj/AOGp+H//\n" +
                "AMz1fy4/8NM/tN/9HG/G7/w63xB/+aigDxOiiigAooooAKKKKACv7aK/iXr+2igAooooAKKKKAGx\n" +
                "/d/z/ekrnvE+P7C1HnkC2wMdf+Jjb5+mBz+nWuhj+7/n+9JXPeJ/+QFqH0t//Thb18X4hf8AJvPE\n" +
                "P/si+Kf/AFn88/r/AIJ+feKv/JrPE3/sgONP/Wfzg8iooor/ACjP8UAooooAKKKKACiiigAooooA\n" +
                "KKKKACiiigAooooAKKKKAHD7sn/AP/Qq+Q/28P8Ak0P4s+3/AAg2P/Dl+D/8/wBa+vB92T/gH/oV\n" +
                "fIf7eH/JofxZ9v8AhBsf+HL8H/5/rX2fhr/ycHgj/ss+Ev8A1ocL59bJ/Jq7er+78JP+Tq+Gv/Zd\n" +
                "8Gf+tfhF3/z3Wujb/nRooor/AFdP9tQooooAK/tor+Jev7aKACiiigAooooAKKKKACv4l6/tor+J\n" +
                "egAooooAKKKKACiiigAr+2iv4l6/tooAKKKKACiiigAooooAK/iXr+2iv4l6ACiiigAooooAKKKK\n" +
                "ACv7aK/iXr+2igAooooAKKKKACiiigAr+Jev7aK/iXoAKKKKACiiigAooooAK/tor+Jev7aKACii\n" +
                "igAooooAbH93/P8AekrnvE+P7C1HnkC2wMdf+Jjb5+mBz+nWuhj+7/n+9JXPeJ/+QFqH0t//AE4W\n" +
                "9fF+IX/JvPEP/si+Kf8A1n88/r/gn594q/8AJrPE3/sgONP/AFn84PIqKKK/yjP8UAooooAKKKKA\n" +
                "CiiigAooooAKKKKACiiigAooooAKKKKAHD7sn/AP/Qq+Q/28P+TQ/iz7f8INj/w5fg//AD/Wvrwf\n" +
                "dk/4B/6FXyH+3h/yaH8Wfb/hBsf+HL8H/wCf619n4a/8nB4I/wCyz4S/9aHC+fWyfyau3q/u/CT/\n" +
                "AJOr4a/9l3wZ/wCtfhF3/wA91ro2/wCdGiiiv9XT/bUKKKKACv7aK/iXr+2igAooooAKKKKACiii\n" +
                "gAr+Jev7aK/iXoAKKKKACiiigAooooAK/tor+Jev7aKACiiigAooooAKKKKACv4l6/tor+JegAoo\n" +
                "ooAKKKKACiiigAr+2iv4l6/tooAKKKKACiiigAooooAK/iXr+2iv4l6ACiiigAooooAKKKKACv7a\n" +
                "K/iXr+2igAooooAKKKKAGx/d/wA/3pK57xPj+wtR55AtsDHX/iY2+fpgc/p1roY/u/5/vSVz3if/\n" +
                "AJAWofS3/wDThb18X4hf8m88Q/8Asi+Kf/Wfzz+v+Cfn3ir/AMms8Tf+yA40/wDWfzg8iooor/KM\n" +
                "/wAUAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAHD7sn/AP/AEKvkP8Abw/5ND+LPt/w\n" +
                "g2P/AA5fg/8Az/Wvrwfdk/4B/wChV8h/t4f8mh/Fn2/4QbH/AIcvwf8A5/rX2fhr/wAnB4I/7LPh\n" +
                "L/1ocL59bJ/Jq7er+78JP+Tq+Gv/AGXfBn/rX4Rd/wDPda6Nv+dGiiiv9XT/AG1CiiigAr+2iv4l\n" +
                "6/tooAKKKKACiiigAooooAK/iXr+2iv4l6ACiiigAooooAKKKKACv7aK/iXr+2igAooooAKKKKAC\n" +
                "iiigAr+Jev7aK/iXoAKKKKACiiigAooooAK/tor+Jev7aKACiiigAooooAKKKKACv4l6/tor+Jeg\n" +
                "Aor+o7/h3H+xD/0Rb/zIfxW/+byj/h3H+xD/ANEW/wDMh/Fb/wCbygD+XGiv6jv+Hcf7EP8A0Rb/\n" +
                "AMyH8Vv/AJvKP+Hcf7EP/RFv/Mh/Fb/5vKAP5caK/qO/4dx/sQ/9EW/8yH8Vv/m8o/4dx/sQ/wDR\n" +
                "Fv8AzIfxW/8Am8oA/lxr+2iviX/h3H+xD/0Rb/zIfxW/+byvxL/4eN/tu/8ARbP/ADHfwp/+YGgD\n" +
                "+o6iv5cf+Hjf7bv/AEWz/wAx38Kf/mBo/wCHjf7bv/RbP/Md/Cn/AOYGgD+o6iv5cf8Ah43+27/0\n" +
                "Wz/zHfwp/wDmBo/4eN/tu/8ARbP/ADHfwp/+YGgD+oyP7v8An+9JXPeJ8f2FqPPIFtgY6/8AExt8\n" +
                "/TA5/TrX8yUf/BRv9t3b/wAls/8AMd/Cn+9J/wBSDX0F+yj+2r+1L8Xv2h/Anw6+IvxQ/wCEg8Ie\n" +
                "IB4o/tbSP+EN+H2li8/sjwV4q1jTP+JnpHhLTb+H7NqmmWlyfs11b/avLFnfE2N1d2g+L8Qv+Tee\n" +
                "If8A2RfFP/rP55/X/BPz7xV/5NZ4m/8AZAcaf+s/nB+x1FFFf5Rn+KAUUUUAFFFFABRRRQAUUUUA\n" +
                "FFFFABRRRQAUUUUAFFFFADh92T/gH/oVfIf7eH/JofxZ9v8AhBsf+HL8H/5/rX14Puyf8A/9CrG1\n" +
                "v4b+CPi9pF78OviLov8AwkHhDxB9n/tfSf7R1bTBe/2PdprGmf8AEy0e/wBNv4Da6pp1pcf6Nc25\n" +
                "uvLFle/6DPe2dfZ+Gv8AycHgj/ss+Ev/AFocL59bJ/Jq7er+78JP+Tq+Gv8A2XfBn/rX4Rd/891r\n" +
                "o2/5LqK/qO/4dx/sQ/8ARFv/ADIfxW/+byj/AIdx/sQ/9EW/8yH8Vv8A5vK/1dP9tT+XGiv6jv8A\n" +
                "h3H+xD/0Rb/zIfxW/wDm8o/4dx/sQ/8ARFv/ADIfxW/+bygD+XGv7aK+Jf8Ah3H+xD/0Rb/zIfxW\n" +
                "/wDm8r8S/wDh43+27/0Wz/zHfwp/+YGgD+o6iv5cf+Hjf7bv/RbP/Md/Cn/5gaP+Hjf7bv8A0Wz/\n" +
                "AMx38Kf/AJgaAP6jqK/lx/4eN/tu/wDRbP8AzHfwp/8AmBo/4eN/tu/9Fs/8x38Kf/mBoA/qOor+\n" +
                "XH/h43+27/0Wz/zHfwp/+YGj/h43+27/ANFs/wDMd/Cn/wCYGgD+o6v4l6+2v+Hjf7bv/RbP/Md/\n" +
                "Cn/5ga/bT/h3H+xD/wBEW/8AMh/Fb/5vKAP5caK/qO/4dx/sQ/8ARFv/ADIfxW/+byj/AIdx/sQ/\n" +
                "9EW/8yH8Vv8A5vKAP5caK/qO/wCHcf7EP/RFv/Mh/Fb/AObyj/h3H+xD/wBEW/8AMh/Fb/5vKAP5\n" +
                "caK/qO/4dx/sQ/8ARFv/ADIfxW/+byj/AIdx/sQ/9EW/8yH8Vv8A5vKAP5ca/tor4l/4dx/sQ/8A\nRFv/ADIfxW/+byvxL/4eN/tu/wDRbP8AzHfwp/8AmBoA/qOor+XH/h43+27/ANFs/wDMd/Cn/wCY\nGj/h43+27/0Wz/zHfwp/+YGgD+o6iv5cf+Hjf7bv/RbP/Md/Cn/5gaP+Hjf7bv8A0Wz/AMx38Kf/\nAJgaAP6jqK/lx/4eN/tu/wDRbP8AzHfwp/8AmBo/4eN/tu/9Fs/8x38Kf/mBoA/qOr+Jevtr/h43\n+27/ANFs/wDMd/Cn/wCYGv20/wCHcf7EP/RFv/Mh/Fb/AObygD+XGiv6jv8Ah3H+xD/0Rb/zIfxW\n/wDm8o/4dx/sQ/8ARFv/ADIfxW/+bygD+XGiv6jv+Hcf7EP/AERb/wAyH8Vv/m8o/wCHcf7EP/RF\nv/Mh/Fb/AObygD+XGiv6jv8Ah3H+xD/0Rb/zIfxW/wDm8o/4dx/sQ/8ARFv/ADIfxW/+bygD+XGv\n7aK+Jf8Ah3H+xD/0Rb/zIfxW/wDm8r8S/wDh43+27/0Wz/zHfwp/+YGgD+o6iv5cf+Hjf7bv/RbP\n/Md/Cn/5gaP+Hjf7bv8A0Wz/AMx38Kf/AJgaAP6jqK/lx/4eN/tu/wDRbP8AzHfwp/8AmBo/4eN/\ntu/9Fs/8x38Kf/mBoA/qOor+XH/h43+27/0Wz/zHfwp/+YGj/h43+27/ANFs/wDMd/Cn/wCYGgD+\no6v4l6+2v+Hjf7bv/RbP/Md/Cn/5ga/bT/h3H+xD/wBEW/8AMh/Fb/5vKAPtqiiigAooooAKKKKA\nCv4l6/tor+JegAooooAKKKKAGx/d/wA/3pK+t/2Csf8ADXvwt57eOe3/AFTXx5XyRH93/P8Aekr6\n3/YK/wCTvvhZ9PHP/qtfHlfF+IX/ACbzxD/7Ivin/wBZ/PP6/wCCfn3ir/yazxN/7IDjT/1n84P6\nM6KKK/yjP8UAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAHD7sn/AAD/ANCrd8Jf8h60\n+tx/6S6hWEPuyf8AAP8A0Kt3wl/yHrT63H/pLqFfZ+Gv/JweCP8Ass+Ev/Whwvn1sn8mrt6v7vwk\n/wCTq+Gv/Zd8Gf8ArX4Rd/8APda6Nv2Giiiv9XT/AG1CiiigAr+Jev7aK/iXoAKKKKACiiigAooo\noAK/tor+Jev7aKACiiigAooooAKKKKACv4l6/tor+JegAooooAKKKKACiiigAr+2iv4l6/tooAKK\nKKACiiigAooooAK/iXr+2iv4l6ACiiigAooooAKKKKACv7aK/iXr+2igAooooAKKKKACiiigAr+J\nev7aK/iXoAKKKKACiiigBsf3f8/3pK+t/wBgrH/DXvwt57eOe3/VNfHlfJEf3f8AP96Svrf9gr/k\n774WfTxz/wCq18eV8X4hf8m88Q/+yL4p/wDWfzz+v+Cfn3ir/wAms8Tf+yA40/8AWfzg/ozooor/\nACjP8UAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAHD7sn/AP/Qq3fCX/IetPrcf+kuo\nVhD7sn/AP/Qq3fCX/IetPrcf+kuoV9n4a/8AJweCP+yz4S/9aHC+fWyfyau3q/u/CT/k6vhr/wBl\n3wZ/61+EXf8Az3Wujb9hooor/V0/21CiiigAr+Jev7aK/iXoAKKKKACiiigAooooAK/tor+Jev7a\nKACiiigAooooAKKKKACv4l6/tor+JegAooooAKKKKACiiigAr+2iv4l6/tooAKKKKACiiigAoooo\nAK/iXr+2iv4l6ACiiigAooooAKKKKACv7aK/iXr+2igAooooAKKKKACiiigAr+Jev7aK/iXoAKKK\nKACiiigBsf3f8/3pK+t/2Csf8Ne/C3nt457f9U18eV8kR/d/z/ekr63/AGCv+TvvhZ9PHP8A6rXx\n5XxfiF/ybzxD/wCyL4p/9Z/PP6/4J+feKv8AyazxN/7IDjT/ANZ/OD+jOiiiv8oz/FAKKKKACiii\ngAooooAKKKKACiiigAooooAKKKKACiiigBw+7J/wD/0Kt3wl/wAh60+tx/6S6hWEPuyf8A/9Crd8\nJf8AIetPrcf+kuoV9n4a/wDJweCP+yz4S/8AWhwvn1sn8mrt6v7vwk/5Or4a/wDZd8Gf+tfhF3/z\n3Wujb9hooor/AFdP9tQooooAK/iXr+2iv4l6ACiiigAooooAKKKKACv7aK/iXr+2igAooooAKKKK\nACiiigAr+Jev7aK/iXoAKKKKACiiigAooooAK/tor+Jev7aKACiiigAooooAKKKKACv4l6/tor+J\negAooooAKKKKACiiigAr+2iv4l6/tooAKK/nc/4e5ftOf9CJ8Ev/AAmviF/89ij/AIe5ftOf9CJ8\n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\n2n/Dm7/q5r/zEH/406P+"+
        "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"+
        "/+etQB+pNFflt/w9y/Zj/6Eb42/wDhO/D7\n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"+
        "fbVFfEv/AA8b\n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\nAUUUUAFF"+
        "FFABX9tFfxL1/bRQAUUUUAFFFFABRRRQAV/EvX9tFfxL0AFFFFABRRRQAUUUUAFf20V/\nEvX9tFABRRRQAUUUUAFFFFABX8S9f20V/EvQAUUUUAFFFFABRRRQAV/bRX8S9f20UAFFFFABRRRQ\nAUUUUAFfxL1/bRX8S9ABRRRQAUUUUAFFFFABX9tFfxL1/bRQAUUUUAFFFFABRRRQAV/EvX9tFfxL\n0AFFFFABRRRQAUUUUAFf20V/EvX9tFABRRRQAUUUUAFFFFABX8S9f20V/EvQAUUUUAFFFFABRRRQ\nAV/bRX8S9f20UAFFFFABRRRQAUUUUAFfxL1/bRX8S9ABRRRQAUUUUAFFFFABX9tFfxL1/bRQAUUU\nUAFFFFABRRRQAV/EvX9tFfxL0AFFFFABRRRQAUUUUAFf20V/EvX9tFABRRRQAUUUUAFFFFABX8S9\nf20V/EvQAUUUUAFFFFABRRRQAV/bRX8S9f20UAFFFFABRRRQAUUUUAFfxL1/bRX8S9ABRRRQAUUU\nUAFFFFABX9tFfxL1/bRQAUUUUAFFFFABRRRQAV/EvX9tFfxL0Af/2Q==\n";
//        Toast.makeText(this, String.valueOf(encodedImage.length()), Toast.LENGTH_SHORT).show();
//        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        soalGambar.setImageBitmap(decodedByte);
    }

    private void initListener() {
        soalGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSoalGambarPictureDialog();
            }
        });
        pilihanJawabanAGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanAGambarPictureDialog();
            }
        });
        pilihanJawabanBGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanBGambarPictureDialog();
            }
        });
        pilihanJawabanCGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanCGambarPictureDialog();
            }
        });
        pilihanJawabanDGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanDGambarPictureDialog();
            }
        });
        pilihanJawabanEGambarPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPilihanJawabanEGambarPictureDialog();
            }
        });
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoalUjianDetail soalUjianDetail = new SoalUjianDetail();
                soalUjianDetail.setIdSoalUjian(idSoalUjian.getText().toString());
                soalUjianDetail.setIdJenisSoalUjianDetail(idJenisSoalUjianDetail.getText().toString());
                soalUjianDetail.setSoalTulisan(soalTulisan.getText().toString());
                List<String> listSoalGambar = new ArrayList<>();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ((BitmapDrawable) soalGambar.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                listSoalGambar.add(encodedImage);
                soalUjianDetail.setSoalGambar(listSoalGambar);

                Call<SoalUjianDetailResponse> call = apiInterface.tambahSoalUjianDetail(soalUjianDetail);
                call.enqueue(new Callback<SoalUjianDetailResponse>() {
                    @Override
                    public void onResponse(Call<SoalUjianDetailResponse> call, Response<SoalUjianDetailResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<SoalUjianDetailResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void showSoalGambarPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chooseSoalGambarFromGallery();
                                break;
                            case 1:
                                takeSoalGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void chooseSoalGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_SOAL_GAMBAR);
    }

    private void takeSoalGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_SOAL_GAMBAR);
    }

    private void showPilihanJawabanAGambarPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePilihanJawabanAGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanAGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanAGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_A_GAMBAR);
    }

    private void takePilihanJawabanAGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_A_GAMBAR);
    }

    private void showPilihanJawabanBGambarPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePilihanJawabanBGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanBGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanBGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_B_GAMBAR);
    }

    private void takePilihanJawabanBGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_B_GAMBAR);
    }

    private void showPilihanJawabanCGambarPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePilihanJawabanCGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanCGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanCGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_C_GAMBAR);
    }

    private void takePilihanJawabanCGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_C_GAMBAR);
    }

    private void showPilihanJawabanDGambarPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePilihanJawabanDGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanDGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanDGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_D_GAMBAR);
    }

    private void takePilihanJawabanDGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_D_GAMBAR);
    }

    private void showPilihanJawabanEGambarPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePilihanJawabanEGambarFromGallery();
                                break;
                            case 1:
                                takePilihanJawabanEGambarFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePilihanJawabanEGambarFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY_PILIHAN_JAWABAN_E_GAMBAR);
    }

    private void takePilihanJawabanEGambarFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PILIHAN_JAWABAN_E_GAMBAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY_SOAL_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    soalGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_SOAL_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            soalGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY_PILIHAN_JAWABAN_A_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    pilihanJawabanAGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_PILIHAN_JAWABAN_A_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pilihanJawabanAGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY_PILIHAN_JAWABAN_B_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    pilihanJawabanBGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_PILIHAN_JAWABAN_B_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pilihanJawabanBGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY_PILIHAN_JAWABAN_C_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    pilihanJawabanCGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_PILIHAN_JAWABAN_D_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pilihanJawabanDGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY_PILIHAN_JAWABAN_E_GAMBAR) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    pilihanJawabanEGambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA_PILIHAN_JAWABAN_E_GAMBAR) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pilihanJawabanEGambar.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
}
