package com.myaxa.hotel_details_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.myaxa.common.ViewModelCreationExtraKey
import com.myaxa.domain.HotelId
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class HotelDetailsViewModel @AssistedInject constructor(
    @Assisted(HOTEL_ID_KEY) private val hotelId: HotelId,
) : ViewModel() {

    @AssistedFactory
    interface DaggerAssistedFactory {
        fun create(@Assisted(HOTEL_ID_KEY) hotelId: HotelId): HotelDetailsViewModel
    }

    companion object {
        private const val HOTEL_ID_KEY = "hotel_id"
        internal val CREATION_EXTRA_HOTEL_ID_KEY = ViewModelCreationExtraKey<HotelId>()
    }
}

@Suppress("UNCHECKED_CAST")
class HotelDetailsViewModelFactory @Inject constructor(
    private val daggerAssistedFactory: HotelDetailsViewModel.DaggerAssistedFactory,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

        val hotelId = extras[HotelDetailsViewModel.CREATION_EXTRA_HOTEL_ID_KEY]
            ?: throw NoSuchElementException()

        return daggerAssistedFactory.create(hotelId) as T
    }
}