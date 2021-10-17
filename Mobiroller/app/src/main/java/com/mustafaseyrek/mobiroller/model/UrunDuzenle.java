package com.mustafaseyrek.mobiroller.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class UrunDuzenle {
    DatabaseReference databaseReference;
    public UrunDuzenle(){

    }

    public void urunDuzenle(String kategori, String baslik, double fiyat, String aciklama,String key){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Date tarih = new Date();
        databaseReference.child("urunler").child(key).child("kategori").setValue(kategori);
        databaseReference.child("urunler").child(key).child("baslik").setValue(baslik);
        databaseReference.child("urunler").child(key).child("fiyat").setValue(fiyat);
        databaseReference.child("urunler").child(key).child("tarih").setValue(tarih.toString());
        databaseReference.child("urunler").child(key).child("aciklama").setValue(aciklama);
    }
}
