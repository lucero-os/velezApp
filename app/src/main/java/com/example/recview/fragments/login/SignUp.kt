package com.example.recview.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.recview.R
import com.example.recview.databinding.FragmentSignUpBinding
import com.example.recview.viewmodels.login.SignUpViewModel

class SignUp : Fragment() {

    companion object {
        fun newInstance() = SignUp()
    }
    //Implement binding
    private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        binding.registrarseBtn.setOnClickListener {
//            if(validateForm(name, lastname, email, dni, cellphone, pass)) {
//                Log.d("paso", name)
//
//            }else{
//                Log.d("no paso", name)
//            }
            var name = binding.inputName.text.toString()
            var lastname = binding.inputLastname.text.toString()
            var email = binding.inputEmail.text.toString()
            var dni = binding.inputDni.text.toString().toInt()
            var pass = binding.inputPassword.text.toString()
            var terms = binding.inputTerms.text.toString()

            viewModel.signUp(name, lastname, email, dni, pass)

        }

        viewModel.signUpResult.observe( viewLifecycleOwner, Observer {
            if(it){
                val action = SignUpDirections.actionSignUpToLogin()
                findNavController().navigate(action)
            }
        })
    }
    //TODO: FALTA VALIDAR TERMINOS Y CONDICIONES, QUE TIPO DE DATO ES????
    fun validateForm(name: String, lastname: String,  email: String, dni: String, password: String) : Boolean {
        return name.isNotEmpty() &&
                lastname.isNotEmpty() &&
                email.isNotEmpty() &&
                dni.isNotEmpty() &&
                password.isNotEmpty()
//                && terms.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}