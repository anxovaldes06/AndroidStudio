package com.primer_trimestre.b02ciclodevida;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CicloVida";

    private TextView estados;
    private final ArrayList<String> historialEstados = new ArrayList<>();

    private void mostrarEstado(String estado) {

        Log.d(TAG, estado);

        historialEstados.add(estado);

        if (historialEstados.size() > 5) {
            historialEstados.remove(0);
        }

        StringBuilder texto = new StringBuilder();

        for (String e : historialEstados) {
            texto.append("[ ")
                    .append(e)
                    .append(" ]\n\n");
        }

        estados.setText(texto.toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        estados = findViewById(R.id.text2);

        mostrarEstado("onCreate()");

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars =
                            insets.getInsets(WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        mostrarEstado("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarEstado("onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mostrarEstado("onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mostrarEstado("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mostrarEstado("onStop()");
    }

}