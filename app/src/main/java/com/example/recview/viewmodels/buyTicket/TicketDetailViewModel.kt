package com.example.recview.viewmodels.buyTicket

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.recview.entities.Partido
import com.example.recview.entities.Ticket
import com.example.recview.fragments.buyTicket.TicketDetail
import kotlin.properties.Delegates

class TicketDetailViewModel : ViewModel() {


    fun generateTicket(isSocio: Boolean, partido: Partido, idSector : String, idUser: Int?) : Ticket?{

        val valor : Double = generateValor(isSocio)
        var ticket : Ticket? = null

        if(idUser != null){
                ticket = Ticket("Velez", partido.id, idUser, idSector, partido.rival, partido.torneo, valor, partido.dia, partido.mes)
        }

        return ticket
    }

    private fun generateValor(isSocio: Boolean) : Double{
        val valor : Double = if(isSocio){
            1000.0
        } else{
            1500.0
        }
        return valor
    }

}