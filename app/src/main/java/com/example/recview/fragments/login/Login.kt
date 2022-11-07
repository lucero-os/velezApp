package com.example.recview.fragments.login

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var v: View
    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var userEmail : TextView
    private lateinit var userPass : TextView
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



    override fun onStart() {
        super.onStart()

        viewModel.user.observe( viewLifecycleOwner, Observer {
            if(it != null){
                val action = LoginDirections.actionLoginToMainActivity2()
                v.findNavController().navigate(action)
            }

        })

        signUpBtn.setOnClickListener {

            val action = LoginDirections.actionLoginToSignUp()
            v.findNavController().navigate(action)

        }

        loginBtn.setOnClickListener {
            val contextView = v
            val email = userEmail.text.toString()
            val pass = userPass.text.toString()



            if(!email.isNullOrEmpty() && !pass.isNullOrEmpty()){
                    viewModel.login(email, pass)
                    progressBar.visibility = View.VISIBLE
            }else{
                Snackbar.make(contextView, "Email Y Contraseña INCOMPLETOS, ingrese la informacion para iniciar sesion",Snackbar.LENGTH_LONG).show()
            }

        }
    }
}