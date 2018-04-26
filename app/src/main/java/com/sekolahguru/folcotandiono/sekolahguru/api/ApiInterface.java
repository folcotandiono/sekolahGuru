package com.sekolahguru.folcotandiono.sekolahguru.api;

import com.sekolahguru.folcotandiono.sekolahguru.model.Guru;
import com.sekolahguru.folcotandiono.sekolahguru.model.GuruLoginResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.JadwalUjian;
import com.sekolahguru.folcotandiono.sekolahguru.model.JadwalUjianResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.JadwalUjianTambahResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.JenisSoalUjianDetailResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaranResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.MateriPelajaran;
import com.sekolahguru.folcotandiono.sekolahguru.model.MateriPelajaranResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.MateriPelajaranTambahResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.PR;
import com.sekolahguru.folcotandiono.sekolahguru.model.PRResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.PRTambahResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjian;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetail;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetailResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetailTambahResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianTambahResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by folcotandiono on 19/04/2018.
 */

public interface ApiInterface {

    @POST("guru/login")
    Call<GuruLoginResponse> login(@Body Guru guru);

    @GET("guru/data_mata_pelajaran")
    Call<MataPelajaranResponse> getDataMataPelajaran(@QueryMap Map<String, String> param);

    @GET("guru/data_soal_ujian")
    Call<SoalUjianResponse> getDataSoalUjian(@QueryMap Map<String, String> param);

    @GET("guru/data_soal_ujian_detail")
    Call<SoalUjianDetailResponse> getDataSoalUjianDetail(@QueryMap Map<String, String> param);

    @GET("guru/data_jenis_soal_ujian_detail")
    Call<JenisSoalUjianDetailResponse> getDataJenisSoalUjianDetail(@QueryMap Map<String, String> param);

    @GET("guru/data_jadwal_ujian")
    Call<JadwalUjianResponse> getDataJadwalUjian(@QueryMap Map<String, String> param);

    @GET("guru/data_pr")
    Call<PRResponse> getDataPR(@QueryMap Map<String, String> param);

    @GET("guru/data_materi_pelajaran")
    Call<MateriPelajaranResponse> getDataMateriPelajaran(@QueryMap Map<String, String> param);

    @POST("guru/tambah_soal_ujian")
    Call<SoalUjianTambahResponse> tambahSoalUjian(@Body SoalUjian soalUjian);

    @POST("guru/tambah_soal_ujian_detail")
    Call<SoalUjianDetailTambahResponse> tambahSoalUjianDetail(@Body SoalUjianDetail soalUjianDetail);

    @POST("guru/tambah_jadwal_ujian")
    Call<JadwalUjianTambahResponse> tambahJadwalUjian(@Body JadwalUjian jadwalUjian);

    @POST("guru/tambah_pr")
    Call<PRTambahResponse> tambahPR(@Body PR pr);

    @POST("guru/tambah_materi_pelajaran")
    Call<MateriPelajaranTambahResponse> tambahMateriPelajaran(@Body MateriPelajaran materiPelajaran);

}
