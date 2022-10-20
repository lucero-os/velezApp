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
import com.example.recview.viewmodels.buyTicket.ConfirmarCompraViewModel
import org.w3c.dom.Text
import kotlin.random.Random

class ConfirmarCompra : Fragment() {

    companion object {
        fun newInstance() = ConfirmarCompra()
    }

    private lateinit var viewModel: ConfirmarCompraViewModel
    private lateinit var v: View
    private lateinit var goToComprarBtn: Button
    private lateinit var cancelBtn: Button
    private var ticket = ConfirmarCompraArgs.fromBundle(requireArguments()).ticket

    private lateinit var tituloTicket : TextView
    private lateinit var equipos : TextView
    private lateinit var sector : TextView
    private lateinit var valor : TextView
    private lateinit var code : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_confirmar_compra, container, false)
        goToComprarBtn = v.findViewById(R.id.comprarBtn)
        cancelBtn = v.findViewById(R.id.cancelBtn)
        tituloTicket = v.findViewById(R.id.tituloTicket)
        equipos = v.findViewById(R.id.equipos)
        sector = v.findViewById(R.id.sector)
        valor = v.findViewById(R.id.valor)
        code = v.findViewById(R.id.code)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfirmarCompraViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        setTicket(ticket)

        goToComprarBtn.setOnClickListener {

            val action = ConfirmarCompraDirections.actionConfirmarCompraToResultadoCompra(viewModel.resultadoCompra())
            v.findNavController().navigate(action)
        }

        cancelBtn.setOnClickListener {

            val action = ConfirmarCompraDirections.actionConfirmarCompraToVentaEntradasFragment()
            v.findNavController().navigate(action)
        }
    }

    private fun setTicket(ticket : Ticket){

        tituloTicket.text = ticket.titulo
        equipos.text = ticket.equipo + " VS " + ticket.rival
        sector.text = ticket.idSector
        valor.text = ticket.valor.toString()
        code.text = Random(123).nextInt(10000000).toString()
    }
}