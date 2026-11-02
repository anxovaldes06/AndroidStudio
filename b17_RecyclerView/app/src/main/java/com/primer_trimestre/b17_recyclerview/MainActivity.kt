package com.primer_trimestre.b17_recyclerview

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.primer_trimestre.b17_recyclerview.BuildConfig.API_KEY
import com.primer_trimestre.b17_recyclerview.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URI
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val misPaises = mutableListOf<Pais>()
    private lateinit var adapter: PaisesAdapter

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

        misPaises.add(Pais("Canada", "Ottawa", "https://flagcdn.com/w320/ca.png"))
        misPaises.add(Pais("Japan", "Tokyo", "https://flagcdn.com/w320/jp.png"))
        misPaises.add(Pais("Spain", "Madrid", "https://flagcdn.com/w320/es.png"))
        misPaises.add(Pais("Italy", "Rome", "https://flagcdn.com/w320/it.png"))

        adapter = PaisesAdapter(misPaises) { paisBorrado ->
            val index = misPaises.indexOf(paisBorrado)
            if (index != -1) {
                misPaises.removeAt(index)
                adapter.notifyItemRemoved(index)
                Toast.makeText(this, "Eliminado: ${paisBorrado.nombre}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.adapter = adapter

        binding.Button.setOnClickListener {
            val nombrePais = binding.EditText.text.toString().trim()

            if (nombrePais.isNotBlank()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val paisObtenido = consultarApiRestCountries(nombrePais)

                    withContext(Dispatchers.Main) {
                        if (paisObtenido != null) {
                            misPaises.add(paisObtenido)
                            val nuevaPosicion = misPaises.size - 1

                            adapter.notifyItemInserted(nuevaPosicion)
                            binding.RecyclerView.scrollToPosition(nuevaPosicion)
                            binding.EditText.text.clear()
                        } else {
                            Toast.makeText(this@MainActivity, "País no encontrado en la API", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Escribe el nombre de un país", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun consultarApiRestCountries(nombre: String): Pais? {
        var connection: HttpURLConnection? = null
        return try {
            val q = URLEncoder.encode(nombre.trim(), "UTF-8")
            val url = URI.create(
                "https://api.restcountries.com/countries/v5/name?q=$q" +
                        "&response_fields=names.common,capitals,codes.alpha_2"
            ).toURL()

            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.setRequestProperty("Authorization", "Bearer $API_KEY")
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode != 200) {
                Log.e("API", "HTTP ${connection.responseCode}: " +
                        connection.errorStream?.bufferedReader()?.readText())
                return null
            }

            val body = connection.inputStream.bufferedReader().use { it.readText() }
            val objects = JSONObject(body).getJSONObject("data").getJSONArray("objects")
            if (objects.length() == 0) return null

            var country = objects.getJSONObject(0)
            for (i in 0 until objects.length()) {
                val c = objects.getJSONObject(i)
                if (c.getJSONObject("names").getString("common")
                        .equals(nombre.trim(), ignoreCase = true)) {
                    country = c
                    break
                }
            }

            val paisNombre = country.getJSONObject("names").getString("common")

            val capitales = country.optJSONArray("capitals")
            val capitalNombre = if (capitales != null && capitales.length() > 0)
                capitales.getJSONObject(0).optString("name", "No capital")
            else "No capital"

            val cca2 = country.optJSONObject("codes")?.optString("alpha_2", "")?.lowercase().orEmpty()
            val urlBandera = "https://flags.restcountries.com/v5/w320/$cca2.png"

            Pais(paisNombre, capitalNombre, urlBandera)
        } catch (e: Exception) {
            Log.e("API", "Error consultando país", e)
            null
        } finally {
            connection?.disconnect()
        }
    }


}