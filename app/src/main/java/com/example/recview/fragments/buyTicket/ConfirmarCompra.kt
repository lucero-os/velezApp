package com.example.recview.fragments.buyTicket

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.recview.R
import com.example.recview.entities.Partido
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
    private lateinit var ticket : Ticket
    private lateinit var partido: Partido

    private lateinit var tituloTicket : TextView
    private lateinit var equipos : TextView
    private lateinit var sector : TextView
    private lateinit var valor : TextView
    private lateinit var code : TextView
    //private lateinit var detalle : TextView


    private lateinit var debitCardNumber: EditText
    private lateinit var cvv: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_confirmar_compra, container, false)
        ticket = ConfirmarCompraArgs.fromBundle(requireArguments()).ticket
        partido = ConfirmarCompraArgs.fromBundle(requireArguments()).partido
        goToComprarBtn = v.findViewById(R.id.comprarBtn)
        cancelBtn = v.findViewById(R.id.cancelBtn)
        tituloTicket = v.findViewById(R.id.tituloTicket)
        //detalle = v.findViewById(R.id.detallesTitle)
        equipos = v.findViewById(R.id.equipos)
        sector = v.findViewById(R.id.sector)
        valor = v.findViewById(R.id.valor)
        code = v.findViewById(R.id.code)
        cvv = v.findViewById(R.id.cvv)
        debitCardNumber = v.findViewById(R.id.debitCardNumber)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfirmarCompraViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        setTicket(ticket)

        goToComprarBtn.setOnClickListener {

            val d = debitCardNumber.text.toString()
            val c = cvv.text.toString()

            val action = ConfirmarCompraDirections.actionConfirmarCompraToResultadoCompra(viewModel.comprar(d, c, partido, ticket),ticket, partido)
            v.findNavController().navigate(action)
        }

        cancelBtn.setOnClickListener {

            val action = ConfirmarCompraDirections.actionConfirmarCompraToVentaEntradasFragment()
            v.findNavController().navigate(action)
        }
    }

    private fun setTicket(ticket : Ticket){
        //detalle.text = "Detalle de entrada"
        tituloTicket.text = ticket.titulo
        equipos.text = ticket.equipo + " VS " + ticket.rival
        sector.text = "Sector: " + ticket.idSector
        valor.text = "Precio: $" + ticket.valor.toString()
        code.text ="Codigo de compra: #" + Random(123).nextInt(10000000).toString()
    }
}