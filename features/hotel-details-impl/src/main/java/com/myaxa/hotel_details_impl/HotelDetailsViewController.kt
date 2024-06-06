package com.myaxa.hotel_details_impl

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.myaxa.common.collectOnLifecycle
import com.myaxa.hotel_details_impl.databinding.FragmentHotelDetailsBinding
import javax.inject.Inject

internal class HotelDetailsViewController @Inject constructor(
    private val fragment: Fragment,
    private val viewModel: HotelDetailsViewModel,
    private val binding: FragmentHotelDetailsBinding,
    private val lifecycleOwner: LifecycleOwner,
){
    fun setUpViews() {
        setUpScreenStateObserver()
    }

    private fun setUpScreenStateObserver() {
        viewModel.hotelFlow.collectOnLifecycle(lifecycleOwner) {
            it.hotel?.run {
                binding.id.text = name
            }

            binding.progressBar.isVisible = it.isLoading
        }
    }
}
