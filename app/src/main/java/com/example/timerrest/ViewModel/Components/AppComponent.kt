package com.example.timerrest.ViewModel.Components

import com.example.timerrest.Model.Modules.NetworkModule
import com.example.timerrest.Model.Modules.StorageModule
import com.example.timerrest.Model.Objects.DatabaseHelper
import com.example.timerrest.Model.Objects.NetworkUtils
import com.example.timerrest.TimerActivity
import dagger.Component

@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {
    fun getDatabaseHelper(): DatabaseHelper
    fun getNetworkUtils(): NetworkUtils

    //fun injectMainActivity(timerActivity: TimerActivity)
}