package com.example.recview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.recview.R
import com.example.recview.entities.User
import com.example.recview.entities.UserSingleton
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment
    private val auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        bottomNavView = findViewById(R.id.bottom_bar)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        val scope = CoroutineScope(
            Job() + Dispatchers.Main
        )
        scope.launch {
            try{
                val usersRef = db.collection("usuarios")
                val currentU = auth.currentUser
                if(currentU != null){
                    val usuario = usersRef.document("/${currentU.uid}").get().await()
                    UserSingleton.setUser(usuario.toObject<User>())
                }
            }
            catch (e: Exception){

            }

        }

    }
}