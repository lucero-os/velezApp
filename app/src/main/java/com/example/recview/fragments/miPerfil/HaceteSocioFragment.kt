package com.example.recview.fragments.miPerfil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.recview.R
import com.example.recview.viewmodels.miPerfil.HaceteSocioViewModel
import com.google.android.material.snackbar.Snackbar


class HaceteSocioFragment : Fragment() {


    lateinit var v : View
    lateinit var btnHaceteSocio: Button
    lateinit var haceteSocioNombre: TextView
    lateinit var haceteSocioApellido: TextView
    lateinit var haceteSocioMail: TextView
    lateinit var haceteSocioDni: TextView
    lateinit var haceteSocioCelular: TextView

    private var correo: Array<String> = arrayOf("ivanra48@gmail.com")

    companion object {
        fun newInstance() = HaceteSocioFragment()
    }

    private lateinit var viewModel: HaceteSocioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_hacete_socio, container, false)
        btnHaceteSocio = v.findViewById(R.id.haceteSocioButton)
        haceteSocioNombre = v.findViewById(R.id.haceteSocioNombre)
        haceteSocioApellido = v.findViewById(R.id.haceteSocioApellido)
        haceteSocioMail = v.findViewById(R.id.haceteSocioMail)
        haceteSocioDni = v.findViewById(R.id.haceteSocioDni)
        haceteSocioCelular = v.findViewById(R.id.haceteSocioCelular)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnHaceteSocio.setOnClickListener(){
            val contextView = v
            val packageManager = requireActivity().packageManager

            var solicitudSocio : String = haceteSocioNombre.text.toString() + "\n" + haceteSocioApellido.text.toString() +  "\n" + haceteSocioMail.text.toString() + "\n" + haceteSocioDni.text.toString() + "\n" + haceteSocioCelular.text.toString()

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, correo)
                putExtra(Intent.EXTRA_SUBJECT, "solicitud de socio")
                putExtra(Intent.EXTRA_TEXT, solicitudSocio)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            Snackbar.make(contextView, "Tu solicitud ha sido enviada y esta sujeta a aprobacion", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

}