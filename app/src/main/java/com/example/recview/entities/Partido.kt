package com.example.recview.entities

import android.os.Parcel
import android.os.Parcelable


class Partido(fechaFin: String?, fechaInicio: String?, local: Boolean, rival: String?, torneo: String?):
    Parcelable{

    var fechaFin: String
    var fechaInicio: String
    var local: Boolean
    var rival: String
    var torneo: String

    constructor(parcel: Parcel) : this(
         parcel.readString(),
         parcel.readString(),
         parcel.readByte() != 0.toByte(),
         parcel.readString(),
         parcel.readString()
    )



    constructor() : this("","",false,"","")

    init {
        this.fechaFin = fechaFin!!
        this.fechaInicio = fechaInicio!!
        this.local = local!!
        this.rival = rival!!
        this.torneo = torneo!!
    }

    fun testList(){

    }
    override fun toString(): String {
        return "Partido(equipo='Velez', rival='$rival', torneo='$torneo')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fechaFin)
        parcel.writeString(fechaInicio)
        parcel.writeByte(if (local) 1 else 0)
        parcel.writeString(rival)
        parcel.writeString(torneo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Partido> {
        override fun createFromParcel(parcel: Parcel): Partido {
            return Partido(parcel)
        }

        override fun newArray(size: Int): Array<Partido?> {
            return arrayOfNulls(size)
        }
    }

}