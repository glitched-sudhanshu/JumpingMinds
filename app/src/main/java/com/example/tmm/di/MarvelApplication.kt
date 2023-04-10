package com.example.tmm.di

import android.app.Application
import com.example.tmm.data.data_source.database.MarvelDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelApplication : Application(){
    private val database by lazy{
        MarvelDatabase.getDatabase((this@MarvelApplication))
    }
}