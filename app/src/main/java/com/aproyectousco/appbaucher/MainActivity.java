package com.aproyectousco.appbaucher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText precioEditText;
    private Button btnGenerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        precioEditText = findViewById(R.id.precio);
        btnGenerar = findViewById(R.id.btnGenerar);

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String precioStr = precioEditText.getText().toString().trim();

                if (!precioStr.isEmpty()) {
                    // Convertir el precio a un número, si es necesario
                    Intent intent = new Intent(MainActivity.this, Baucher.class);
                    intent.putExtra("PRECIO", precioStr); // Enviar el precio como extra
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, ingrese un valor", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}