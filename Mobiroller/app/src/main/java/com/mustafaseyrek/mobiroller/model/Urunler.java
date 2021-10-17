package com.mustafaseyrek.mobiroller.model;


public class Urunler {

    public String id;
    public String kategori;
    public String baslik;
    public double fiyat;
    public String tarih;
    public String aciklama;

    public Urunler(String id, String kategori, String baslik, double fiyat, String tarih, String aciklama) {
        this.id = id;
        this.kategori = kategori;
        this.baslik = baslik;
        this.fiyat = fiyat;
        this.tarih = tarih;
        this.aciklama = aciklama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
