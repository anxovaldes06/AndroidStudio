package com.example.b18_SpinnerFutbol

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class SpinnerAdapter(
    context: Context,
    list: ArrayList<Team>
) : ArrayAdapter<Team>(context, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context)
                .inflate(R.layout.elemento, parent, false)

            holder = ViewHolder(
                view.findViewById(R.id.name),
                view.findViewById(R.id.year),
                view.findViewById(R.id.country),
                view.findViewById(R.id.city),
                view.findViewById(R.id.escudo)
            )

            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val team = getItem(position)

        if (team != null) {
            holder.name.text = team.name
            holder.year.text = team.year.toString()
            holder.country.text = team.country
            holder.city.text = team.city
            holder.crest.setImageResource(team.crest)
        }

        return view
    }

    data class ViewHolder(
        val name: TextView,
        val year: TextView,
        val country: TextView,
        val city: TextView,
        val crest: ImageView
    )
}