package com.example.tmm.views.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tmm.R
import com.example.tmm.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setupNavController()
    }

    private fun setupNavController()
    {
        val navView: BottomNavigationView = _binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        //No action bar in this app
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//        setupActionBarWithNavController(navController)
        navView.setupWithNavController(navController)
    }
}