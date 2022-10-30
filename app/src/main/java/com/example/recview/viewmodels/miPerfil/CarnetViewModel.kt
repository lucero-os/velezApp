package com.example.recview.viewmodels.miPerfil

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.recview.entities.Partido
import com.example.recview.entities.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class CarnetViewModel : ViewModel() {

    private var myAccount : MutableList<User> = mutableListOf()
    private val db = Firebase.firestore

    // TODO: hay que agregar para que reciba el dni del usuario logueado
    suspend fun getUser() : User {
        val db = Firebase.firestore
        val userRef = db.collection("usuarios")
        myAccount.clear()
        try {
            val usuario = userRef.whereEqualTo("dni", 36397441).get().await()

            if (myAccount != null) {
                myAccount.clear()

                for (document in usuario) {
                    myAccount.add(document.toObject<User>())
                    Log.d("User", myAccount[0].toString())
                }
            }
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error si ya compró ticket: ", e)
        }
        return myAccount[0]
    }
}