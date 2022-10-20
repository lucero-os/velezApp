package com.example.recview.entities

import android.os.Parcelable

class Ticket (var code: String? = "",
              var equipo: String? = "",
              var idEvento: Int? = 0,
              var idUser: Int? = 0,
              var isSocio: Boolean? = false,
              var idSector: Int? = 0,
              var rival: String? = "",
              var titulo: String? = "",
              var valor: Double? = 0.0)
{
    constructor() : this("","",0,0,false,0,"","",0.0)
}
