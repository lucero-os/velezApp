package com.example.recview.fragments.login

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recview.R
import com.example.recview.entities.User
import com.example.recview.viewmodels.login.SignUpViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.regex.Pattern
import kotlin.math.absoluteValue


class SignUp : Fragment() {

    lateinit var v : View
    private lateinit var btnRegistrarse : Button
    lateinit var name: TextView
    lateinit var lastname: TextView
    lateinit var email: TextView
    lateinit var dni: TextView
    lateinit var pass: TextView
    lateinit var passCheck: TextView
    lateinit var phone: TextView
    lateinit var terms: CheckBox
    private val db = Firebase.firestore
    private var miUser : MutableList<User> = mutableListOf()

    companion object {
        fun newInstance() = SignUp()
    }

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_sign_up,container,false)
        btnRegistrarse= v.findViewById(R.id.registrarseButton)
        name= v.findViewById(R.id.inputName)
        lastname= v.findViewById(R.id.inputLastname)
        email= v.findViewById(R.id.inputEmail)
        dni= v.findViewById(R.id.inputDni)
        phone= v.findViewById(R.id.inputPhone)
        pass= v.findViewById(R.id.inputPassword)
        passCheck= v.findViewById(R.id.inputPasswordCheck)
        terms= v.findViewById(R.id.inputTerms)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        val contextView = v

        val scope = CoroutineScope(
            Job() + Dispatchers.Main
        )

        btnRegistrarse.setOnClickListener() {

            val nameForm = name.text.toString()
            val lastnameForm = lastname.text.toString()
            val emailForm = email.text.toString()
            val dniForm = if (dni.text.toString() != ""){
                dni.text.toString().toInt()
            } else {
                0
            }
            val passForm = pass.text.toString()
            val passCheckpassForm = passCheck.text.toString()
            val phoneForm = phone.text.toString()
            val termsForm = terms.isChecked

            try{
                scope.launch {

                    if (validateForm(nameForm,lastnameForm,emailForm,dniForm,passForm,passCheckpassForm,phoneForm)){
                        if (termsForm){
                            if (checkDni(dniForm)){
                                viewModel.signUp(nameForm, lastnameForm, emailForm, dniForm, passForm, phoneForm)
                            }else{
                                dni.error= "Usuario ya existe"
                                dni.isFocusable= true
                            }
                        }else{
                            Snackbar.make(contextView,"Debe aceptar terminos y condiciones",Snackbar.LENGTH_SHORT).show()
                            terms.isFocusable= true
                        }
                    }
                }
            }catch (_: Exception){
            }
        }

        viewModel.signUpResult.observe( viewLifecycleOwner, Observer {
            if(it){
                val action = SignUpDirections.actionSignUpToLogin()
                findNavController().navigate(action)
            }else{
                email.error="Ingrese un email válido"
                email.isFocusable= true
            }
        })

    }

    fun validateForm(newName: String, newLastname: String, newEmail: String, newDni: Int, newPass : String, newPassCheck : String, newPhone : String) : Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        var validation = false

        if(newName.isEmpty() || !isLetters(newName) ){
            name.error="Ingrese su nombre"
            name.isFocusable= true
            return false
        }else{
            name.error=null
            validation = true
        }
        if(newLastname.isEmpty() || !isLetters(newLastname)){
            lastname.error="Ingrese su apellido"
            lastname.isFocusable= true
            return false
        }else{
            lastname.error=null
            validation = true
        }
        if (!pattern.matcher(newEmail).matches()){
            email.error="Ingrese un email válido"
            email.isFocusable= true
            return false
        }else{
            email.error=null
            validation = true
        }
        if (newDni.absoluteValue.toString().length < 7 || newDni.absoluteValue.toString().length > 8){
            dni.error="Ingrese un DNI válido"
            dni.isFocusable= true
            return false
        }else{
            dni.error=null
            validation = true
        }
        if (newPhone.isEmpty() || newPhone.length < 8 ){
            phone.error="Ingrese un número válido"
            phone.isFocusable= true
            return false
        }else{
            phone.error=null
            validation = true
        }
        if (newPass.length < 6){
            pass.error="Debe contener al menos 6 caracteres"
            pass.isFocusable= true
            return false
        }else{
            pass.error= null
            validation = true
        }
        if (newPass != newPassCheck){
            passCheck.error="Contraseña no coincide"
            passCheck.isFocusable= true
            return false
        }else{
            passCheck.error=null
            validation = true
        }
    return validation
    }

    //Devuelve TRUE si el usuario con DNI NO existe
    suspend fun checkDni(dni : Int) : Boolean{
        val usersRef = db.collection("usuarios")
        var state : Boolean = false
        miUser.clear()

        try{
            val usuario = usersRef.whereEqualTo("dni", dni).get().await()

            for (document in usuario) {
                miUser.add(document.toObject<User>())
                Log.d("Ticket", miUser[0].toString())
            }
            state = miUser.isEmpty()
        }
        catch (e: Exception){

        }
    return state
    }

    fun isLetters(string: String): Boolean {
        return string.all { it.isLetter() }
    }
}