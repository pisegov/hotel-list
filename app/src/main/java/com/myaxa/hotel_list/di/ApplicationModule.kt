package com.myaxa.hotel_list.di

import android.content.Context
import com.myaxa.common.navigator.Navigator
import com.myaxa.data.HotelRepositoryImpl
import com.myaxa.database.LocalDataSource
import com.myaxa.domain.HotelRepository
import com.myaxa.hotel_list.BuildConfig
import com.myaxa.hotel_list.R
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

        @Provides
        fun provideNavigator(): Navigator = Navigator(R.id.fragment_container)
    }

    @Binds
    fun bindHotelRepository(impl: HotelRepositoryImpl): HotelRepository
}