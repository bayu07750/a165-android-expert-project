package com.dicoding.tourismapp

import android.app.Application
import com.bayu07750.tourismapp.core.di.databaseModule
import com.bayu07750.tourismapp.core.di.networkModule
import com.bayu07750.tourismapp.core.di.repositoryModule
import com.dicoding.tourismapp.di.viewModelModule
import com.dicoding.tourismapp.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.NONE)
            androidContext(this@App)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }
}