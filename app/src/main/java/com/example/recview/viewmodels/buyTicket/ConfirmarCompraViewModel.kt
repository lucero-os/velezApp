package com.example.recview.viewmodels.buyTicket

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.recview.entities.Partido
import com.example.recview.entities.Ticket
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ConfirmarCompraViewModel : ViewModel() {
    private val db = Firebase.firestore
    private var resultadoCompra = false
    private var miPartido : MutableList<Partido> = mutableListOf()

    fun resultadoCompra(): Boolean{
        return this.resultadoCompra
    }

    fun checkDisponibilidad(partido: Partido, ticket: Ticket): Boolean{
        var cantidad = 0
        db.collection("partidos")
            .whereEqualTo("id",partido.id)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    miPartido.clear()

                    for(document in document) {
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
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting partido: ", exception)
            }

        return cantidad > 0
    }

    fun confirmarCompra(partido: Partido,ticket: Ticket){

        crearTicket(ticket)
        val cantSector = descontarCantidadSector(partido, ticket)
        val partidoID = obtenerPartidoID(partido,ticket)
        restarSector(partidoID, cantSector)

    }

    fun restarSector(partidoID : String, cantSector : Partido){

        val updatePartido = hashMapOf(
            "sectorEste" to cantSector.sectorEste,
            "sectorNorte" to cantSector.sectorNorte,
            "sectorOeste" to cantSector.sectorOeste,
            "sectorSurAlta" to cantSector.sectorSurAlta,
            "sectorSurBaja" to cantSector.sectorSurBaja,
            "sectorvisitante" to cantSector.sectorVisitante
        )

        db.collection("partidos").document(partidoID)
            .set(updatePartido)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun crearTicket(ticket: Ticket){
        val nuevoTicket = hashMapOf(
            "equipo" to "Velez",
            "idUser" to ticket.idUser,
            "idSector" to ticket.idSector,
            "rival" to ticket.rival,
            "titulo" to ticket.titulo,
            "valor" to ticket.valor
        )
        db.collection("tickets")
            .add(nuevoTicket)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun obtenerPartidoID(partido: Partido, ticket: Ticket) : String{
        var partidoID : String = ""

        db.collection("partidos").whereEqualTo("id",partido.id)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    partidoID = document.id
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        return partidoID
    }

    fun descontarCantidadSector(partido : Partido, ticket: Ticket) : Partido{
             db.collection("partidos")
            .whereEqualTo("id",partido.id)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    miPartido.clear()

                    for(document in document) {
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
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting partido: ", exception)
            }

        return miPartido[0]
    }

}