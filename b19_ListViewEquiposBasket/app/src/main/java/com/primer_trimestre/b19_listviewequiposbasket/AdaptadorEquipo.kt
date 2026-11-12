package com.primer_trimestre.b19_listviewequiposbasket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AdaptadorEquipo
//constructor
    (
    context: Context,
    private val layoutResId: Int,

    private val listaEquipos: MutableList<Equipo>
) : ArrayAdapter<Equipo?>(
    context,
    layoutResId, listaEquipos
) {
    override fun getCount(): Int {
        return listaEquipos.size
    }

    override fun getItem(position: Int): Equipo? {
        return listaEquipos.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = LayoutInflater.from(getContext())
            view = inflater.inflate(R.layout.equipo, parent, false)
        }
        val clubName = view.findViewById<TextView>(R.id.tv_clubName)
        val country = view.findViewById<TextView>(R.id.tv_country)
        val city = view.findViewById<TextView>(R.id.tv_city)
        val year = view.findViewById<TextView>(R.id.tv_year)
        val imagen = view.findViewById<ImageView>(R.id.iv_escudo)
        val elemento = listaEquipos.get(position)
        clubName.setText(elemento.nombre)
        country.setText(elemento.pais)
        city.setText(elemento.ciudad)
        year.setText(elemento.fundacion.toString())
        imagen.setImageResource(elemento.idImage)

        view.setOnClickListener(View.OnClickListener { view1: View? ->
            listaEquipos.remove(elemento)
            notifyDataSetChanged()
        })

        return view
    }



    fun addEquipo(equipo: Equipo?) {
        listaEquipos.add(equipo!!)
        notifyDataSetChanged()
    }
}




