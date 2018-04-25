package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by folcotandiono on 4/25/2018.
 */

public class JadwalUjianResponse {
    @SerializedName("list_jadwal_ujian")
    private List<JadwalUjian> listJadwalUjian;

    public List<JadwalUjian> getListJadwalUjian() {
        return listJadwalUjian;
    }

    public void setListJadwalUjian(List<JadwalUjian> listJadwalUjian) {
        this.listJadwalUjian = listJadwalUjian;
    }
}
