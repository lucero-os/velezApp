package com.example.recview.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
    //TODO: Se podria implementar binding
    private lateinit var goToComprar: Button

    private lateinit var partidosAdapter: PartidosAdapter

    var partidos : MutableList<Partido> = ArrayList<Partido>()

    companion object {
        fun newInstance() = PartidoFragment()
    }

    private val viewModel: VentaEntradasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_venta_entradas, container, false)
        recPartidos = v.findViewById(R.id.rec_partidos)
        return v
    }


    override fun onStart() {
        super.onStart()

        partidos = viewModel.getPartidos()

        recPartidos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recPartidos.layoutManager = linearLayoutManager

        //TODO: Llegan objetos de firebase, pero no se renderizan
        partidosAdapter = PartidosAdapter(partidos) { pos ->

            val action = VentaEntradasFragmentDirections.actionVentaEntradasFragmentToTicketDetail(partidos[pos])
            v.findNavController().navigate(action)
        }

        recPartidos.adapter = partidosAdapter
    }

}