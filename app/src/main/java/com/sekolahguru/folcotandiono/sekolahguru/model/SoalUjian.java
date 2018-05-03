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
    @SerializedName("nama_mata_pelajaran")
    private String namaMataPelajaran;
    @SerializedName("nama")
    private String nama;

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

    public String getNamaMataPelajaran() {
        return namaMataPelajaran;
    }

    public void setNamaMataPelajaran(String namaMataPelajaran) {
        this.namaMataPelajaran = namaMataPelajaran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
