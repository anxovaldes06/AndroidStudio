package com.primer_trimestre.b10_activityswitcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView tv1 = findViewById(R.id.textView3);
        TextView tv2 = findViewById(R.id.textView4);

        String num1 = getIntent().getStringExtra("num1");
        String num2 = getIntent().getStringExtra("num2");

        tv1.setText(num1);
        tv2.setText(num2);


        findViewById(R.id.button).setOnClickListener(v -> {

            int n1 = Integer.parseInt(num1);
            int n2 = Integer.parseInt(num2);

            int suma = n1 + n2;

            Intent intent = new Intent(SecondActivity.this, MainActivity.class);

            intent.putExtra("resultado", String.valueOf(suma));

            startActivity(intent);
        });
    }
}