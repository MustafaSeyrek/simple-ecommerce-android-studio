package com.mustafaseyrek.mobiroller.model;

import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;

public class UrunDetay {
    public DatabaseReference databaseReference;
    public ArrayList<String> list = new ArrayList<>();
    public String key;

    String kategori="";
    String baslik="";
    String fiyat="";
    String tarih="";
    String aciklama="";

    public UrunDetay(String key) {
        this.key = key;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("urunler").orderByChild("id").equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    kategori = snapshot1.child("kategori").getValue().toString();
                    baslik = snapshot1.child("baslik").getValue().toString();
                    fiyat = snapshot1.child("fiyat").getValue().toString();
                    tarih = snapshot1.child("tarih").getValue().toString();
                    aciklama = snapshot1.child("aciklama").getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /*public  ArrayList<String> veriCek(String key){
        urun u = new urun();
      databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("urunler").orderByChild("id").equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    list.add(0, snapshot1.child("kategori").getValue().toString());
                    list.add(1, snapshot1.child("baslik").getValue().toString());
                    list.add(2, snapshot1.child("fiyat").getValue().toString());
                    list.add(3, snapshot1.child("tarih").getValue().toString());
                    list.add(4, snapshot1.child("aciklama").getValue().toString());
                    u.myList.add(snapshot1.child("kategori").getValue().toString());
                    u.myList.add(snapshot1.child("baslik").getValue().toString());
                    u.myList.add( snapshot1.child("fiyat").getValue().toString());
                    u.myList.add(snapshot1.child("tarih").getValue().toString());
                    u.myList.add(snapshot1.child("aciklama").getValue().toString());
                }
                Log.i("i", "iç " + list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        u.myList.add("snapshot1.child(kategori).getValue().toString()");
        u.myList.add("snapshot1.child().getValue().toString()");
        u.myList.add( "snapshot1.child().getValue().toString()");
        u.myList.add("snapshot1.child().getValue().toString()");
        u.myList.add("snapshot1.child().getValue().toString()");
        return u.myList;
    }*/
    public ArrayList<String> detayGoster(String key) {
        list.add(0,kategori);
        list.add(1,baslik);
        list.add(2,fiyat);
        list.add(3,tarih);
        list.add(4,aciklama);
        return list;
    }

/*class urun{
        String kategori;
        String baslik;
        String fiyat;
        String tarih;
        String aciklama;

        ArrayList<String> myList = new ArrayList<>();

    public urun() {

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

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
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
}*/

}


