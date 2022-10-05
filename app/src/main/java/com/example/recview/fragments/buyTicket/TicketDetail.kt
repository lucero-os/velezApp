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
import com.example.recview.activities.BuyTicketActivity
import com.example.recview.viewmodels.buyTicket.TicketDetailViewModel

class TicketDetail : Fragment() {

    companion object {
        fun newInstance() = TicketDetail()
    }

    private lateinit var viewModel: TicketDetailViewModel
    private lateinit var v: View
    private lateinit var agregarCarritoBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_ticket_detail, container, false)
        agregarCarritoBtn = v.findViewById(R.id.agregarCarritoBtn)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        agregarCarritoBtn.setOnClickListener {

            val action = TicketDetailDirections.actionTicketDetailToConfirmarCompra()
            v.findNavController().navigate(action)
        }
    }
}