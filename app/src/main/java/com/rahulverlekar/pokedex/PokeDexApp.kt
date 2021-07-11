package com.rahulverlekar.pokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class PokeDexApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}