package com.example.recview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.recview.R
import com.example.recview.viewmodels.miPerfil.MiPerfilViewModel

class MiPerfilFragment : Fragment() {


    lateinit var v : View
    lateinit var btnCarnet : Button
    lateinit var btnMisEntradas : Button
    lateinit var btnMisPagos : Button
    lateinit var btnHaceteSocio : Button

    companion object {
        fun newInstance() = MiPerfilFragment()
    }

    private lateinit var viewModel: MiPerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_mi_perfil, container, false)
        btnCarnet = v.findViewById(R.id.btnMiPerfilToCarnet)
        btnMisEntradas = v.findViewById(R.id.btnMiPerfilToMisEntradas)
        btnMisPagos = v.findViewById(R.id.btnMiPerfilToMisPagos)
        btnHaceteSocio = v.findViewById(R.id.btnMiPerfilToHaceteSocio)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnCarnet.setOnClickListener {
            val action = MiPerfilFragmentDirections.actionMiPerfilFragmentToCarnetFragment()
            v.findNavController().navigate(action)

        }
        btnMisEntradas.setOnClickListener {
            val action = MiPerfilFragmentDirections.actionMiPerfilFragmentToMisEntradasFragment()
            v.findNavController().navigate(action)

        }

        btnMisPagos.setOnClickListener {
            val action = MiPerfilFragmentDirections.actionMiPerfilFragmentToMisPagosFragment()
            v.findNavController().navigate(action)

        }

        btnHaceteSocio.setOnClickListener {
            val action = MiPerfilFragmentDirections.actionMiPerfilFragmentToHaceteSocioFragment()
            v.findNavController().navigate(action)

        }




    }



}