package com.myaxa.hotel_details_impl

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.myaxa.domain.HotelId

internal class HotelDetailsFragment : Fragment(R.layout.fragment_hotel_details) {

    companion object {

        private const val HOTEL_ID_KEY = "hotel_id"

        fun newInstance(id: HotelId): HotelDetailsFragment {
            return HotelDetailsFragment().apply {
                arguments = bundleOf(HOTEL_ID_KEY to id)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getLong(HOTEL_ID_KEY)?.let {
            view.findViewById<TextView>(R.id.id).text = it.toString()
        }
    }
}