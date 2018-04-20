package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by folcotandiono on 20/04/2018.
 */

public class SoalUjianTambahResponse {
    @SerializedName("list_soal_ujian")
    private List<SoalUjian> listSoalUjian;

    public List<SoalUjian> getListSoalUjian() {
        return listSoalUjian;
    }

    public void setListSoalUjian(List<SoalUjian> listSoalUjian) {
        this.listSoalUjian = listSoalUjian;
    }
}
