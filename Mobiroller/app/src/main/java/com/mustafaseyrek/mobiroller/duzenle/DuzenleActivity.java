package com.mustafaseyrek.mobiroller.duzenle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mustafaseyrek.mobiroller.R;
import com.mustafaseyrek.mobiroller.detay.DetayActivity;
import com.mustafaseyrek.mobiroller.ekle.EkleContract;
import com.mustafaseyrek.mobiroller.ekle.EklePresenter;
import com.mustafaseyrek.mobiroller.main.MainActivity;

public class DuzenleActivity extends AppCompatActivity implements DuzenleContract.View {

    DuzenleContract.Presenter presenter;
    EditText editKategori, editBaslik, editFiyat, editAciklama;
    Button btnDuzenle;
    String id, key;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duzenle);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        presenter = new DuzenlePresenter(this);
        editKategori = findViewById(R.id.editKategori);
        editBaslik = findViewById(R.id.editBaslik);
        editFiyat = findViewById(R.id.editFiyat);
        editAciklama = findViewById(R.id.editAciklama);
        btnDuzenle = findViewById(R.id.btnDuzenle);

        key = getIntent().getExtras().getString("key");
        id = getIntent().getExtras().getString("id");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("urunler").orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    editKategori.setText(snapshot1.child("kategori").getValue().toString());
                    editBaslik.setText(snapshot1.child("baslik").getValue().toString());
                    editFiyat.setText(snapshot1.child("fiyat").getValue().toString());
                    editAciklama.setText(snapshot1.child("aciklama").getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.urunDuzenle(editKategori.getText().toString(),editBaslik.getText().toString(),
                        Double.parseDouble(editFiyat.getText().toString()),editAciklama.getText().toString(),key);
                Toast.makeText(DuzenleActivity.this, "Ürün duzenlendi!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DuzenleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
