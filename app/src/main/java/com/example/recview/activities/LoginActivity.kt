package com.example.recview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recview.R
import com.example.recview.fragments.login.Login


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                if (fragment is Login) {

                } else {
                    super.onBackPressed()
                }
            }
        }
    }

}