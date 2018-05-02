package com.sekolahguru.folcotandiono.sekolahguru.api;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.IP_ADDRESS;
import static com.sekolahguru.folcotandiono.sekolahguru.LoginActivity.LOGIN;

/**
 * Created by folcotandiono on 19/04/2018.
 */

public class ApiClient {

    private static Retrofit retrofit = null;
    public static String BASE_IP_ADDRESS = "192.168.43.166";
    private static String BASE_URL;

    private static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static Retrofit getClient(Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String ipAddress = "";

        sharedPreferences = context.getSharedPreferences(LOGIN, 0);
        BASE_IP_ADDRESS = sharedPreferences.getString(IP_ADDRESS, null);

        BASE_URL = "http://" + BASE_IP_ADDRESS + "/sekolah/index.php/";

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

}
