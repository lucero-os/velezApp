package com.example.recview.fragments.buyTicket

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.recview.R
import com.example.recview.viewmodels.buyTicket.ConfirmarCompraViewModel

class ConfirmarCompra : Fragment() {

    companion object {
        fun newInstance() = ConfirmarCompra()
    }

    private lateinit var viewModel: ConfirmarCompraViewModel
    private lateinit var v: View
    private lateinit var goToComprarBtn: Button
    private lateinit var cancelBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_confirmar_compra, container, false)
        goToComprarBtn = v.findViewById(R.id.comprarBtn)
        cancelBtn = v.findViewById(R.id.cancelBtn)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfirmarCompraViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        goToComprarBtn.setOnClickListener {

            val action = ConfirmarCompraDirections.actionConfirmarCompraToResultadoCompra(viewModel.resultadoCompra())
            v.findNavController().navigate(action)
        }

        cancelBtn.setOnClickListener {

            val action = ConfirmarCompraDirections.actionConfirmarCompraToVentaEntradasFragment()
            v.findNavController().navigate(action)
        }
    }

}