package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by folcotandiono on 19/04/2018.
 */

public class GuruLoginResponse {
    @SerializedName("list_guru")
    List<Guru> listGuru;

    public List<Guru> getListGuru() {
        return listGuru;
    }

    public void setListGuru(List<Guru> listGuru) {
        this.listGuru = listGuru;
    }
}
