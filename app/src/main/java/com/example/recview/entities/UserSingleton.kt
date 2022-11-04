package com.example.recview.entities

object UserSingleton {

    private var user: User = User()

    fun setUser(u: User?){
        if(u != null){
            user.nombre = u.nombre
            user.apellido = u.apellido
            user.email = u.email
            user.dni = u.dni
            user.telefono = u.telefono
        }
    }

    fun getNombre() : String?{
        return user.nombre
    }

    fun getApellido() : String?{
        return user.apellido
    }

    fun getDni() : Int?{
        return user.dni
    }

    fun getEmail() : String?{
        return user.email
    }

    fun getTelefono(): String?{
        return user.telefono
    }
}