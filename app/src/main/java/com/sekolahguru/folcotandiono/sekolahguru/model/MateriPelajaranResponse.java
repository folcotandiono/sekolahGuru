package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MateriPelajaranResponse {
    @SerializedName("list_materi_pelajaran")
    private List<MateriPelajaranGet> listMateriPelajaran;

    public List<MateriPelajaranGet> getListMateriPelajaran() {
        return listMateriPelajaran;
    }

    public void setListMateriPelajaran(List<MateriPelajaranGet> listMateriPelajaran) {
        this.listMateriPelajaran = listMateriPelajaran;
    }
}
