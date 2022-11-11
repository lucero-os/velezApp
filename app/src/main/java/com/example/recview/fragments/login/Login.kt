package com.example.recview.fragments.login

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.recview.R
import com.example.recview.entities.User
import com.example.recview.entities.UserSingleton
import com.example.recview.viewmodels.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var v: View
    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var userEmail : EditText
    private lateinit var userPass : EditText
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        signUpBtn = v.findViewById(R.id.signUpBtn)
        loginBtn = v.findViewById(R.id.loginButton)
        userEmail = v.findViewById(R.id.userEmail)
        userPass = v.findViewById(R.id.userPass)
        progressBar = v.findViewById(R.id.progressBar)
        progressBar.visibility= View.GONE

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        val contextView = v

        viewModel.signUpResult.observe( viewLifecycleOwner, Observer {
            if(it){
                progressBar.visibility = View.VISIBLE
                val action = LoginDirections.actionLoginToMainActivity2()
                v.findNavController().navigate(action)
            }else{
                Snackbar.make(contextView,"Usuario no existe",Snackbar.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }

        })

        signUpBtn.setOnClickListener {

            val action = LoginDirections.actionLoginToSignUp()
            v.findNavController().navigate(action)

        }

        loginBtn.setOnClickListener {

            val email = userEmail.text.toString()
            val pass = userPass.text.toString()

            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    userEmail.error= "Email incorrecto"
                    userEmail.isFocusable= true
                }else{
                    viewModel.login(email, pass)
                }

            }else{
                if (email.isEmpty()){
                    userEmail.error= "Ingrese su email"
                    userEmail.isFocusable= true
                }
                if (pass.isEmpty()){
                    userPass.error= "Ingrese su contraseña"
                    userPass.isFocusable= true
                }
            }

        }
    }
}