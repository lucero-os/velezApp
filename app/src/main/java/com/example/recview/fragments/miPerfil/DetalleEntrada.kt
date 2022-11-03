package com.example.recview.fragments.miPerfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.recview.R
import com.example.recview.entities.Ticket
import com.example.recview.viewmodels.miPerfil.DetalleEntradaViewModel

class DetalleEntrada : Fragment() {

    companion object {
        fun newInstance() = DetalleEntrada()
    }

    private lateinit var v: View
    private lateinit var viewModel: DetalleEntradaViewModel
    private lateinit var equipoVsRival: TextView
    private lateinit var fecha: TextView
    private lateinit var torneo: TextView
    private lateinit var socioNombre: TextView
    private lateinit var dni: TextView
    private lateinit var sectorSocio: TextView
    private lateinit var valorEntrada: TextView
    private lateinit var ticket : Ticket

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_detalle_entrada, container, false)
        equipoVsRival =  v.findViewById(R.id.equipoVsRival)
        fecha = v.findViewById(R.id.fecha)
        torneo =  v.findViewById(R.id.torneo)
        socioNombre =  v.findViewById(R.id.socioNombre)
        dni = v.findViewById(R.id.dniSocio)
        sectorSocio =  v.findViewById(R.id.sectorSocio)
        valorEntrada = v.findViewById(R.id.valorEntrada)
        ticket = DetalleEntradaArgs.fromBundle(requireArguments()).ticket
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetalleEntradaViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        equipoVsRival.text = getString(R.string.texto_vs, ticket.equipo, ticket.rival)
        fecha.text = getString(R.string.texto_fecha, ticket.dia, ticket.mes)
        torneo.text = ticket.titulo
        //cuando se autentique el usuario sacamos el nombre de ahi
        socioNombre.text = ""
        dni.text = ticket.idUser.toString()
        sectorSocio.text = ticket.idSector
        valorEntrada.text = ticket.valor.toString()
    }

}