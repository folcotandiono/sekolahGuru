package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by folcotandiono on 23/04/2018.
 */

public class JenisSoalUjianDetailResponse {
    @SerializedName("list_jenis_soal_ujian_detail")
    private List<JenisSoalUjianDetail> listJenisSoalUjianDetail;

    public List<JenisSoalUjianDetail> getListJenisSoalUjianDetail() {
        return listJenisSoalUjianDetail;
    }

    public void setListJenisSoalUjianDetail(List<JenisSoalUjianDetail> listJenisSoalUjianDetail) {
        this.listJenisSoalUjianDetail = listJenisSoalUjianDetail;
    }
}
