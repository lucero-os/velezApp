package com.example.recview.entities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Partido(var dia: String = "",
              var fecha: String = "",
              var fechaFin: String? = "",
              var fechaInicio: String? = "",
              var hora: String = "",
              var id: Int = 0,
              var mes: String = "",
              var rival: String = "",
              var sectorEste: Int = 0,
              var sectorNorte: Int = 0,
              var sectorOeste: Int = 0,
              var sectorSurAlta: Int = 0,
              var sectorSurBaja: Int = 0,
              var sectorVisitante: Int = 0,
              var torneo: String = "",
              var estaDisp: Boolean = false):
    Parcelable{

    constructor() : this("","","","","",0,"","",0,0,0,0,0,0,"", false)

    override fun toString(): String {
        return "Partido(equipo='Velez', rival='$rival', torneo='$torneo',dia='$dia',mes'$mes',hora'$hora',fecha'$fecha')"
    }

}