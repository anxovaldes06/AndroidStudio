package com.primer_trimestre.b06_primeratoast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mostrar el Toast al iniciar la Activity
        Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show();

        // Configurar el botón para mostrar el Toast al pulsar
        Button btnToast = findViewById(R.id.btn_toast);
        if (btnToast != null) {
            btnToast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
