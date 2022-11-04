package com.example.recview.fragments.miPerfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recview.R
import com.example.recview.adapters.PartidosAdapter
import com.example.recview.entities.Partido
import com.example.recview.entities.User
import com.example.recview.entities.UserSingleton
import com.example.recview.fragments.VentaEntradasFragmentDirections
import com.example.recview.viewmodels.miPerfil.CarnetViewModel

class CarnetFragment : Fragment() {



    companion object {
        fun newInstance() = CarnetFragment()
    }
    lateinit var v: View
    lateinit var nombreSocio: TextView


    private val viewModel: CarnetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       v = inflater.inflate(R.layout.fragment_carnet, container, false)
        nombreSocio = v.findViewById(R.id.nombre_socio)
        return v
    }



    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    override fun onStart() {
        super.onStart()

        nombreSocio.text = UserSingleton.getNombre() + " " + UserSingleton.getApellido()

    }

}