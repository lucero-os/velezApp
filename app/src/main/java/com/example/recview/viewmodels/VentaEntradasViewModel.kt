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

//el viewmodel es donde están las reglas de negocio, sirve para que si se modifica en un futuro la base de datos por ejemplo
// solo deba cambiarse el back sin necesidad de tocar el front

class VentaEntradasViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val partidos = MutableLiveData<MutableList<Partido>>()
    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    fun getPartidos(){
        viewModelScope.launch {
            partidos.value = getAll().toMutableList() // es una corrutina dentro de getPartidos()
        }
    }
      private suspend fun getAll(): List<Partido> { //es asíncrona por eso tiene el suspend

        var partidosList = arrayListOf<Partido>()
        val partidosRef = db.collection("partidos")
        // val query = questionRef - trae los partidos que están en la base de datos

        try {
            val data = partidosRef.get().await() //no pasa de acá hasta que no trae la base de datos
            for (document in data) {
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                val p = document.toObject<Partido>()
                if(p.estaDisp){ //estaDisp es un booleano dentro de la entidad partido, la setea el admin
                    partidosList.add(document.toObject<Partido>())
                    // agrega a la lista los partidos de la base de datos, los que no están disponibles no los trae
                    // toObject mapea el documento de firebase al objeto
                }
            }
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "Error getting partidos", e)
        }
        return partidosList
    }
}