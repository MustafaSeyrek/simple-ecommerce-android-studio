package com.mustafaseyrek.mobiroller.duzenle;

import com.mustafaseyrek.mobiroller.model.UrunDuzenle;

public class DuzenlePresenter implements DuzenleContract.Presenter{
    DuzenleContract.View view;
    UrunDuzenle urunDuzenle= new UrunDuzenle();

    public DuzenlePresenter(DuzenleContract.View view) {
        this.view = view;
    }

    @Override
    public void urunDuzenle(String kategori, String baslik, double fiyat, String aciklama,String key) {
        urunDuzenle.urunDuzenle( kategori,  baslik,  fiyat,  aciklama, key);
    }
}
