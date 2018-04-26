package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by folcotandiono on 4/25/2018.
 */

public class PRResponse {
    @SerializedName("list_pr")
    public List<PRGet> listPR;

    public List<PRGet> getListPR() {
        return listPR;
    }

    public void setListPR(List<PRGet> listPR) {
        this.listPR = listPR;
    }
}
