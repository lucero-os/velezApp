package com.example.recview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recview.R
import com.example.recview.entities.Ticket

class EntradasAdapter(
    var entradaList: MutableList<Ticket>,
    val onClick : (Int) -> Unit
        ) : RecyclerView.Adapter<EntradasAdapter.EntradaHolder>() {

    class EntradaHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View

        init {
            this.view = v
        }

        fun setEquipoRival(name: String) {
            val txt: TextView = view.findViewById(R.id.equipoRival)
            txt.text = name
        }

        fun setValor(valor: Double) {
            val txt: TextView = view.findViewById(R.id.fecha)
            txt.text = valor.toString()
        }

        fun setTorneo(name: String) {
            val txt: TextView = view.findViewById(R.id.torneo)
            txt.text = name

        }

        fun getCardLayout(): CardView {
            return view.findViewById(R.id.card_package_item)
        }

        fun getCardView(): CardView {
            return view.findViewById(R.id.card_package_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntradaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entrada, parent, false)
        return (EntradasAdapter.EntradaHolder(view))
    }

    override fun onBindViewHolder(holder: EntradaHolder, position: Int) {
        //holder.setImagen(entradaList[position].imagen)
        holder.setEquipoRival(entradaList[position].rival)
        holder.setValor(entradaList[position].valor)
        holder.setTorneo(entradaList[position].titulo)

        holder.getCardView().setOnClickListener {
            onClick(position)
        }

    }

    override fun getItemCount(): Int {
        return entradaList.size
    }
}