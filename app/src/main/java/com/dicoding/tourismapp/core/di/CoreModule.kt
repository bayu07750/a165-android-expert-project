package com.dicoding.tourismapp.core.di

import androidx.room.Room
import com.dicoding.tourismapp.core.data.TourismRepository
import com.dicoding.tourismapp.core.data.source.local.LocalDataSource
import com.dicoding.tourismapp.core.data.source.local.room.TourismDatabase
import com.dicoding.tourismapp.core.data.source.remote.RemoteDataSource
import com.dicoding.tourismapp.core.data.source.remote.network.ApiConfig
import com.dicoding.tourismapp.core.domain.repository.ITourismRepository
import com.dicoding.tourismapp.core.utils.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TourismDatabase::class.java,
            "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }

    single {
        get<TourismDatabase>().tourismDao()
    }
}

val networkModule = module {
    single {
        ApiConfig.provideOkHttpClient()
    }

    single {
        ApiConfig.provideApiService(get())
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITourismRepository> { TourismRepository(get(), get(), get()) }
}