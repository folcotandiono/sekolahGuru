package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by folcotandiono on 19/04/2018.
 */

public class MataPelajaranResponse {
    @SerializedName("list_mata_pelajaran")
    private List<MataPelajaran> listMataPelajaran;

    public List<MataPelajaran> getListMataPelajaran() {
        return listMataPelajaran;
    }

    public void setListMataPelajaran(List<MataPelajaran> listMataPelajaran) {
        this.listMataPelajaran = listMataPelajaran;
    }
}
