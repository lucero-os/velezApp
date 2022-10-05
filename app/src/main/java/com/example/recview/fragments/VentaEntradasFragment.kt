package com.example.recview.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recview.R
import com.example.recview.adapters.PartidosAdapter
import com.example.recview.entities.Partido
import com.example.recview.viewmodels.VentaEntradasViewModel
import com.google.android.material.snackbar.Snackbar

class VentaEntradasFragment : Fragment() {

    lateinit var v: View

    lateinit var recPartidos : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var goToComprar: Button

    private lateinit var partidosAdapter: PartidosAdapter

    var partidos : MutableList<Partido> = ArrayList<Partido>()

    companion object {
        fun newInstance() = PartidoFragment()
    }

    private lateinit var viewModel: VentaEntradasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_venta_entradas, container, false)
        recPartidos = v.findViewById(R.id.rec_partidos)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VentaEntradasViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        partidos.add(Partido(1, "Velez", "Boca", "Superliga"))
        partidos.add(Partido(2, "Velez", "River", "Superliga"))
        partidos.add(Partido(3, "Velez", "Ferro", "Superliga"))
        partidos.add(Partido(4, "Velez", "Sanlo", "Superliga"))
        partidos.add(Partido(5, "Velez", "Racing", "Superliga"))

        recPartidos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recPartidos.layoutManager = linearLayoutManager

        partidosAdapter = PartidosAdapter(partidos) { pos ->

            val action = VentaEntradasFragmentDirections.actionVentaEntradasFragmentToBuyTicketActivity()
            v.findNavController().navigate(action)
        }

        recPartidos.adapter = partidosAdapter
    }

}