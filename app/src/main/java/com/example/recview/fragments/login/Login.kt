package com.example.recview.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.recview.R
import com.example.recview.viewmodels.login.LoginViewModel

class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var v: View
    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        signUpBtn = v.findViewById(R.id.signUpBtn)
        loginBtn = v.findViewById(R.id.loginButton)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        signUpBtn.setOnClickListener {
            val action = LoginDirections.actionLoginToSignUp()
            v.findNavController().navigate(action)
        }

        loginBtn.setOnClickListener {

            val action = LoginDirections.actionLoginToMainActivity2()
            v.findNavController().navigate(action)
        }
    }

}