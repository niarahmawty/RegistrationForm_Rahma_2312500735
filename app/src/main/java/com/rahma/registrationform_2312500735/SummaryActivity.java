package com.rahma.registrationform_2312500735;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    private TextView tvNIM, tvNama, tvEmail, tvPhone, tvJurusan, tvFakultas;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Initialize TextViews
        tvNIM = findViewById(R.id.tvNIM);
        tvNama = findViewById(R.id.tvNama);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvJurusan = findViewById(R.id.tvJurusan);
        tvFakultas = findViewById(R.id.tvFakultas);

        // Initialize the Back Button
        btnBack = findViewById(R.id.btnBack);

        // Get the data from Intent
        Intent intent = getIntent();
        String nim = intent.getStringExtra("nim");
        String nama = intent.getStringExtra("nama");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        String jurusan = intent.getStringExtra("jurusan");
        String fakultas = intent.getStringExtra("fakultas");

        // Set the data to TextViews
        tvNIM.setText("NIM: " + nim);
        tvNama.setText("Nama: " + nama);
        tvEmail.setText("Email: " + email);
        tvPhone.setText("Phone: " + phone);
        tvJurusan.setText("Jurusan: " + jurusan);
        tvFakultas.setText("Fakultas: " + fakultas);

        // Set onClickListener for Email TextView
        tvEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
            startActivity(Intent.createChooser(emailIntent, "Choose an Email client"));
        });

        // Set onClickListener for Phone TextView
        tvPhone.setOnClickListener(v -> {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            startActivity(phoneIntent);
        });

        // Set the Back button click listener
        btnBack.setOnClickListener(v -> {
            // Navigate back to MainActivity
            Intent backIntent = new Intent(SummaryActivity.this, MainActivity.class);
            startActivity(backIntent);
            finish(); // Optionally, you can call finish() to remove the SummaryActivity from the back stack
        });
    }
}
