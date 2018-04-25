package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by folcotandiono on 4/25/2018.
 */

public class PRResponse {
    @SerializedName("list_pr")
    public List<PR> listPR;

    public List<PR> getListPR() {
        return listPR;
    }

    public void setListPR(List<PR> listPR) {
        this.listPR = listPR;
    }
}
