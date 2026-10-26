package com.primer_trimestre.b15_calendarpicker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main),
            OnApplyWindowInsetsListener { v: View, insets: WindowInsetsCompat ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
                )
                insets
            }
        )

        val calendario = findViewById<CalendarView>(R.id.calendario)
        val txtFecha = findViewById<EditText>(R.id.editText)
        val txtFecha2 = findViewById<EditText>(R.id.editText2)
        val btnSetear = findViewById<Button>(R.id.button)

        calendario.setOnDateChangeListener { _, year, month, dayOfMonth ->

            txtFecha.setText("$dayOfMonth/${month + 1}/$year")

            btnSetear.setOnClickListener {
                val textoFecha2 = txtFecha2.text.toString()
                val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                try {
                    val fecha = formato.parse(textoFecha2)

                    if (fecha != null) {
                        calendario.date = fecha.time
                    }

                } catch (e: Exception) {
                    txtFecha2.error = "Formato incorrecto"
                }
            }
        }
    }
}