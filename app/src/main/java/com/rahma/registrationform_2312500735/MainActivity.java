package com.rahma.registrationform_2312500735;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etNIM, etNama, etEmail, etPhone, etJurusan;
    private Spinner spinnerFakultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etNIM = findViewById(R.id.etNIM);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etJurusan = findViewById(R.id.etJurusan);
        spinnerFakultas = findViewById(R.id.spinnerFakultas);

        // Fakultas Spinner setup
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fakultas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFakultas.setAdapter(adapter);

        // Register button click listener
        findViewById(R.id.btnRegister).setOnClickListener(v -> {
            String nim = etNIM.getText().toString();
            String nama = etNama.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String jurusan = etJurusan.getText().toString();
            String fakultas = spinnerFakultas.getSelectedItem().toString();

            // Email validation
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Please enter a valid email address");
                etEmail.requestFocus();
                return;  // Stop further execution if email is invalid
            }

            // Phone validation (only digits allowed)
            if (phone.isEmpty() || !phone.matches("[0-9]+")) {
                etPhone.setError("Phone number must contain only numbers");
                etPhone.requestFocus();
                return;  // Stop further execution if phone is invalid
            }

            // Jurusan validation (must be filled)
            if (jurusan.isEmpty()) {
                etJurusan.setError("Jurusan is required");
                etJurusan.requestFocus();
                return;  // Stop further execution if Jurusan is empty
            }

            // Proceed to next activity if all validations pass
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            intent.putExtra("nim", nim);
            intent.putExtra("nama", nama);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            intent.putExtra("jurusan", jurusan);
            intent.putExtra("fakultas", fakultas);
            startActivity(intent);
        });

        // Reset button click listener
        findViewById(R.id.btnReset).setOnClickListener(v -> {
            etNIM.setText("");
            etNama.setText("");
            etEmail.setText("");
            etPhone.setText("");
            etJurusan.setText("");
            spinnerFakultas.setSelection(0); // Reset spinner to first item
        });
    }
}
