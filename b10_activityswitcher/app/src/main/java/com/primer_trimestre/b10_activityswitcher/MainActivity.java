package com.primer_trimestre.b10_activityswitcher;

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

        findViewById(R.id.button).setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            intent.putExtra("num1", et1.getText().toString());
            intent.putExtra("num2", et2.getText().toString());

            startActivity(intent);
        });

        TextView resultado = findViewById(R.id.resultado);

        Intent intent = getIntent();

        if(intent.hasExtra("resultado")){
            resultado.setText(intent.getStringExtra("resultado"));
        }


    }
}