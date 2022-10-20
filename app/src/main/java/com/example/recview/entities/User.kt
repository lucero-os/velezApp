package com.example.recview.entities

class User (var admin: Boolean? = false,
            var apellido: String? = "",
            var dni: Int? = 0,
            var domicilio: String? = "",
            var email: String? = "",
            var fechaNacimiento: String? = "",
            var id: Int? = 0,
            var nombre: String? = "",
            var sexo: String? = "",
            var socio: Boolean? = false
){
    constructor() : this(false,"",0,"","","",0,"","",false)

    override fun toString(): String {
        return "Usuario ($nombre, DNI='$dni')"
    }
}


