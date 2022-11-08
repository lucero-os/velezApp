package com.example.recview.fragments.buyTicket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.recview.R
import com.example.recview.entities.Partido
import com.example.recview.entities.Ticket
import com.example.recview.viewmodels.buyTicket.ConfirmarCompraViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.random.Random

class ConfirmarCompra : Fragment() {

    companion object {
        fun newInstance() = ConfirmarCompra()
    }

    object DebitCardConstants {
        const val DEBIT_CARD_LENGTH = 16
        const val CVV_LENGTH = 3
        const val DATE_LENGTH = 6
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
    private lateinit var cardDate: EditText
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
        cardDate = v.findViewById(R.id.expCard)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfirmarCompraViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        val contextView = v

        setTicket(ticket)

        goToComprarBtn.setOnClickListener {

            val d = debitCardNumber.text.toString()
            val c = cvv.text.toString()
            val fecha = cardDate.text.toString()

            if (validateDebitCardNumber(d) && (fecha!="" && fecha.length > 5) && validateDate(fecha) && validateCvv(c))
            {
                try{
                    viewModel.comprar(partido, ticket)
                    viewModel.compraExitosa.observe(viewLifecycleOwner, Observer { result ->
                        val action = ConfirmarCompraDirections.actionConfirmarCompraToResultadoCompra(result,ticket, partido)
                        v.findNavController().navigate(action)
                    })
                }catch (e: Exception){}
            }else
            {
                Snackbar.make(contextView,"Debe completar todos los datos de la tarjeta", Snackbar.LENGTH_SHORT).show()
            }

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
        code.text ="Operacion: #" + Random(123).nextInt(10000000).toString()
    }


    private fun validateDebitCardNumber(debitCard: String) : Boolean{

        if (debitCard.length == DebitCardConstants.DEBIT_CARD_LENGTH){
            debitCardNumber.error= null
            debitCardNumber.isFocusable= false
            return true
        }else{
            debitCardNumber.error= "Tarjeta erronea"
            debitCardNumber.isFocusable= true
            return false
        }

    }

    private fun validateCvv(cvv1: String) : Boolean{

        if (cvv1.length == DebitCardConstants.CVV_LENGTH){
            cvv.error = null
            cvv.isFocusable= false
            return true
        }else{
            cvv.error= "Codigo erroneo"
            cvv.isFocusable= true
            return false
        }
    }

    private fun validateDate(date : String): Boolean{

        var mes =date.subSequence(0, 2)
        var anio=date.subSequence(2, 6)

        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH) +1



        if (mes[0].toString().toInt() == 0){
            mes = mes[1].toString()
        }

        if (mes.toString().toInt() in 1..12){
            if (anio.toString().toInt() >=year){
                cardDate.error=null
                cardDate.isFocusable= false
                return true
            }else{
                cardDate.error="Tarjeta Vencida"
                cardDate.isFocusable= true
                return false
            }
        }else
        {
            cardDate.error="Fecha ingresada no es valida"
            cardDate.isFocusable= true
            return false
        }

    }
}