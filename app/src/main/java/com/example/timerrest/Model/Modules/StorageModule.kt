package com.example.timerrest.Model.Modules

import com.example.timerrest.Model.Objects.DatabaseHelper
import dagger.Module
import dagger.Provides

@Module
class StorageModule {
    @Provides
    fun provideDatabaseHelper(): DatabaseHelper{
        return DatabaseHelper()
    }
}