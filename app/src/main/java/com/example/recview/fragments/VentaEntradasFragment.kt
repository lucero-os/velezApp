package com.example.recview.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recview.R
import com.example.recview.activities.MainActivity
import com.example.recview.adapters.PartidosAdapter
import com.example.recview.entities.Partido
import com.example.recview.viewmodels.VentaEntradasViewModel

class VentaEntradasFragment : Fragment() {

    class Constants{
        companion object{
            val nombreLocal = "Velez"
        }
    }
    lateinit var v: View
    lateinit var recPartidos : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var partidosAdapter: PartidosAdapter
    var partidos : MutableList<Partido> = ArrayList<Partido>()
    private lateinit var progessBar : ProgressBar

    companion object {
        fun newInstance() = PartidoFragment()
    }

    //trae el viewmodel
    private val viewModel: VentaEntradasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_venta_entradas, container, false)
        recPartidos = v.findViewById(R.id.rec_partidos) // binding recyclerview
        progessBar = v.findViewById((R.id.progressBar)) // binding barra de progreso
        return v
    }


    override fun onStart() {
        super.onStart()

        viewModel.getPartidos() // trae la lista de partidos

        recPartidos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        //inicializo el recyclerview
        recPartidos.layoutManager = linearLayoutManager

        //observer: está monitoreando la variable partidos, trae la lista desde el viewmodel, actualiza los cambios en el momento
        // por eso partidos es una mutablelist
        viewModel.partidos.observe(viewLifecycleOwner, Observer { result ->
            partidos = result.toMutableList()
            progessBar.visibility = View.GONE

            partidosAdapter = PartidosAdapter(partidos) { pos ->
                val action = VentaEntradasFragmentDirections.actionVentaEntradasFragmentToTicketDetail(partidos[pos])
                //pasa del fragment venta de entradas al ticketdetail
                // para seguir con la compra
                v.findNavController().navigate(action)
            }

            recPartidos.adapter = partidosAdapter
        })

    }

}