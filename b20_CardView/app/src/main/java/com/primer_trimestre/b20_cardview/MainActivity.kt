package com.primer_trimestre.b20_cardview

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var recycler: RecyclerView? = null
    private var adapter: AnimeAdapter? = null
    private var lManager: RecyclerView.LayoutManager? = null
    private var repositorio: DataRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recycler = findViewById(R.id.recyclerView)
        recycler?.setHasFixedSize(true)

        lManager = LinearLayoutManager(this)
        recycler?.layoutManager = lManager

        repositorio = DataRepository()
        adapter = AnimeAdapter(repositorio!!.obtenerListaAnimes())
        recycler?.adapter = adapter

        val btnAnadir = findViewById<Button>(R.id.button)
        btnAnadir.setOnClickListener {
            val nuevoAnime = Anime(R.drawable.orugafumona, "Nuevo Anime", 0)
            adapter?.agregarAnime(nuevoAnime)
        }
    }
}
