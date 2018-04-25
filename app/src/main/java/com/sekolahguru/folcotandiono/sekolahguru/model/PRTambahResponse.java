package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by folcotandiono on 4/25/2018.
 */

public class PRTambahResponse {
    @SerializedName("list_pr")
    public List<PR> listPr;

    public List<PR> getListPr() {
        return listPr;
    }

    public void setListPr(List<PR> listPr) {
        this.listPr = listPr;
    }
}
