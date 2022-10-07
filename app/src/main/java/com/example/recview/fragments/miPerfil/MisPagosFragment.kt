package com.example.recview.fragments.miPerfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.recview.R
import com.example.recview.adapters.PagosAdapter
import com.example.recview.entities.PagosHist
import com.example.recview.viewmodels.miPerfil.MisPagosViewModel

class MisPagosFragment : Fragment() {

    companion object {
        fun newInstance() = MisPagosFragment()
    }

    lateinit var v: View
    private lateinit var viewModel: MisPagosViewModel

    lateinit var adapter: PagosAdapter
    lateinit var recyclerView: RecyclerView
    var pagosList: MutableList<PagosHist> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_mis_pagos, container, false)

        recyclerView = v.findViewById(R.id.recPagos)

        pagosList.add(PagosHist("2022-01-01", 25000))
        pagosList.add(PagosHist("2022-02-01", 30000))
        pagosList.add(PagosHist("2022-03-01", 35000))
        pagosList.add(PagosHist("2022-04-01", 35000))
        pagosList.add(PagosHist("2022-05-01", 40000))
        pagosList.add(PagosHist("2022-06-01", 40000))
        pagosList.add(PagosHist("2022-07-01", 40000))
        pagosList.add(PagosHist("2022-08-01", 40000))
        pagosList.add(PagosHist("2022-09-01", 40000))

        return v
    }

    override fun onStart() {
        super.onStart()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = PagosAdapter(pagosList)
        recyclerView.adapter = adapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisPagosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}