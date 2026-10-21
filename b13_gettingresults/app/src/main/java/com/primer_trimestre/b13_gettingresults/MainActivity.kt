package com.primer_trimestre.b13_gettingresults

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var getDateTimeLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getDateTimeLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val fecha = data?.getStringExtra("FECHA") ?: ""
                val hora = data?.getStringExtra("HORA") ?: ""

                val etDate = findViewById<EditText>(R.id.editTextDate)
                val etTime = findViewById<EditText>(R.id.editTextTime)
                val tvDateTime = findViewById<TextView>(R.id.datetime)

                etDate.setText(fecha)
                etTime.setText(hora)
                tvDateTime.text = "$fecha $hora"
            }
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            getDateTimeLauncher.launch(intent)
        }
    }
}