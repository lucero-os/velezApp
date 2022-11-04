package com.example.recview.fragments.login

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

            val name = name.text.toString()
            val lastname = lastname.text.toString()
            val email = email.text.toString()
            val dni = if (dni.text.toString() != ""){
                dni.text.toString().toInt()
            } else {
                0
            }
            val pass = pass.text.toString()
            val passCheck = passCheck.text.toString()
            val phone = phone.text.toString()
            val terms = terms.isChecked

            try{
                scope.launch {
                    if (validateForm(name,lastname,email,dni,pass,passCheck,phone) && terms && checkDni(dni)){
                        viewModel.signUp(name, lastname, email, dni, pass, phone)
                    }else{
                        Snackbar.make(contextView,"Debe completar todos los datos solicitados y aceptar terminos y condiciones",Snackbar.LENGTH_SHORT).show()
                    }
                }
            }catch (e: Exception){}
        }

        viewModel.signUpResult.observe( viewLifecycleOwner, Observer {
            if(it){
                val action = SignUpDirections.actionSignUpToLogin()
                findNavController().navigate(action)
            }
        })

    }

    fun validateForm(name: String, lastname: String, email: String, dni: Int, pass : String, passCheck : String, phone : String) : Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return name.isNotEmpty() &&
                lastname.isNotEmpty() &&
                pattern.matcher(email).matches() &&
                (dni.absoluteValue.toString().length >= 7 && dni.absoluteValue.toString().length <= 8 ) &&
                phone.isNotEmpty() &&
                (pass.length >= 6) &&
                pass == passCheck
    }

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
}