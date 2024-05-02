package com.example.mobilodevv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Guncelle extends AppCompatActivity {
    TextView advesoyadd;
    TextView numarass;
    EditText etNumara;
    EditText etAdveSoyad;
    Button kaydetbtn;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.guncelle);
        advesoyadd = findViewById(R.id.advesoyadd);
        numarass = findViewById(R.id.numarass);
        etNumara = findViewById(R.id.etNumara);
        etAdveSoyad = findViewById(R.id.etAdveSoyad);
        kaydetbtn=findViewById(R.id.kaydetbtn);

        // Veritabanı bağlantısını oluştur
        database db = new database(this);

        // Önceki aktiviteden seçilen Kisi nesnesini al
        Intent intent = getIntent();
        Kisi secilenKisi = (Kisi) intent.getSerializableExtra("secilenKisi");

        // EditText öğelerini mevcut verilerle doldur
        etAdveSoyad.setText(secilenKisi.getAdsoyad());
        etNumara.setText(secilenKisi.getTelefonno());

        // Güncelleme butonuna tıklama dinleyicisi ekle
        kaydetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText öğelerinden güncel verileri al
                String yeniAdSoyad = etAdveSoyad.getText().toString().trim();
                String yeniTelefonNo = etNumara.getText().toString().trim();

                // Yeni verileri Kisi nesnesine ekle
                secilenKisi.setAdsoyad(yeniAdSoyad);
                secilenKisi.setTelefonno(yeniTelefonNo);

                // Kişiyi güncelle
                db.KisiGuncelle(secilenKisi);

                // Aktiviteyi kapat ve önceki aktiviteye geri dön
                finish();
            }
        });



    }

}
