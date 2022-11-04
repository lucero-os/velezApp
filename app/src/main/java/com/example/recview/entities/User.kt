package com.example.recview.entities

class User (
            var apellido: String? = "",
            var dni: Int? = 0,
            var email: String? = "",
            var nombre: String? = "",
            var telefono: String? = ""
){
    constructor() : this("",0,"","","")

    override fun toString(): String {
        return "Usuario ($nombre)"
    }
}


