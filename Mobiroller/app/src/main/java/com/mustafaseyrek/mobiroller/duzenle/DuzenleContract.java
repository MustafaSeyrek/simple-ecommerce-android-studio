package com.mustafaseyrek.mobiroller.duzenle;

public interface DuzenleContract {
    interface View {
    }

    interface Presenter {
        void urunDuzenle(String kategori, String baslik, double fiyat, String aciklama,String key);
    }
}
