package com.mustafaseyrek.mobiroller.detay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mustafaseyrek.mobiroller.R;
import com.mustafaseyrek.mobiroller.duzenle.DuzenleActivity;
import com.mustafaseyrek.mobiroller.main.MainActivity;

import java.util.ArrayList;

public class DetayActivity extends AppCompatActivity implements DetayContract.View {

    DetayContract.Presenter presenter;
    String key;//= "ebd260a1-c6c5-412c-a89a-18f74089e087";
    TextView txtKategori, txtBaslik, txtFiyat, txtTarih, txtAciklama;
    Button btnGeriDon, btnSil, btnDuzenle;
    DatabaseReference databaseReference;
    ArrayList <String> list = new ArrayList<>();
    String silinecekKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        presenter = new DetayPresenter(this);
        txtKategori = findViewById(R.id.txtKategori);
        txtBaslik = findViewById(R.id.txtBaslik);
        txtFiyat = findViewById(R.id.txtFiyat);
        txtTarih = findViewById(R.id.txtTarih);
        txtAciklama = findViewById(R.id.txtAciklama);
        btnGeriDon = findViewById(R.id.btnGeriDon);
        btnSil = findViewById(R.id.btnSil);
        btnDuzenle = findViewById(R.id.btnDuzenle);

        key = getIntent().getExtras().getString("id");
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
                    silinecekKey = snapshot1.getKey();
                }
                txtKategori.setText(list.get(0).toString());
                txtBaslik.setText(list.get(1).toString());
                txtFiyat.setText(list.get(2).toString());
                txtTarih.setText(list.get(3).toString());
                txtAciklama.setText(list.get(4).toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnGeriDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("urunler").child(silinecekKey).removeValue();
                Toast.makeText(DetayActivity.this, "Ürün silindi!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetayActivity.this, DuzenleActivity.class);
                intent.putExtra("id",key);
                intent.putExtra("key",silinecekKey);
                startActivity(intent);
            }
        });

       /* if (!TextUtils.isEmpty(key)) {
            list = presenter.detayGoster(key);
            if(list.size()!=0) {
                txtKategori.setText(list.get(0).toString());
                txtBaslik.setText(list.get(1).toString());
                txtFiyat.setText(list.get(2).toString());
                txtTarih.setText(list.get(3).toString());
                txtAciklama.setText(list.get(4).toString());
            }
            else {
                Toast.makeText(this, "hata", Toast.LENGTH_LONG).show();
            }
        }*/
    }
}
