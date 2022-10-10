package com.example.recview.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.recview.R
import com.example.recview.viewmodels.login.SignUpViewModel

class SignUp : Fragment() {

    companion object {
        fun newInstance() = SignUp()
    }

    private lateinit var viewModel: SignUpViewModel
    private lateinit var v: View
    private lateinit var registrarseBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_sign_up, container, false)
        registrarseBtn = v.findViewById(R.id.registrarseButton)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        registrarseBtn.setOnClickListener {

            val action = SignUpDirections.actionSignUpToLogin()
            findNavController().navigate(action)
        }
    }
}