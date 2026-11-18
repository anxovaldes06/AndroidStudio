package com.primer_trimestre.b20_cardview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.primer_trimestre.b20_cardview.AnimeAdapter.AnimeViewHolder

class AnimeAdapter(private val items: MutableList<Anime?>) :
    RecyclerView.Adapter<AnimeViewHolder?>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AnimeViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.anime_card, viewGroup, false)
        return AnimeViewHolder(v)
    }



    override fun onBindViewHolder(viewHolder: AnimeViewHolder, i: Int) {
        val anime = items[i] ?: return

        viewHolder.imagen.setImageResource(anime.imagen)
        viewHolder.nombre.text = anime.nombre
        viewHolder.visitas.text = "Visitas: ${anime.visitas}"



        val compartirClickListener = View.OnClickListener { v ->
            val context = v.context
            val textoCompartir = "¡Hola! Te recomiendo el anime *${anime.nombre}* que tiene ${anime.visitas} visitas."

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Recomendación de Anime")
                putExtra(Intent.EXTRA_TEXT, textoCompartir)
            }

            val chooser = Intent.createChooser(intent, "Compartir anime usando:")
            context.startActivity(chooser)
        }

        viewHolder.btnCompartir.setOnClickListener(compartirClickListener)
        viewHolder.txtCompartir.setOnClickListener(compartirClickListener)
    }

    class AnimeViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imagen: ImageView = v.findViewById(R.id.imagen)
        var nombre: TextView = v.findViewById(R.id.nombre)
        var visitas: TextView = v.findViewById(R.id.visitas)

        var btnCompartir: ImageView = v.findViewById(R.id.compartir)
        var txtCompartir: TextView = v.findViewById(R.id.txtCompartir)
    }

    fun agregarAnime(anime: Anime) {
        items.add(anime)
        notifyItemInserted(items.size - 1)
    }
}