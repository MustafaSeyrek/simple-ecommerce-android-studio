package com.mustafaseyrek.mobiroller.detay;

import android.util.Log;

import com.mustafaseyrek.mobiroller.model.UrunDetay;

import java.util.ArrayList;

public class DetayPresenter implements DetayContract.Presenter {
    DetayContract.View view;


    public DetayPresenter(DetayContract.View view) {
        this.view = view;
    }

    @Override
    public ArrayList<String> detayGoster(String key) {
        UrunDetay urunDetay = new UrunDetay(key);
       /* urunDetay.detayGoster(key);
        ArrayList <String> list = new ArrayList<>();
        list =  urunDetay.getList();
        Log.i("i","="+list.size());
        return urunDetay.getList();*/
       return urunDetay.detayGoster(key);
    }
}
