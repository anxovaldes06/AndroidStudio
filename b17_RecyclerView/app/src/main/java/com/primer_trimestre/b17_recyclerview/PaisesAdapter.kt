package com.primer_trimestre.b17_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.primer_trimestre.b17_recyclerview.databinding.ItempaisBinding

class PaisesAdapter(
    private val listaPaises: MutableList<Pais>,
    private val onEliminarClick: (Pais) -> Unit
) : RecyclerView.Adapter<PaisesAdapter.PaisViewHolder>() {

    class PaisViewHolder(val binding: ItempaisBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisViewHolder {
        val binding = ItempaisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaisViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
        val pais = listaPaises[position]

        holder.binding.textNombre.text = pais.nombre
        holder.binding.textCapital.text = pais.capital

        Glide.with(holder.itemView.context)
            .load(pais.bandera)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_dialog_alert)
            .into(holder.binding.imageViewBandera)

        holder.itemView.setOnClickListener {
            onEliminarClick(pais)
        }
    }

    override fun getItemCount(): Int = listaPaises.size
}
