package com.example.recview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recview.R
import com.example.recview.entities.PagosHist

class PagosAdapter(var pagoList: MutableList<PagosHist>) :
    RecyclerView.Adapter<PagosAdapter.PagoHolder>() {

    class PagoHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View

        init {
            this.view = v
        }

        fun setFecha(fecha: String) {
            val txt: TextView = view.findViewById(R.id.txtFecha)
            txt.text = fecha
        }

        fun setMonto(monto: Int) {
            val txt: TextView = view.findViewById(R.id.txtMonto)
            txt.text = monto.toString()
        }

        fun getCardLayout(): CardView {
            return view.findViewById(R.id.card_package_item)
        }

        fun getCardView(): CardView {
            return view.findViewById(R.id.card_package_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pago, parent, false)
        return (PagosAdapter.PagoHolder(view))
    }

    override fun onBindViewHolder(holder: PagoHolder, position: Int) {
        holder.setFecha(pagoList[position].fecha)
        holder.setMonto(pagoList[position].monto)
    }

    override fun getItemCount(): Int {
        return pagoList.size
    }

}