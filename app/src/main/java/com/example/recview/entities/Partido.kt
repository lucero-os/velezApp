package com.example.recview.entities

import android.os.Parcel
import android.os.Parcelable
import android.widget.Button


class Partido (id:Int?,equipo:String?, rival: String?, torneo: String?) {

    var id : Int = 0;
    var equipo : String = "";
    var rival : String = "";
    var torneo : String = "";

    class Constants {
        companion object {
            val equipo = "Velez"
        }
    }

    init {
        this.id = id!!
        this.equipo = equipo!!
        this.rival = rival!!
        this.torneo = torneo!!
    }



}