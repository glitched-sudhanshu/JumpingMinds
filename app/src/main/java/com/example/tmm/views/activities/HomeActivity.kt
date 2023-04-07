package com.example.tmm.views.activities

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tmm.R
import com.example.tmm.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), View.OnClickListener {

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
        navView.menu.getItem(2).isEnabled = false
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        navView.setupWithNavController(navController)
    }

    override fun onClick(view: View?) {
        when(view!!.id)
        {

        }

    }
}