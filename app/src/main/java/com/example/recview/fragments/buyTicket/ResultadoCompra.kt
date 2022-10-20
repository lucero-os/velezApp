package com.example.recview.fragments.buyTicket

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.recview.R
import com.example.recview.entities.Ticket
import com.example.recview.viewmodels.buyTicket.ResultadoCompraViewModel

class ResultadoCompra : Fragment() {

    companion object {
        fun newInstance() = ResultadoCompra()
    }

    private lateinit var viewModel: ResultadoCompraViewModel
    private lateinit var v: View
    private lateinit var goToHomeBtn: Button
    private var resultado = false
    private lateinit var resultadoTitle : TextView
    private lateinit var ticket : Ticket

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_resultado_compra, container, false)
        ticket = ResultadoCompraArgs.fromBundle(requireArguments()).ticket
        goToHomeBtn = v.findViewById(R.id.goToHomeBtn)
        resultadoTitle = v.findViewById(R.id.resultadoTitle)
        resultado = ResultadoCompraArgs.fromBundle(requireArguments()).resultadoCompra
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResultadoCompraViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        if(resultado){
            resultadoTitle.setText(R.string.compraOk)
            goToHomeBtn.setOnClickListener {

                val action = ResultadoCompraDirections.actionResultadoCompraToVentaEntradasFragment()
                v.findNavController().navigate(action)
            }
        }
        else{
            resultadoTitle.setText(R.string.compraNotOk)
            with(goToHomeBtn) {
                setText(R.string.compraNotOkBtn)
                setTextColor(resources.getColor(R.color.white))
                setBackgroundColor(resources.getColor(R.color.red))
            }
            goToHomeBtn.setOnClickListener {

                val action = ResultadoCompraDirections.actionResultadoCompraToConfirmarCompra(ticket)
                v.findNavController().navigate(action)
            }
        }

    }

}