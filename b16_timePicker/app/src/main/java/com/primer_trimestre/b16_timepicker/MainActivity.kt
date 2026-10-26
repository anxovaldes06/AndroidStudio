package com.primer_trimestre.b16_timepicker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        val textView = findViewById<TextView>(R.id.getTime)
        val putHour = findViewById<EditText>(R.id.editTextTime)
        val btnSetear = findViewById<Button>(R.id.button)

        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val hora = String.format("%02d:%02d", hourOfDay, minute)

            textView.text = hora
        }

        btnSetear.setOnClickListener {

            val horaTexto = putHour.text.toString()

            if (":" in horaTexto) {

                val partes = horaTexto.split(":")

                if (partes.size == 2) {

                    val hora = partes[0].toInt()
                    val minuto = partes[1].toInt()

                    timePicker.hour = hora
                    timePicker.minute = minuto

                    textView.text = horaTexto
                }
            }
        }
    }
}