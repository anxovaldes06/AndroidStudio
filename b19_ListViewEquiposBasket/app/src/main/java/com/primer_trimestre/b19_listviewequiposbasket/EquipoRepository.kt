package com.primer_trimestre.b19_listviewequiposbasket

class EquipoRepository {

    var listaEquipos: ArrayList<Equipo>

    init {
        listaEquipos = poblarSpinner()
    }

    companion object {

        fun poblarSpinner(): ArrayList<Equipo> {

            val lista = ArrayList<Equipo>()

            val equipo1 = Equipo(
                "Maccabi Basketball Club",
                "Tel Aviv",
                "Israel",
                1932,
                R.drawable.club2
            )
            lista.add(equipo1)

            val equipo2 = Equipo(
                "Košarkaški Klub Crvena Zvezda",
                "Belgrado",
                "Serbia",
                1945,
                R.drawable.club1
            )
            lista.add(equipo2)

            val equipo3 = Equipo(
                "Basketball Club Žalgiris",
                "Kaunas",
                "Lituania",
                1944,
                R.drawable.club3
            )
            lista.add(equipo3)

            val equipo4 = Equipo(
                "Club Deportivo Baskonia",
                "Vitoria",
                "España",
                1959,
                R.drawable.club4
            )
            lista.add(equipo4)

            val equipo5 = Equipo(
                "Panathinaikos Basketball Club",
                "Atenas",
                "Grecia",
                1919,
                R.drawable.club5
            )
            lista.add(equipo5)

            val equipo6 = Equipo(
                "Olympiakós Syndesmos Filathlon Peiraiós",
                "Atenas",
                "Grecia",
                1925,
                R.drawable.club6
            )
            lista.add(equipo6)

            return lista
        }
    }
}