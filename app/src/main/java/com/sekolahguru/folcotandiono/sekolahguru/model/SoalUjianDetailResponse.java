package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by folcotandiono on 20/04/2018.
 */

public class SoalUjianDetailResponse {
    @SerializedName("list_soal_ujian_detail")
    private List<SoalUjianDetailGet> listSoalUjianDetail;

    public List<SoalUjianDetailGet> getListSoalUjianDetail() {
        return listSoalUjianDetail;
    }

    public void setListSoalUjianDetail(List<SoalUjianDetailGet> listSoalUjianDetail) {
        this.listSoalUjianDetail = listSoalUjianDetail;
    }
}
