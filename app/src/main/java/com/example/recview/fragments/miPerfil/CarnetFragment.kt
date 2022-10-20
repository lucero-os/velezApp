package com.example.recview.fragments.miPerfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recview.R
import com.example.recview.adapters.PartidosAdapter
import com.example.recview.entities.Partido
import com.example.recview.entities.User
import com.example.recview.fragments.VentaEntradasFragmentDirections
import com.example.recview.viewmodels.miPerfil.CarnetViewModel

class CarnetFragment : Fragment() {

    companion object {
        fun newInstance() = CarnetFragment()
    }
    lateinit var v: View
    private var myAccount : MutableList<User> = ArrayList<User>()

    private val viewModel: CarnetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       v = inflater.inflate(R.layout.fragment_carnet, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // viewModel = ViewModelProvider(this).get(CarnetViewModel::class.java)
        // TODO: Use the ViewModel
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    override fun onStart() {
        super.onStart()
        myAccount = viewModel.getUser()

    }

}