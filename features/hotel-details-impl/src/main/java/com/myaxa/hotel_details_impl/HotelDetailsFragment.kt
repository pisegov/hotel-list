package com.myaxa.hotel_details_impl

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.myaxa.domain.HotelId
import com.myaxa.hotel_details_impl.di.HotelDetailsDependenciesProvider

internal class HotelDetailsFragment : Fragment(R.layout.fragment_hotel_details) {

    companion object {

        private const val HOTEL_ID_KEY = "hotel_id"

        fun newInstance(id: HotelId): HotelDetailsFragment {
            return HotelDetailsFragment().apply {
                arguments = bundleOf(HOTEL_ID_KEY to id)
            }
        }
    }

    private val viewModelFactory
        get() = (requireActivity().applicationContext as HotelDetailsDependenciesProvider)
            .provideHotelDetailsDependencies()
            .viewModelFactory

    private val viewModel: HotelDetailsViewModel by viewModels(
        extrasProducer = {

            val id = arguments?.getLong(HOTEL_ID_KEY)
                ?: throw NoSuchElementException("No hotel id found in arguments")

            MutableCreationExtras().apply {
                set(HotelDetailsViewModel.CREATION_EXTRA_HOTEL_ID_KEY, id)
            }
        },
        factoryProducer = { viewModelFactory }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getLong(HOTEL_ID_KEY)?.let {
            view.findViewById<TextView>(R.id.id).text = it.toString()
        }
    }
}