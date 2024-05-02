package com.example.mobilodevv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ListRehber extends AppCompatActivity {
    ListView rehberlist;
    Button eklerehber;
    database db;
    Button sil ;
    Button edit;

    Kisi kisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database db= new database(ListRehber.this);
        rehberlist = findViewById(R.id.rehberlist);
        eklerehber = findViewById(R.id.eklerehber);
        sil = findViewById(R.id.silBtn);
        edit = findViewById(R.id.editBtn);

        List<Kisi> kisilerListesi = db.Kisilistele();
        ArrayList<String> kisilerStringListesi = new ArrayList<>();
        for (Kisi kisi : kisilerListesi) {
            String test = "\n【Kişi adı】: " + kisi.getTelefonno() + "\n" + "【Kişi numarası】: " + kisi.getAdsoyad()+"\n";
            kisilerStringListesi.add(test);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListRehber.this, android.R.layout.simple_list_item_1, kisilerStringListesi);
        rehberlist.setAdapter(adapter);
        sil.setOnClickListener(new View.OnClickListener() {

            Integer i=0;
            public void onClick(View v) {

                Integer boyut= kisilerListesi.size();
                if (boyut > 0) { // Boyut 0'dan büyükse işlem yapılacak
                    db.Kisisil(i); // Verilen id'ye sahip kaydı sil
                     i++;
                    List<Kisi> kisilerListesi = db.Kisilistele();
                    ArrayList<String> kisilerStringListesi = new ArrayList<>();
                    for (Kisi kisi : kisilerListesi) {
                        String test = "\n【Kişi adı】: " + kisi.getTelefonno() + "\n" + "【Kişi numarası】: " + kisi.getAdsoyad()+"\n";
                        kisilerStringListesi.add(test);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ListRehber.this, android.R.layout.simple_list_item_1, kisilerStringListesi);
                    rehberlist.setAdapter(adapter);

                }
            }
            });

        rehberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Guncelle kt = new Guncelle();
                Object item = parent.getItemAtPosition(position);
                if (item instanceof String) {
                    String secilenItem = (String) item;
                    Log.d("Item Content", "Secilen item icerigi: " + secilenItem);
                } else {
                    Log.e("Error", "Unexpected item type: " + item.getClass().getSimpleName());
                }



            }
        });



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Kisi> kisilerListesi = db.Kisilistele();
                ArrayList<String> kisilerStringListesi = new ArrayList<>();
                for (Kisi kisi : kisilerListesi) {

                    String test ="\n【Kişi adı】: " + kisi.getTelefonno() + "\n" + "【Kişi numarası】: " + kisi.getAdsoyad()+"\n";
                    kisilerStringListesi.add(test);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ListRehber.this, android.R.layout.simple_list_item_1, kisilerStringListesi);
                rehberlist.setAdapter(adapter);

            }
        });


        eklerehber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListRehber.this, EkleRehber.class);
                startActivity(i);
            }
        });


    }

}
