package com.example.recview.entities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Partido(var fechaFin: String? = "",
              var fechaInicio: String? = "",
              var local: Boolean? = false,
              var rival: String = "",
              var sectorEste: Int? = 0,
              var sectorNorte: Int? = 0,
              var sectorOeste: Int? = 0,
              var sectorSurAlta: Int? = 0,
              var sectorSurBaja: Int? = 0,
              var sectorVisitante: Int? = 0,
              var torneo: String? = ""):
    Parcelable{

    constructor() : this("","",false,"",0,0,0,0,0,0,"")

    override fun toString(): String {
        return "Partido(equipo='Velez', rival='$rival', torneo='$torneo')"
    }

}