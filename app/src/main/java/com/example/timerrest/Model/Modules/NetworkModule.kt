package com.example.timerrest.Model.Modules

import com.example.timerrest.Model.Objects.NetworkUtils
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideNetworkUtils(): NetworkUtils{
        return NetworkUtils()
    }
}