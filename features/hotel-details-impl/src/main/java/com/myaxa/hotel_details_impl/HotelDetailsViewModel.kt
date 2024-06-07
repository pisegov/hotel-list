package com.myaxa.hotel_details_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.myaxa.common.ViewModelCreationExtraKey
import com.myaxa.domain.HotelId
import com.myaxa.domain.HotelRepository
import com.myaxa.hotel_details_impl.model.ScreenState
import com.myaxa.hotel_details_impl.model.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelDetailsViewModel(
    private val hotelId: HotelId,
    private val repository: HotelRepository,
) : ViewModel() {

    companion object {
        internal val CREATION_EXTRA_HOTEL_ID_KEY = ViewModelCreationExtraKey<HotelId>()
    }

    init {
        repository.getHotelDetailsFlow(hotelId).onEach {
            _stateFlow.tryEmit(ScreenState(hotel = it.toUiModel()))
        }.onStart {
            repository.loadHotelDetails(hotelId)
        }.launchIn(viewModelScope)

        repository.errorFlow.onEach { throwable ->
            _stateFlow.update { state -> state.copy(errorText = throwable.message) }
        }.launchIn(viewModelScope)
    }

    private val _stateFlow = MutableStateFlow(ScreenState(isLoading = true))
    internal val stateFlow = _stateFlow.asStateFlow()

    fun loadHotelDetails() {
        viewModelScope.launch {
            _stateFlow.update { it.copy(isRefreshing = true) }
            repository.loadHotelDetails(hotelId)
            _stateFlow.update { it.copy(isRefreshing = false) }
        }
    }

    fun setErrorWasShown() {
        _stateFlow.update { it.copy(errorText = null) }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val repository: HotelRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

            val hotelId = extras[CREATION_EXTRA_HOTEL_ID_KEY]
                ?: throw NoSuchElementException()

            return HotelDetailsViewModel(hotelId, repository) as T
        }
    }
}
