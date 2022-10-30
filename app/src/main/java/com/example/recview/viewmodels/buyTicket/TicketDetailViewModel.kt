package com.example.recview.viewmodels.buyTicket

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.recview.entities.Partido
import com.example.recview.entities.Ticket
import com.example.recview.fragments.buyTicket.TicketDetail
import kotlin.properties.Delegates

class TicketDetailViewModel : ViewModel() {


    fun generateTicket(isSocio: Boolean, partido: Partido, idSector : String, idUser: Int) : Ticket?{

        var valor : Double = generateValor(isSocio)
        var ticket : Ticket? = null

        if(quedaLugar(idSector, partido)){
            ticket = Ticket("Velez", partido.id, idUser, idSector, partido.rival, partido.torneo, valor)
        }

        return ticket
    }

    private fun generateValor(isSocio: Boolean) : Double{
        Log.d(ContentValues.TAG, "ES SOCIO?" + isSocio)

        var valor : Double = if(isSocio){
            1000.0
        } else{
            1500.0
        }
        return valor
    }

    private fun quedaLugar(idSector: String, partido: Partido) : Boolean {
        var cantidad = 0

        if(idSector.equals("Norte")){
            cantidad = partido.sectorNorte
        }
        else if(idSector.equals("Este")){
            cantidad = partido.sectorEste
        }
        else if(idSector.equals("Sur Baja")){
            cantidad = partido.sectorSurBaja
        }
        else if(idSector.equals("Sur Alta")){
            cantidad = partido.sectorSurAlta
        }
        else if(idSector.equals("Oeste")){
            cantidad = partido.sectorOeste
        }
        else{
            cantidad = partido.sectorVisitante
        }

        return cantidad > 0
    }
}