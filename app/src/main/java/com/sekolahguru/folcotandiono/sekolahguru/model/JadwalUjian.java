package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by folcotandiono on 4/25/2018.
 */

public class JadwalUjian {
    @SerializedName("id")
    private String id;
    @SerializedName("id_soal_ujian")
    private String idSoalUjian;
    @SerializedName("nama_soal_ujian")
    private String namaSoalUjian;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("nama")
    private String nama;
    @SerializedName("durasi")
    private String durasi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSoalUjian() {
        return idSoalUjian;
    }

    public void setIdSoalUjian(String idSoalUjian) {
        this.idSoalUjian = idSoalUjian;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getNamaSoalUjian() {
        return namaSoalUjian;
    }

    public void setNamaSoalUjian(String namaSoalUjian) {
        this.namaSoalUjian = namaSoalUjian;
    }
}
