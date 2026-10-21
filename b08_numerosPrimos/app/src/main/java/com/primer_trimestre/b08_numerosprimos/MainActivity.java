package com.primer_trimestre.b08_numerosprimos;

import android.os.Bundle;
import android.widget.EditText;
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

        EditText editText = findViewById(R.id.first_edittext);
        TextView textView = findViewById(R.id.second_text);

        findViewById(R.id.first_button).setOnClickListener(v -> {
            try {
                int num = Integer.parseInt(editText.getText().toString().trim());
                if (num > 1000) {
                    textView.setText("false");
                } else {
                    textView.setText("¿Es primo? " + esPrimo(num) + "\nSuma de primos: " + sumaPrimos(num));
                }
            } catch (NumberFormatException e) {
                textView.setText("false");
            }
        });
    }

    public boolean esPrimo(int n) {
        if (n <= 1) return false;
        for (int i = 2;i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public int sumaPrimos(int limite) {
        int suma = 0;
        for (int i = 2; i <= limite; i++) {
            if (esPrimo(i)) suma += i;
        }
        return suma;
    }
}
