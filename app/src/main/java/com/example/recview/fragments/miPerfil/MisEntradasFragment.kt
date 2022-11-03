package com.example.recview.fragments.miPerfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recview.R
import com.example.recview.adapters.EntradasAdapter
import com.example.recview.adapters.PartidosAdapter
import com.example.recview.entities.EntradaHist
import com.example.recview.entities.Partido
import com.example.recview.entities.Ticket
import com.example.recview.fragments.VentaEntradasFragmentDirections
import com.example.recview.viewmodels.miPerfil.MisEntradasViewModel


class MisEntradasFragment : Fragment() {

    companion object {
        fun newInstance() = MisEntradasFragment()
    }

    private lateinit var viewModel: MisEntradasViewModel
    lateinit var v : View

    var ticketList : MutableList<Ticket> = ArrayList<Ticket>()

    lateinit var adapter : EntradasAdapter
    lateinit var recyclerView: RecyclerView
    //lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_mis_entradas, container, false)

        recyclerView = v.findViewById(R.id.recEntradas)
        //imageView = v.findViewById(R.id.imagen)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisEntradasViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        viewModel.getTickets(36397441)


        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.tickets.observe(viewLifecycleOwner, Observer { result ->
            ticketList = result.toMutableList()

            adapter = EntradasAdapter(ticketList) { pos ->
                val action = MisEntradasFragmentDirections.actionMisEntradasFragmentToDetalleEntrada(ticketList[pos])
                v.findNavController().navigate(action)
            }

            recyclerView.adapter = adapter
        })

    }


}