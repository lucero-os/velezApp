package com.example.recview.viewmodels

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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class VentaEntradasViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val partidos = MutableLiveData<MutableList<Partido>>()
    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore
    private var miTicket : MutableList<Ticket> = mutableListOf()
    var poseeEntrada = MutableLiveData<Boolean>()

    fun getPartidos(){
        viewModelScope.launch {
            partidos.value = getAll().toMutableList()
        }
    }

    private suspend fun getAll(): List<Partido> {

        var partidosList = arrayListOf<Partido>()
        val partidosRef = db.collection("partidos")
        // val query = questionRef

        try {
            val data = partidosRef.get().await()
            for (document in data) {
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                partidosList.add(document.toObject<Partido>())
            }
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "Error getting partidos", e)
        }
        return partidosList
    }

    private suspend fun validarSiYaCompro(partido: Partido){
        val ticketsRef = db.collection("tickets")
        miTicket.clear()

        try {
            val ticketsPartido = ticketsRef.whereEqualTo("idPartido", partido.id).whereEqualTo("idUser", UserSingleton.getDni()).get().await()

            for (document in ticketsPartido) {
                miTicket.add(document.toObject<Ticket>())
                Log.d("Ticket", miTicket[0].toString())
            }

            if (miTicket.size > 0){
                poseeEntrada.value = true
                Log.d("Ya compro para este partido", "ya tiene un ticket adquirido")
            }
            else{
                poseeEntrada.value = false
            }
        }catch (e: Exception){
            poseeEntrada.value = false
            Log.w(ContentValues.TAG, "Error si ya compró ticket: ", e)
        }
    }

    fun yaCompro(partido: Partido) : Boolean{
        var resultado = false

        viewModelScope.launch {
            try{
                validarSiYaCompro(partido)
            }
            catch (e: Exception){
                Log.d("Error", "Error en yaCompro")
            }
        }

        return resultado
    }
}