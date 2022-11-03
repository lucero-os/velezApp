package com.example.recview.viewmodels.login

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recview.entities.User
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

    fun signUp(name: String, lastname: String, email: String, dni: Int, password: String){
        //TODO: Primero deberia generarse el auth, luego obtener el uid del FirebaseUser y utilizarlo para generar el registro en "usuarios"

        viewModelScope.launch {
            var createResult : Boolean = newUser(name, lastname, email, dni, password)

            if(createResult){
                try{
                    auth.createUserWithEmailAndPassword(email, password).await()
                    Log.d(ContentValues.TAG, "Auth user written")
                    signUpResult.value = true
                }catch( e: Exception){
                    Log.d(ContentValues.TAG, "Error writing auth user" + e)
                    signUpResult.value = false
                }
            }else{
                signUpResult.value = false
            }
        }
    }
    //Contrasena debe tener 6 caracteres o mas
    suspend fun newUser( name: String, lastname: String, email: String,dni: Int,password: String ) : Boolean {

//        var newUser = User(false, lastname, dni,"", email,"",null, name,"",false)
        val newUser = hashMapOf(
            "nombre" to name,
            "apellido" to lastname,
            "email" to email,
            "dni" to dni
        )

        val usersRef = db.collection("usuarios")

        return try {
            usersRef.add(newUser).await()
            Log.d(ContentValues.TAG, "DocumentSnapshot written")
            true
        }catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            false
        }
    }

}