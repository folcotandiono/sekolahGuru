package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MateriPelajaran {
    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("id_mata_pelajaran")
    private String idMataPelajaran;
    @SerializedName("nama_mata_pelajaran")
    private String namaMataPelajaran;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("gambar")
    private List<String> gambar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdMataPelajaran() {
        return idMataPelajaran;
    }

    public void setIdMataPelajaran(String idMataPelajaran) {
        this.idMataPelajaran = idMataPelajaran;
    }

    public String getNamaMataPelajaran() {
        return namaMataPelajaran;
    }

    public void setNamaMataPelajaran(String namaMataPelajaran) {
        this.namaMataPelajaran = namaMataPelajaran;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public List<String> getGambar() {
        return gambar;
    }

    public void setGambar(List<String> gambar) {
        this.gambar = gambar;
    }
}
