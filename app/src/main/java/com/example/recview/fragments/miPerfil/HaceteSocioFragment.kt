package com.example.recview.fragments.miPerfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.recview.R
import com.example.recview.viewmodels.miPerfil.HaceteSocioViewModel
import com.google.android.material.snackbar.Snackbar

class HaceteSocioFragment : Fragment() {


    lateinit var v : View
    lateinit var btnHaceteSocio: Button

    companion object {
        fun newInstance() = HaceteSocioFragment()
    }

    private lateinit var viewModel: HaceteSocioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_hacete_socio, container, false)
        btnHaceteSocio = v.findViewById(R.id.haceteSocioButton)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnHaceteSocio.setOnClickListener(){
            val contextView = v

            Snackbar.make(contextView, "Tu solicitud ha sido enviada y esta sujeta a aprobacion", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

}