package com.primer_trimestre.b12_calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



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

        EditText et1 = findViewById(R.id.editTextNumber);
        EditText et2 = findViewById(R.id.editTextNumber2);
        TextView resultado = findViewById(R.id.resultado);

        double numero1 = Double.parseDouble(et1.getText().toString());
        double numero2 = Double.parseDouble(et2.getText().toString());
        double producto = numero1 * numero2;
        double resta = numero1 - numero2;
        double suma = numero1 + numero2;
        double division = numero1 / numero2;

        findViewById(R.id.buttonMultiplicar).setOnClickListener(v -> {
            resultado.setText(String.valueOf(producto));

        });
        findViewById(R.id.buttonDividir).setOnClickListener(v -> {
            resultado.setText(String.valueOf(division));

        });
        findViewById(R.id.buttonRestar).setOnClickListener(v -> {
            resultado.setText(String.valueOf(resta));

        });
        findViewById(R.id.buttonSumar).setOnClickListener(v -> {
            resultado.setText(String.valueOf(suma));

        });

    }
}