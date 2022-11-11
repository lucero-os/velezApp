package com.example.recview.fragments.buyTicket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.recview.R
import com.example.recview.activities.MainActivity
import com.example.recview.entities.Partido
import com.example.recview.entities.UserSingleton
import com.example.recview.viewmodels.buyTicket.TicketDetailViewModel
import com.google.android.material.snackbar.Snackbar


class TicketDetail : Fragment() {

    companion object {
        fun newInstance() = TicketDetail()
    }

    private lateinit var viewModel: TicketDetailViewModel
    private lateinit var v: View
    private lateinit var agregarCarritoBtn: Button
    private lateinit var equipoName: TextView
    private lateinit var rivalName: TextView
    private lateinit var spinner: Spinner
    private lateinit var partido: Partido

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_ticket_detail, container, false)
        agregarCarritoBtn = v.findViewById(R.id.agregarCarritoBtn)
        equipoName = v.findViewById(R.id.equipoName)
        rivalName = v.findViewById(R.id.rivalName)
        spinner = v.findViewById(R.id.spinner)
        partido = TicketDetailArgs.fromBundle(requireArguments()).partido
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        val contextView = v
        equipoName.text = "Velez"
        rivalName.text = partido.rival

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            (activity as MainActivity),
            R.array.sectores_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        agregarCarritoBtn.setOnClickListener {

            if (quedaLugar(spinner.selectedItem.toString(), partido)){
                (spinner.selectedView as TextView).error = null
                spinner.isFocusable= false
                try {
                    var ticket = viewModel.generateTicket(true, partido, spinner.selectedItem.toString(), UserSingleton.getDni())
                    if(ticket != null){
                        val action = TicketDetailDirections.actionTicketDetailToConfirmarCompra(ticket, partido)
                        v.findNavController().navigate(action)
                    }
                }catch (_: Exception){}

            }else{
                (spinner.selectedView as TextView).error = "No hay lugar en el sector seleccionado"
                spinner.isFocusable= true
                Snackbar.make(contextView,"No hay lugar en el sector seleccionado",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun quedaLugar(idSector: String, partido: Partido) : Boolean {
        var cantidad = 0

        if(idSector.equals("Norte")){
            cantidad = partido.sectorNorte
        }
        else if(idSector.equals("Este")){
            cantidad = partido.sectorEste
        }
        else if(idSector.equals("Sur Baja")){
            cantidad = partido.sectorSurBaja
        }
        else if(idSector.equals("Sur Alta")){
            cantidad = partido.sectorSurAlta
        }
        else if(idSector.equals("Oeste")){
            cantidad = partido.sectorOeste
        }
        else{
            cantidad = partido.sectorVisitante
        }

        return cantidad > 0
    }
}