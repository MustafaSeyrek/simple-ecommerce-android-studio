package com.mustafaseyrek.mobiroller.ekle;



public interface EkleContract {
    interface View {
        void onSuccess(String mesaj);
        void onError(String mesaj);
    }

    interface Presenter {
        void urunEkle(String kategori, String baslik, double fiyat, String aciklama);
    }
}
