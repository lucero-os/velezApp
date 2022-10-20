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

class CarnetViewModel : ViewModel() {

    private var myAccount : MutableList<User> = mutableListOf()
    private val db = Firebase.firestore

    fun getUser() : MutableList<User> {
        db.collection("usuarios")
            .whereEqualTo("dni", 36397441)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    myAccount.clear()

                for(document in document) {
                    myAccount.add(document.toObject<User>())
                    Log.d("User", myAccount[0].toString())
                }
            }}
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting user profile: ", exception)
            }
        return myAccount
    }
}