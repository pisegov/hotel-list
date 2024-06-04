package com.myaxa.hotels_list

import android.app.Application
import com.myaxa.domain.HotelRepository
import com.myaxa.hotels_list.di.ApplicationComponent
import com.myaxa.hotels_list.di.DaggerApplicationComponent

class HotelsApplication : Application(){
    private val component : ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext = this)
    }
}