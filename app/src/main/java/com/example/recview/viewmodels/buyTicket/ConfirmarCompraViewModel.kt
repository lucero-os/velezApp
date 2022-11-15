package com.example.recview.viewmodels.buyTicket

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recview.entities.Partido
import com.example.recview.entities.Ticket
import com.example.recview.entities.UserSingleton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ConfirmarCompraViewModel : ViewModel() {

    private val db = Firebase.firestore
    private var miPartido : MutableList<Partido> = mutableListOf()
    private var miTicket : MutableList<Ticket> = mutableListOf()
    var compraExitosa = MutableLiveData<Boolean>()

   private suspend fun checkDisponibilidad(partido: Partido, ticket: Ticket): Boolean{
        var cantidad = 0
        val partidosRef = db.collection("partidos")
        var resultado = false

        try {
            val partidos = partidosRef.whereEqualTo("id",partido.id).get().await()

            if (partidos != null) {
                miPartido.clear()

                for(document in partidos) {
                    miPartido.add(document.toObject<Partido>())
                    Log.d("Partido", miPartido[0].toString())
                }

                if(ticket.idSector.equals("Norte")){
                    cantidad = miPartido[0].sectorNorte
                }
                else if(ticket.idSector.equals("Este")){
                    cantidad = miPartido[0].sectorEste
                }
                else if(ticket.idSector.equals("SurBaja")){
                    cantidad = miPartido[0].sectorSurBaja
                }
                else if(ticket.idSector.equals("SurAlta")){
                    cantidad = miPartido[0].sectorSurAlta
                }
                else if(ticket.idSector.equals("Oeste")){
                    cantidad = miPartido[0].sectorOeste
                }
                else {
                    cantidad = miPartido[0].sectorVisitante
                }
            }

            resultado = cantidad > 0
        }catch (e : Exception){
            Log.w(ContentValues.TAG, "Error getting partido: ", e)
        }

        Log.d("CANTIDAD", cantidad.toString())
        return resultado
    }

    private suspend fun confirmarCompra(partido: Partido, ticket: Ticket){

        //var partidoComprado = validarSiYaCompro(partido) //hay que agregar el usuario o el dni
        crearTicket(ticket,partido)
        val cantSector = descontarCantidadSector(partido, ticket)
        val partidoID = obtenerPartidoID(partido,ticket)
        restarSector(partidoID, cantSector)

    }

    suspend fun validarSiYaCompro(partido: Partido) : Boolean{
        val ticketsRef = db.collection("tickets")
        var state : Boolean = false
        miTicket.clear()

        try {
            val ticketsPartido = ticketsRef.whereEqualTo("idPartido", partido.id).whereEqualTo("idUser", UserSingleton.getDni()).get().await()

                for (document in ticketsPartido) {
                    miTicket.add(document.toObject<Ticket>())
                    Log.d("Ticket", miTicket[0].toString())
                }

            if (miTicket.size > 0){
                state = true
                Log.d("Ya compro para este partido", "ya tiene un ticket adquirido")
            }
        }catch (e: Exception){
        Log.w(ContentValues.TAG, "Error si ya compró ticket: ", e)
    }
    return state
    }

    private suspend fun restarSector(partidoID : String, cantSector : Partido){
        val partidosRef = db.collection("partidos")
        val updatePartido = hashMapOf(
            "sectorEste" to cantSector.sectorEste,
            "sectorNorte" to cantSector.sectorNorte,
            "sectorOeste" to cantSector.sectorOeste,
            "sectorSurAlta" to cantSector.sectorSurAlta,
            "sectorSurBaja" to cantSector.sectorSurBaja,
            "sectorvisitante" to cantSector.sectorVisitante
        )
        try {
            partidosRef.document(partidoID).update(updatePartido as Map<String, Any>)
            Log.d(TAG, "DocumentSnapshot successfully written!")
        }catch (e: Exception){
            Log.w(TAG, "Error writing document", e)
        }

    }

    private suspend fun crearTicket(ticket: Ticket, partido: Partido){

        val nuevoTicket = hashMapOf(

            "equipo" to "Velez",
            "idPartido" to partido.id,
            "idUser" to ticket.idUser,
            "idSector" to ticket.idSector,
            "rival" to ticket.rival,
            "titulo" to ticket.titulo,
            "valor" to ticket.valor,
            "dia" to ticket.dia,
            "mes" to ticket.mes
        )
        val ticketsRef = db.collection("tickets")

        try {
            ticketsRef.add(nuevoTicket).await()
            Log.d(TAG, "DocumentSnapshot written")
        }catch (e: Exception) {
            Log.w(TAG, "Error adding document", e)
        }
    }

    private suspend fun obtenerPartidoID(partido: Partido, ticket: Ticket) : String{
        var partidoID : String = ""
        val partidosRef = db.collection("partidos")
        try {
            val partido = partidosRef.whereEqualTo("id",partido.id).get().await()
            for (document in partido) {
                Log.d(TAG, "${document.id} => ${document.data}")
                partidoID = document.id
            }
        }catch (e: Exception){
            Log.w(TAG, "Error getting documents: ", e)
        }
        return partidoID
    }

    private suspend fun descontarCantidadSector(partido : Partido, ticket: Ticket) : Partido{

        val partidosRef = db.collection("partidos")
        try {
            val partidos = partidosRef.whereEqualTo("id",partido.id).get().await()
            Log.d("Partido", miPartido[0].toString())
            if (partidos != null) {
                miPartido.clear()

                for(document in partidos) {
                    miPartido.add(document.toObject<Partido>())
                    Log.d("Partido", miPartido[0].toString())
                }

                if(ticket.idSector.equals("Norte")){
                    miPartido[0].sectorNorte = miPartido[0].sectorNorte - 1
                }
                else if(ticket.idSector.equals("Este")){
                    miPartido[0].sectorEste = miPartido[0].sectorEste - 1
                }
                else if(ticket.idSector.equals("SurBaja")){
                    miPartido[0].sectorSurBaja = miPartido[0].sectorSurBaja - 1
                }
                else if(ticket.idSector.equals("SurAlta")){
                    miPartido[0].sectorSurAlta = miPartido[0].sectorSurAlta - 1
                }
                else if(ticket.idSector.equals("Oeste")){
                    miPartido[0].sectorOeste = miPartido[0].sectorOeste - 1
                }
                else {
                    miPartido[0].sectorVisitante = miPartido[0].sectorVisitante - 1
                }
            }

        }catch (e: Exception){
            Log.w(ContentValues.TAG, "Error getting partido: ", e)
        }
        return miPartido[0]
    }

    fun comprar( partido: Partido, ticket: Ticket) {

        viewModelScope.launch {
            try {
                var rtdo2 = validarSiYaCompro(partido) //devuelve TRUE si ya tiene 1 entrada para ese partido
                if (!rtdo2) {
                        rtdo2 = checkDisponibilidad(partido, ticket)

                        if (rtdo2) {
                                confirmarCompra(partido, ticket)
                                compraExitosa.value = true
                                Log.d("RESULTADO_COMPRA", rtdo2.toString())
                        }else{
                                compraExitosa.value = rtdo2
                            }
                } else {
                    Log.d("RESULTADO_ENTRADA", "YA POSEE ENTRADA PARA ESTE PARTIDO")
                    compraExitosa.value = !rtdo2
                }
            } catch (e: Exception) {
                Log.d("RESULTADO_COMPRA", "salio por la excepcion de comprar" )
                compraExitosa.value = false
                }
        }
    }
}