package com.sekolahguru.folcotandiono.sekolahguru.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by folcotandiono on 20/04/2018.
 */

public class SoalUjianDetailGet {
    @SerializedName("id")
    private String id;
    @SerializedName("id_soal_ujian")
    private String idSoalUjian;
    @SerializedName("nama_soal_ujian")
    private String namaSoalUjian;
    @SerializedName("id_jenis_soal_ujian_detail")
    private String idJenisSoalUjianDetail;
    @SerializedName("nama_jenis_soal_ujian_detail")
    private String namaJenisSoalUjianDetail;
    @SerializedName("soal_tulisan")
    private String soalTulisan;
    @SerializedName("soal_gambar")
    private String soalGambar;
    @SerializedName("pilihan_jawaban_tulisan")
    private String pilihanJawabanTulisan;
    @SerializedName("pilihan_jawaban_gambar")
    private String pilihanJawabanGambar;
    @SerializedName("kunci_jawaban")
    private String kunciJawaban;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSoalUjian() {
        return idSoalUjian;
    }

    public void setIdSoalUjian(String idSoalUjian) {
        this.idSoalUjian = idSoalUjian;
    }

    public String getNamaSoalUjian() {
        return namaSoalUjian;
    }

    public void setNamaSoalUjian(String namaSoalUjian) {
        this.namaSoalUjian = namaSoalUjian;
    }

    public String getIdJenisSoalUjianDetail() {
        return idJenisSoalUjianDetail;
    }

    public void setIdJenisSoalUjianDetail(String idJenisSoalUjianDetail) {
        this.idJenisSoalUjianDetail = idJenisSoalUjianDetail;
    }

    public String getNamaJenisSoalUjianDetail() {
        return namaJenisSoalUjianDetail;
    }

    public void setNamaJenisSoalUjianDetail(String namaJenisSoalUjianDetail) {
        this.namaJenisSoalUjianDetail = namaJenisSoalUjianDetail;
    }

    public String getSoalTulisan() {
        return soalTulisan;
    }

    public void setSoalTulisan(String soalTulisan) {
        this.soalTulisan = soalTulisan;
    }

    public String getSoalGambar() {
        return soalGambar;
    }

    public void setSoalGambar(String soalGambar) {
        this.soalGambar = soalGambar;
    }

    public String getPilihanJawabanTulisan() {
        return pilihanJawabanTulisan;
    }

    public void setPilihanJawabanTulisan(String pilihanJawabanTulisan) {
        this.pilihanJawabanTulisan = pilihanJawabanTulisan;
    }

    public String getPilihanJawabanGambar() {
        return pilihanJawabanGambar;
    }

    public void setPilihanJawabanGambar(String pilihanJawabanGambar) {
        this.pilihanJawabanGambar = pilihanJawabanGambar;
    }

    public String getKunciJawaban() {
        return kunciJawaban;
    }

    public void setKunciJawaban(String kunciJawaban) {
        this.kunciJawaban = kunciJawaban;
    }
}
