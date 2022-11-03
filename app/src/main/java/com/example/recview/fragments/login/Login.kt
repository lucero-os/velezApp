package com.example.recview.fragments.login

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.recview.R
import com.example.recview.viewmodels.login.LoginViewModel

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        signUpBtn = v.findViewById(R.id.signUpBtn)
        loginBtn = v.findViewById(R.id.loginButton)
        userEmail = v.findViewById(R.id.userEmail)
        userPass = v.findViewById(R.id.userPass)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        viewModel.user.observe( viewLifecycleOwner, Observer {
            if(it != null){
                Log.d(ContentValues.TAG, "Auth user loggued" + it.uid)
                val action = LoginDirections.actionLoginToMainActivity2()
                v.findNavController().navigate(action)
            }
        })

        signUpBtn.setOnClickListener {
            val action = LoginDirections.actionLoginToSignUp()
            v.findNavController().navigate(action)
        }

        loginBtn.setOnClickListener {
            val email = userEmail.text.toString()
            val pass = userPass.text.toString()

            if(!email.isNullOrEmpty() && !pass.isNullOrEmpty()){
                viewModel.login(email, pass)
            }
        }
    }
}