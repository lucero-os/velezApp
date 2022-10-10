package com.example.recview.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recview.R

class BuyTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket)
    }

    fun getEquipo(): String? {
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        val sh: SharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        // We can then use the data
        return sh.getString("equipo", "")
    }

    fun getRival(): String? {
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        val sh: SharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        // We can then use the data
        return sh.getString("rival", "")
    }
}