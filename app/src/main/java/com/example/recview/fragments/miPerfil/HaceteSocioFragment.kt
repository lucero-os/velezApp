package com.example.recview.fragments.miPerfil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
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
    lateinit var checkBoxTermsHaceteSocio: CheckBox

    private var correo: Array<String> = arrayOf("solicitudSocio@velez.com.ar")

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
        checkBoxTermsHaceteSocio = v.findViewById((R.id.checkBoxTermsHaceteSocio))
        return v
    }

    override fun onStart() {
        super.onStart()
        btnHaceteSocio.setOnClickListener(){
            val contextView = v
            val packageManager = requireActivity().packageManager

            val nombre = haceteSocioNombre.text.toString()
            val apellido = haceteSocioApellido.text.toString()
            val mail = haceteSocioMail.text.toString()
            val dni = haceteSocioDni.text.toString()
            val celular = haceteSocioCelular.text.toString()


            var validacionCampos : Boolean = validarCampos(nombre,apellido,mail,dni,celular)

            var solicitudSocio : String = ""

            if(checkBoxTermsHaceteSocio.isChecked){
                try {
                    if (validacionCampos) {
                        solicitudSocio ="Solicitud de socio #000001"+ "\n\n" + "Nombre: " + haceteSocioNombre.text.toString() + "\n" + "Apellido: " + haceteSocioApellido.text.toString() +  "\n"+ "Email: " + haceteSocioMail.text.toString() + "\n"+ "Dni: " + haceteSocioDni.text.toString() + "\n"+ "Celular: " + haceteSocioCelular.text.toString()
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:") // only email apps should handle this
                            putExtra(Intent.EXTRA_EMAIL, correo)
                            putExtra(Intent.EXTRA_SUBJECT, "solicitud de socio")
                            putExtra(Intent.EXTRA_TEXT, solicitudSocio)
                        }
                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        }
                    } else {
                        Snackbar.make(contextView,"Debe completar todos los datos solicitados",Snackbar.LENGTH_SHORT).show()
                    }
                }catch (e: Exception){}

            /*Snackbar.make(contextView, "Tu solicitud ha sido enviada y esta sujeta a aprobacion", Snackbar.LENGTH_SHORT)
                .show()*/
        }else{
                Snackbar.make(contextView, "Debe leer y aceptar los terminos y condiciones", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun validarCampos(n : String, a : String, m : String, d : String, c : String) : Boolean{
        var state = false
        if(haceteSocioNombre.text.toString() != "" && haceteSocioApellido.text.toString() != "" && haceteSocioMail.text.toString() != "" && haceteSocioDni.text.toString() != "" && haceteSocioCelular.text.toString() != "")
        {
            state = true
        }
        return state
    }

}