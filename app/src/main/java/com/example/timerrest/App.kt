package com.example.timerrest

import android.app.Application
import com.example.timerrest.ViewModel.Components.AppComponent
import com.example.timerrest.ViewModel.Components.DaggerAppComponent

class App: Application() {
    //lateinit var appComponent: AppComponent
    val appComponent = DaggerAppComponent.create()
}