package com.primer_trimestre.b17_recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
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

        val misTextos = mutableListOf(
            "🇨🇦 Canadá",
            "🇿🇼 Zimbabue",
            "🇦🇫 Afganistán",
            "🇷🇺 Rusia",
            "🇸🇷 Serbia",
            "🇨🇴 Colombia",
            "🇻🇪 Venezuela",
            "🇨🇭 Suiza",
            "🇵🇱 Polonia",
            "🇦🇴 Angola"
        )

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
            val nuevoTexto = binding.EditText.text.toString().trim()
            if (nuevoTexto.isNotBlank()) {
                val textoConBandera = if (nuevoTexto.any { Character.isSurrogate(it) }) {
                    nuevoTexto
                } else {
                    agregarBandera(nuevoTexto)
                }
                misTextos.add(textoConBandera)
                val nuevaPosicion = misTextos.size - 1
                adapter.notifyItemInserted(nuevaPosicion)
                binding.RecyclerView.scrollToPosition(nuevaPosicion)
                binding.EditText.text.clear()
            }
        }
    }

    private fun agregarBandera(pais: String): String {
        val nombreLimpio = pais.lowercase()
        val bandera = when {
            "canadá" in nombreLimpio || "canada" in nombreLimpio -> "🇨🇦"
            "zimbabue" in nombreLimpio || "zimbabwe" in nombreLimpio -> "🇿🇼"
            "afganistán" in nombreLimpio || "afganistan" in nombreLimpio -> "🇦🇫"
            "rusia" in nombreLimpio || "russia" in nombreLimpio -> "🇷🇺"
            "serbia" in nombreLimpio -> "🇸🇷"
            "colombia" in nombreLimpio -> "🇨🇴"
            "venezuela" in nombreLimpio -> "🇻🇪"
            "suiza" in nombreLimpio || "switzerland" in nombreLimpio -> "🇨🇭"
            "polonia" in nombreLimpio || "poland" in nombreLimpio -> "🇵🇱"
            "angola" in nombreLimpio -> "🇦🇴"
            "españa" in nombreLimpio || "espana" in nombreLimpio -> "🇪🇸"
            "méxico" in nombreLimpio || "mexico" in nombreLimpio -> "🇲🇽"
            "argentina" in nombreLimpio -> "🇦🇷"
            "brasil" in nombreLimpio || "brazil" in nombreLimpio -> "🇧🇷"
            "chile" in nombreLimpio -> "🇨🇱"
            "perú" in nombreLimpio || "peru" in nombreLimpio -> "🇵🇪"
            "francia" in nombreLimpio || "france" in nombreLimpio -> "🇫🇷"
            "alemania" in nombreLimpio || "germany" in nombreLimpio -> "🇩🇪"
            "italia" in nombreLimpio || "italy" in nombreLimpio -> "🇮🇹"
            "portugal" in nombreLimpio -> "🇵🇹"
            "estados unidos" in nombreLimpio || "eeuu" in nombreLimpio || "usa" in nombreLimpio -> "🇺🇸"
            "japón" in nombreLimpio || "japon" in nombreLimpio || "japan" in nombreLimpio -> "🇯🇵"
            "china" in nombreLimpio -> "🇨🇳"
            "reino unido" in nombreLimpio || "uk" in nombreLimpio -> "🇬🇧"
            else -> "🏳️"
        }
        return "$bandera $pais"
    }
}
