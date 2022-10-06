package com.example.recview.fragments.miPerfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recview.R
import com.example.recview.viewmodels.miPerfil.MisEntradasViewModel

class MisEntradasFragment : Fragment() {

    companion object {
        fun newInstance() = MisEntradasFragment()
    }

    private lateinit var viewModel: MisEntradasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mis_entradas, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisEntradasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}