package com.example.recview.viewmodels

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recview.entities.Partido
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
                val p = document.toObject<Partido>()
                if(p.estaDisp){
                    partidosList.add(document.toObject<Partido>())
                }
            }
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "Error getting partidos", e)
        }
        return partidosList
    }
}