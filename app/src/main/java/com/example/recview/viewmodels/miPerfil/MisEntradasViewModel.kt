package com.example.recview.viewmodels.miPerfil

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recview.entities.Partido
import com.example.recview.entities.Ticket
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MisEntradasViewModel : ViewModel() {

    val tickets = MutableLiveData<MutableList<Ticket>>()
    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    fun getTickets(dni: Int){
        viewModelScope.launch {
            tickets.value = getMisTickets(dni).toMutableList()
        }
    }

    private suspend fun getMisTickets(dni: Int): List<Ticket> {

        var ticketList = arrayListOf<Ticket>()
        val ticketRef = db.collection("tickets")

        try {
            val data = ticketRef.whereEqualTo("idUser",dni).get().await()
            for (document in data) {
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                ticketList.add(document.toObject<Ticket>())
            }
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "Error getting partidos", e)
        }
        return ticketList
    }

}