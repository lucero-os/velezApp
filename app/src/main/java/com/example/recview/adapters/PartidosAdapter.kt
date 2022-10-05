package com.example.recview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recview.R
import com.example.recview.entities.Partido

class PartidosAdapter (private var partidosList: MutableList<Partido>) :
                        RecyclerView.Adapter<PartidosAdapter.PartidoHolder>() {

    class PartidoHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View

        init {
            this.view = v
        }

        fun setName(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }

        fun setRival(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_rival_item)
            txt.text = name
        }

        fun getCardLayout(): CardView {
            return view.findViewById(R.id.card_package_item)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_partido, parent, false)
        return (PartidoHolder(view))
    }

    override fun onBindViewHolder(holder: PartidoHolder, position: Int) {

        holder.setName(partidosList[position].equipo)
        holder.setRival(partidosList[position].rival)

    }

    override fun getItemCount(): Int {
       return partidosList.size
    }

}