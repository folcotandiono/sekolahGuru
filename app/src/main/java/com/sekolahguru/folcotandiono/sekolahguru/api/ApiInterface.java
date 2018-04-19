package com.sekolahguru.folcotandiono.sekolahguru.api;

import com.sekolahguru.folcotandiono.sekolahguru.model.Guru;
import com.sekolahguru.folcotandiono.sekolahguru.model.GuruLoginResponse;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaranResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
}
