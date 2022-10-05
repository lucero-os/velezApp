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
import com.example.recview.viewmodels.buyTicket.ResultadoCompraViewModel

class ResultadoCompra : Fragment() {

    companion object {
        fun newInstance() = ResultadoCompra()
    }

    private lateinit var viewModel: ResultadoCompraViewModel
    private lateinit var v: View
    private lateinit var goToHomeBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_resultado_compra, container, false)
        goToHomeBtn = v.findViewById(R.id.goToHomeBtn)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResultadoCompraViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        goToHomeBtn.setOnClickListener {

            val action = ResultadoCompraDirections.actionResultadoCompraToMainActivity()
            v.findNavController().navigate(action)
        }
    }

}