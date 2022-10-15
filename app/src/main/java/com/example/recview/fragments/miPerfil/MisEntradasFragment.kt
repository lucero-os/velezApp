package com.example.recview.fragments.miPerfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recview.R
import com.example.recview.adapters.EntradasAdapter
import com.example.recview.entities.EntradaHist
import com.example.recview.viewmodels.miPerfil.MisEntradasViewModel


class MisEntradasFragment : Fragment() {

    companion object {
        fun newInstance() = MisEntradasFragment()
    }

    private lateinit var viewModel: MisEntradasViewModel
    lateinit var v : View


    var entradasList : MutableList<EntradaHist> = mutableListOf()
    lateinit var adapter : EntradasAdapter
    lateinit var recyclerView: RecyclerView
    //lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_mis_entradas, container, false)

        recyclerView = v.findViewById(R.id.recEntradas)
        //imageView = v.findViewById(R.id.imagen)

        entradasList.add(EntradaHist("01/02/2022","River", "campeonato local", "https://cloud10.todocoleccion.online/entradas-futbol/tc/2016/03/22/10/55228685.jpg"))
        entradasList.add(EntradaHist("01/02/2022","Boca", "campeonato local", "https://www.finanzzas.com/wp-content/uploads/precios-entradas-Liga-de-F%C3%BAtbol-Profesional.jpg"))
        entradasList.add(EntradaHist("01/02/2022","Flamengo", "copa libertadores", "https://e00-marca.uecdn.es/assets/multimedia/imagenes/2017/05/18/14951392754691.png"))
        entradasList.add(EntradaHist("01/02/2022","River", "campeonato local", "https://cloud10.todocoleccion.online/entradas-futbol/tc/2016/03/22/10/55228685.jpg"))
        entradasList.add(EntradaHist("01/02/2022","Boca", "campeonato local", "https://www.finanzzas.com/wp-content/uploads/precios-entradas-Liga-de-F%C3%BAtbol-Profesional.jpg"))
        entradasList.add(EntradaHist("01/02/2022","Flamengo", "copa libertadores", "https://e00-marca.uecdn.es/assets/multimedia/imagenes/2017/05/18/14951392754691.png"))

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisEntradasViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = EntradasAdapter(entradasList)

        recyclerView.adapter = adapter

  /*      Glide
            .with(v)
            .load("https://e00-marca.uecdn.es/assets/multimedia/imagenes/2017/05/18/14951392754691.png")
            .centerCrop()
            //.placeholder(R.drawable.loading_spinner)
            .into(imageView);*/

    }


}