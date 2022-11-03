package com.example.recview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Ticket (var equipo: String = "",
              var idPartido: Int = 0,
              var idUser: Int = 0,
              var idSector: String = "",
              var rival: String = "",
              var titulo: String = "",
              var valor: Double = 0.0,
              var dia: String = "",
              var mes: String = "") : Parcelable {
    constructor() : this("",0,0,"","","",0.0, "", "")
}
