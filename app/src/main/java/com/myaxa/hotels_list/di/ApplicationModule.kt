package com.myaxa.hotels_list.di

import android.content.Context
import com.myaxa.data.HotelRepositoryImpl
import com.myaxa.database.LocalDataSource
import com.myaxa.domain.HotelRepository
import com.myaxa.hotels_list.BuildConfig
import com.myaxa.network.NetworkDataSource
import com.myaxa.network.RetrofitModule
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface ApplicationModule {
    companion object {
        @Provides
        fun provideRetrofitModule(): RetrofitModule {
            return RetrofitModule(BuildConfig.BASE_URL)
        }

        @Provides
        fun provideNetworkDataSource(retrofitModule: RetrofitModule): NetworkDataSource {
            return NetworkDataSource(retrofitModule)
        }

        @Provides
        fun provideLocalDataSource(applicationContext: Context): LocalDataSource {
            return LocalDataSource(applicationContext)
        }
    }

    @Binds
    fun bindHotelRepository(impl: HotelRepositoryImpl): HotelRepository
}