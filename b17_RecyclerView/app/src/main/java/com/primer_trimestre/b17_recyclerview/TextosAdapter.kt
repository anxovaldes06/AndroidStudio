package com.primer_trimestre.b17_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.primer_trimestre.b17_recyclerview.databinding.ItemTextoBinding

class TextosAdapter(
    private val listaTextos: MutableList<String>,
    private val onEliminarClick: (String) -> Unit
) : RecyclerView.Adapter<TextosAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemTextoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTextoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val texto = listaTextos[position]
        holder.binding.tvTexto.text = texto

        // Al hacer clic en el texto, se elimina de la lista
        holder.binding.tvTexto.setOnClickListener {
            onEliminarClick(texto)
        }
    }

    override fun getItemCount(): Int = listaTextos.size
}
