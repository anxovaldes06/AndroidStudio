package com.example.b18_SpinnerFutbol

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.graphics.Color;
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var adapter: SpinnerAdapter
    private lateinit var team: TextView
    private lateinit var crest: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        spinner = findViewById(R.id.spinner)
        team = findViewById(R.id.team)
        crest = findViewById(R.id.imageView)

        adapter = SpinnerAdapter(this, TeamRepository.teams)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccionado = parent.getItemAtPosition(position) as Team

                team.text = seleccionado.name
                crest.setImageResource(seleccionado.crest)
                when (seleccionado.name) {
                    "Racing Club de Ferrol" -> team.setTextColor(Color.GREEN)

                    "Real Club Celta de Vigo" -> team.setTextColor(Color.CYAN)

                    "CD Lugo" -> team.setTextColor(Color.RED)

                    "Deportivo da Coruña" -> team.setTextColor(getColor(R.color.depor_purple))

                    "Pontevedra FC" -> team.setTextColor(getColor(R.color.pontevedra_granate))

                    else -> team.setTextColor(Color.BLACK)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}