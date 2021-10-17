package com.mustafaseyrek.mobiroller.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mustafaseyrek.mobiroller.R;
import com.mustafaseyrek.mobiroller.detay.DetayActivity;
import com.mustafaseyrek.mobiroller.ekle.EkleActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    LayoutInflater layoutInflater;
    ArrayList<ListItem> list = new ArrayList<>();
    Adapter adapter;
    ListView listView;
    DatabaseReference databaseReference;
    Button btnUrunEkle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listView = findViewById(R.id.listView);
        adapter = new Adapter();
        listView.setAdapter(adapter);
        btnUrunEkle = findViewById(R.id.btnEkleMain);
        //verileri okuma kısmı
        databaseReference.child("urunler").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    list.add(new ListItem(snapshot1.child("id").getValue().toString(),
                            snapshot1.child("kategori").getValue().toString(),
                                    snapshot1.child("baslik").getValue().toString(),
                                    snapshot1.child("fiyat").getValue().toString()
                            )
                    );
                }

                Collections.sort(list, new Comparator<ListItem>() {
                    @Override
                    public int compare(ListItem o1, ListItem o2) {
                        return o1.getBaslik().toLowerCase().compareTo(o2.getBaslik().toLowerCase());
                    }
                });
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetayActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });



        btnUrunEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EkleActivity.class);
                startActivity(intent);
            }
        });

    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(convertView == null){
                view = layoutInflater.inflate(R.layout.activity_urun_row,null);
            }
            TextView txtKategori = view.findViewById(R.id.txtKategori);
            txtKategori.setText(list.get(position).getKategori());

            TextView txtBaslik = view.findViewById(R.id.txtBaslik);
            txtBaslik.setText(list.get(position).getBaslik());

            TextView txtFiyat = view.findViewById(R.id.txtFiyat);
            txtFiyat.setText(list.get(position).getFiyat());
           return view;
        }
    }
    class ListItem{
        String id;
        String kategori;
        String baslik;
        String fiyat;

        public ListItem(String id, String kategori, String baslik, String fiyat) {
            this.id = id;
            this.kategori = kategori;
            this.baslik = baslik;
            this.fiyat = fiyat;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }
}
