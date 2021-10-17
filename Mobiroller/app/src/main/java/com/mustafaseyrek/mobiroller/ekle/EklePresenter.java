package com.mustafaseyrek.mobiroller.ekle;


import com.mustafaseyrek.mobiroller.model.UrunEkleme;

public class EklePresenter implements EkleContract.Presenter {

    EkleContract.View view;
    UrunEkleme urunEkleme = new UrunEkleme();

    public EklePresenter(EkleContract.View view) {
        this.view = view;
    }

    @Override
    public void urunEkle(String kategori, String baslik, double fiyat, String aciklama) {
        urunEkleme.urunEkle(kategori, baslik, fiyat, aciklama);
    }
}
