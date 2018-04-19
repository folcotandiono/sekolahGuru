package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by folcotandiono on 19/04/2018.
 */

public class MataPelajaran {
    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("id_kelas")
    private String idKelas;
    @SerializedName("nama_kelas")
    private String namaKelas;
    @SerializedName("id_guru")
    private String idGuru;
    @SerializedName("nama_guru")
    private String namaGuru;

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

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(String idGuru) {
        this.idGuru = idGuru;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }
}
