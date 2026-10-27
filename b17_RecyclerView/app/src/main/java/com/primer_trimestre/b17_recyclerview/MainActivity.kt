package com.primer_trimestre.b17_recyclerview

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.primer_trimestre.b17_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val misTextos = mutableListOf("Canadá", "Zimbabue", "Afganistán", "Rusia", "Serbia","Colombia","Venezuela","Suiza","Polonia","Angola")

        lateinit var adapter: TextosAdapter
        adapter = TextosAdapter(misTextos) { textoBorrado ->
            val index = misTextos.indexOf(textoBorrado)
            if (index != -1) {
                misTextos.removeAt(index)
                adapter.notifyItemRemoved(index)
            }
        }

        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.adapter = adapter
        binding.Button.setOnClickListener {
            val nuevoTexto = binding.EditText.text.toString()
            if (nuevoTexto.isNotBlank()) {
                misTextos.add(nuevoTexto)
                adapter.notifyItemInserted(misTextos.size - 1)
                binding.EditText.text.clear()
            }
        }

        binding.Button.setOnClickListener {
            val nuevoTexto = binding.EditText.text.toString().trim()
            if (nuevoTexto.isNotBlank()) {
                misTextos.add(nuevoTexto)
                val nuevaPosicion = misTextos.size - 1
                adapter.notifyItemInserted(nuevaPosicion)
                binding.RecyclerView.scrollToPosition(nuevaPosicion)
                binding.EditText.text.clear()
            }
        }




    }
}
