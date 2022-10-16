package com.example.recview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Partido (var id:Int, var equipo:String, var rival: String, var torneo: String) : Parcelable {

    constructor() : this(0,"Velez","","")

    init {
        this.id = id
        this.equipo = "Velez"
        this.rival = rival!!
        this.torneo = torneo!!
    }

    fun testList(){

    }
    override fun toString(): String {
        return "Partido(equipo='$equipo', rival='$rival', torneo='$torneo')"
    }
}