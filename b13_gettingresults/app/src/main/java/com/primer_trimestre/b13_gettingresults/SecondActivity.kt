package com.primer_trimestre.b13_gettingresults

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.secondMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val hora = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

        val tvInfo = findViewById<TextView>(R.id.textViewSecondInfo)
        tvInfo.text = "Fecha: $fecha\nHora: $hora"

        val btnSend = findViewById<Button>(R.id.buttonSendResult)
        btnSend.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("FECHA", fecha)
                putExtra("HORA", hora)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}