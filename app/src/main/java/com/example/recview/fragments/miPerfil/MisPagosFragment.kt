package com.example.recview.fragments.miPerfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recview.R
import com.example.recview.viewmodels.miPerfil.MisPagosViewModel

class MisPagosFragment : Fragment() {

    companion object {
        fun newInstance() = MisPagosFragment()
    }

    private lateinit var viewModel: MisPagosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mis_pagos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisPagosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}