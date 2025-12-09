package com.example.plataforma_extraescolares

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TokenStorage.init(this)
    }
}