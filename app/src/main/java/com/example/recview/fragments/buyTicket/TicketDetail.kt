package com.example.recview.fragments.buyTicket

import android.content.SharedPreferences
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
import com.example.recview.activities.BuyTicketActivity
import com.example.recview.activities.MainActivity
import com.example.recview.viewmodels.buyTicket.TicketDetailViewModel


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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_ticket_detail, container, false)
        agregarCarritoBtn = v.findViewById(R.id.agregarCarritoBtn)
        equipoName = v.findViewById(R.id.equipoName)
        rivalName = v.findViewById(R.id.rivalName)
        spinner = v.findViewById(R.id.spinner)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        equipoName.text = (activity as BuyTicketActivity).getEquipo()
        rivalName.text = (activity as BuyTicketActivity).getRival()

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            (activity as BuyTicketActivity),
            R.array.sectores_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        agregarCarritoBtn.setOnClickListener {

            val action = TicketDetailDirections.actionTicketDetailToConfirmarCompra()
            v.findNavController().navigate(action)
        }
    }
}