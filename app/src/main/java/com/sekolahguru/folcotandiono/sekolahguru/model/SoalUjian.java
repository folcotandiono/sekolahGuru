package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by folcotandiono on 19/04/2018.
 */

public class SoalUjian {
    @SerializedName("id")
    private String id;
    @SerializedName("id_mata_pelajaran")
    private String idMataPelajaran;
    @SerializedName("nama")
    private String nama;
    @SerializedName("id_guru")
    private String idGuru;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMataPelajaran() {
        return idMataPelajaran;
    }

    public void setIdMataPelajaran(String idMataPelajaran) {
        this.idMataPelajaran = idMataPelajaran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(String idGuru) {
        this.idGuru = idGuru;
    }
}
