package com.example.mobilodevv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EkleRehber extends AppCompatActivity {

    EditText etAdveSoyad;
    EditText etNumara;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ekle_rehber);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAdveSoyad = findViewById(R.id.etAdveSoyad);
        etNumara = findViewById(R.id.etNumara);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database db = new database(EkleRehber.this);

                Kisi kisi = new Kisi();
                kisi.setAdsoyad(etAdveSoyad.getText().toString());
                kisi.setTelefonno(etNumara.getText().toString());

                db.Kisiekle(kisi);
                Toast.makeText(EkleRehber.this, "Kişi kaydedildi", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
