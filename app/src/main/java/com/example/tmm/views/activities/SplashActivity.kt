package com.example.tmm.views.activities

import android.content.Context
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
import com.example.tmm.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        Glide.with(this)
            .asGif()
            .load(R.drawable.four)
            .into(binding!!.gifImage)

        setValuesToLimits()
        setAnimations()
    }

    private fun setValuesToLimits() {
        val sharedPref = getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
        val limitCharacter = sharedPref.getString(Constants.SP_CHARACTERS, "15")
        val limitCreator = sharedPref.getString(Constants.SP_CREATORS, "15")
        val limitComic = sharedPref.getString(Constants.SP_COMICS, "15")
        val limitEvent = sharedPref.getString(Constants.SP_EVENTS, "15")
        val limitSeries = sharedPref.getString(Constants.SP_SERIES, "15")
        Constants.LIMIT_VALUE_FOR_CHARACTERS = limitCharacter?:"15"
        Constants.LIMIT_VALUE_FOR_CREATORS = limitCreator?:"15"
        Constants.LIMIT_VALUE_FOR_COMICS = limitComic?:"15"
        Constants.LIMIT_VALUE_FOR_EVENTS = limitEvent?:"15"
        Constants.LIMIT_VALUE_FOR_SERIES = limitSeries?:"15"
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


