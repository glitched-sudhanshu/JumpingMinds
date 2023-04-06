package com.example.tmm.views.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.tmm.R
import com.example.tmm.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        Glide.with(this)
            .asGif()
            .load(R.drawable.four)
            .into(binding!!.gifImage)

        setAnimations()
    }


    override fun onDestroy() {
        super.onDestroy()
        // Set binding object to null to avoid memory leaks
        binding = null
    }

    private fun setAnimations() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        binding!!.imgLogo.startAnimation(animation)
        binding!!.txtMarvel.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                /**
                 * Handler class offers us to handle things with a delay
                 */
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                    finish()
                }, 200)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
    }


}


