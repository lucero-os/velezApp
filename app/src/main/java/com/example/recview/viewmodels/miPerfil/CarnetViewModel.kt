package com.example.recview.viewmodels.miPerfil

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recview.entities.Partido
import com.example.recview.entities.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CarnetViewModel : ViewModel() {

    //private var myAccount : MutableLiveData<MutableList<User>>()
    private val myAccount = MutableLiveData<MutableList<User>>()

    private val db = Firebase.firestore

    // TODO: hay que agregar para que reciba el dni del usuario logueado

    fun getUser(){
        viewModelScope.launch {
            myAccount.value = getAll().toMutableList()
        }
    }
    private suspend fun getAll() : MutableList<User> {
        val userList = arrayListOf<User>()
        val userRef = db.collection("usuarios")
        try {
            val data = userRef.get().await()
            for (document in data) {
                Log.d("User", userList[0].toString())
                userList.add(document.toObject<User>())
            }

        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error si ya compró ticket: ", e)
        }
        return userList
    }
}