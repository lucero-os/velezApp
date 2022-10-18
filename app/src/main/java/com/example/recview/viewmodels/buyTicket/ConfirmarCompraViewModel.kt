package com.example.recview.viewmodels.buyTicket

import androidx.lifecycle.ViewModel

class ConfirmarCompraViewModel : ViewModel() {

    private var resultadoCompra = false

    fun resultadoCompra(): Boolean{
        return this.resultadoCompra
    }
}