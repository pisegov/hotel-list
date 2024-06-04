package com.myaxa.hotel_list_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.domain.HotelRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelListViewModel @Inject constructor(
    private val repository: HotelRepository,
) : ViewModel() {
    val listFlow = repository.hotelsFlow

    fun loadHotelList() {
        viewModelScope.launch {
            repository.loadHotels()
        }
    }
}