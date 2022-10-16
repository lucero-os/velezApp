package com.example.recview.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.recview.entities.Partido
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class VentaEntradasViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var partidos : MutableList<Partido> = mutableListOf()
    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    fun getPartidos() : MutableList<Partido>{
        db.collection("partidos").get()
            .addOnSuccessListener { snapshot ->
                //TODO: LOG
                if(snapshot != null){
                    partidos.clear()
                    for(partido in snapshot){
                        partidos.add(partido.toObject())
                    }
                    Log.d("Partido" , partidos[0].toString())
                }
            }
            .addOnFailureListener { exception ->
                //TODO: LOG
            }

        return this.partidos
    }
}