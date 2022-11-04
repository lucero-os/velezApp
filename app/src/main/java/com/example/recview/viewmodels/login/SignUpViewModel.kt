package com.example.recview.viewmodels.login

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val auth: FirebaseAuth = Firebase.auth
    var signUpResult : MutableLiveData<Boolean> = MutableLiveData()

    fun signUp(
        name: String,
        lastname: String,
        email: String,
        dni: Int,
        password: String,
        phone: String
    ){

        viewModelScope.launch {
            //Contrasena debe tener 6 caracteres o mas
            val newUser = hashMapOf(
                    "nombre" to name,
                    "apellido" to lastname,
                    "email" to email,
                    "telefono" to phone,
                    "dni" to dni )
            try{
                //Genero FirebaseUser
                auth.createUserWithEmailAndPassword(email, password).await()
                Log.d(ContentValues.TAG, "New user uid" + auth.currentUser!!.uid)
                //Uso uid generado para registrar usuario
                db.collection("usuarios").document(auth.currentUser!!.uid).set(newUser).await()
                Log.d(ContentValues.TAG, "Auth user written")

                signUpResult.value = true
            }catch( e: Exception){
                Log.d(ContentValues.TAG, "Error writing auth user" + e)
                signUpResult.value = false
            }
        }
    }
}