package com.mustafaseyrek.mobiroller.ekle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mustafaseyrek.mobiroller.R;
import com.mustafaseyrek.mobiroller.main.MainActivity;

public class EkleActivity extends AppCompatActivity implements EkleContract.View {

    EkleContract.Presenter presenter;
    EditText editKategori, editBaslik, editFiyat, editAciklama;
    Button btnEkle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);

        presenter = new EklePresenter(this);
        editKategori = findViewById(R.id.editKategori);
        editBaslik = findViewById(R.id.editBaslik);
        editFiyat = findViewById(R.id.editFiyat);
        editAciklama = findViewById(R.id.editAciklama);
        btnEkle = findViewById(R.id.btnEkle);

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double fiyat=0;
                String kategori = editKategori.getText().toString();
                String baslik = editBaslik.getText().toString();
                if (!TextUtils.isEmpty(editFiyat.getText().toString())) {
                     fiyat = Double.parseDouble(editFiyat.getText().toString());
                }
                String aciklama = editAciklama.getText().toString();
                if (TextUtils.isEmpty(kategori) || TextUtils.isEmpty(baslik) || TextUtils.isEmpty(editFiyat.getText().toString()) || TextUtils.isEmpty(aciklama)) {
                    onError("Tüm alanlar doldurulmalı!");
                } else {
                    presenter.urunEkle(kategori, baslik, fiyat, aciklama);
                    editFiyat.setText("");
                    editKategori.setText("");
                    editBaslik.setText("");
                    editAciklama.setText("");
                    onSuccess("Ürün başarıyla eklendi!");
                    Intent intent = new Intent(EkleActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onSuccess(String mesaj) {
        Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String mesaj) {
        Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
    }
}
