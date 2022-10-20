package com.example.recview.viewmodels.buyTicket

import androidx.lifecycle.ViewModel
import java.util.*

class ConfirmarCompraViewModel : ViewModel() {

    private var resultadoCompra = false

    object DebitCardConstants {
        const val DEBIT_CARD_LENGTH = 16
        const val CVV_LENGTH = 3
    }

    fun resultadoCompra(debitCardNumber: String, cvv: Int): Boolean{
        //hay que investigar tema fechas y su validación
        return validateCard(debitCardNumber, cvv)
    }

    private fun validateCard(debitCardNumber: String, cvv: Int): Boolean{

        return validateDebitCardNumber(debitCardNumber) && validateCvv(cvv)
    }

    private fun validateDebitCardNumber(debitCardNumber: String) : Boolean{

        return debitCardNumber.length == DebitCardConstants.DEBIT_CARD_LENGTH
    }

    private fun validateCvv(cvv: Int) : Boolean{

        return cvv.toString().length == DebitCardConstants.CVV_LENGTH
    }
}