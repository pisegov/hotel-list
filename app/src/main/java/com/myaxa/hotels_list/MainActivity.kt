package com.myaxa.hotels_list

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.myaxa.common.navigator.NavigatorProvider
import com.myaxa.hotel_list_api.HotelListApiProvider

internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navigateToHotelList()
    }

    private fun navigateToHotelList() {
        val navigator = (applicationContext as NavigatorProvider).provideNavigator()
        val hotelListApi = (applicationContext as HotelListApiProvider).provideHotelListApi()

        navigator.navigateToFragment(supportFragmentManager, hotelListApi.provideHotelListFragment(), "hotel_list")
    }
}