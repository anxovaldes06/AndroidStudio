package com.primer_trimestre.b19_listviewequiposbasket

class Equipo(

    val nombre: String,
    val ciudad: String,
    val pais: String,
    val fundacion: Int,
    val idImage: Int
) {
    override fun toString(): String {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", fundacion=" + fundacion +
                ", idImage=" + idImage +
                '}'
    }
}
