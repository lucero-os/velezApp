package com.example.recview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Partido (var id:Int,var equipo:String,var rival: String,var torneo: String) : Parcelable {

    init {
        this.id = 0
    }

}