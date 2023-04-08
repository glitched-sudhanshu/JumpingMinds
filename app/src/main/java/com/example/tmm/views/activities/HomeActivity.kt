package com.example.tmm.views.activities

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tmm.R
import com.example.tmm.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var _binding: ActivityHomeBinding
    private var isOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setupNavController()
        _binding.fabMain.setOnClickListener(this)
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
            _binding.fabMain.id -> {
                if(isOpen){
                    scaleOutroAnimation()
                    _binding.fab1.visibility = View.GONE
                    _binding.fab2.visibility = View.GONE
                    _binding.fab3.visibility = View.GONE
                    isOpen = false
                }else{
                    scaleIntroAnimation()
                    _binding.fab1.visibility = View.VISIBLE
                    _binding.fab2.visibility = View.VISIBLE
                    _binding.fab3.visibility = View.VISIBLE
                    isOpen = true
                }
            }
        }

    }

    private fun scaleOutroAnimation() {
        val scaleAnimation = ScaleAnimation(
            1f,  // Start scale X
            0f,  // End scale X
            1f,  // Start scale Y
            0f,  // End scale Y
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot X (50%)
            Animation.RELATIVE_TO_SELF, 0.5f // Pivot Y (50%)
        )

        scaleAnimation.setDuration(500)

        _binding.fab1.startAnimation(scaleAnimation)
        _binding.fab2.startAnimation(scaleAnimation)
        _binding.fab3.startAnimation(scaleAnimation)
    }

    private fun scaleIntroAnimation() {
        val animation = ScaleAnimation(
            0f,  // Start scale X
            1f,  // End scale X
            0f,  // Start scale Y
            1f,  // End scale Y
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot X (50%)
            Animation.RELATIVE_TO_SELF, 0.5f // Pivot Y (50%)
        )


        animation.setDuration(200)

        _binding.fab1.startAnimation(animation)
        _binding.fab2.startAnimation(animation)
        _binding.fab3.startAnimation(animation)
    }
}