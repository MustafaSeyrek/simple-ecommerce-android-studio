package com.mustafaseyrek.mobiroller.model;

import java.util.Date;
import java.util.UUID;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class UrunEkleme {
    DatabaseReference databaseReference;

    public UrunEkleme() {
    }

    public void urunEkle(String kategori, String baslik, double fiyat, String aciklama) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Date tarih = new Date();
        UUID id = UUID.randomUUID();
        databaseReference.child("urunler").push().setValue(
                new Urunler(String.valueOf(id),kategori, baslik, fiyat, tarih.toString(), aciklama)
        );
    }
}
