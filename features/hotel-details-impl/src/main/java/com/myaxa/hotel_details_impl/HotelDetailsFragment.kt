package com.myaxa.hotel_details_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.myaxa.common.unsafeLazy
import com.myaxa.domain.HotelId
import com.myaxa.hotel_details_impl.databinding.FragmentHotelDetailsBinding
import com.myaxa.hotel_details_impl.di.DaggerHotelDetailsFragmentComponent
import com.myaxa.hotel_details_impl.di.DaggerHotelDetailsViewComponent
import com.myaxa.hotel_details_impl.di.HotelDetailsDependenciesProvider
import com.myaxa.hotel_details_impl.di.HotelDetailsFragmentComponent
import com.myaxa.hotel_details_impl.di.HotelDetailsViewComponent

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

    private val fragmentComponent: HotelDetailsFragmentComponent by unsafeLazy {
        DaggerHotelDetailsFragmentComponent.factory().create(
            fragment = this,
            viewModel = viewModel,
        )
    }

    private var viewComponent: HotelDetailsViewComponent? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHotelDetailsBinding.inflate(inflater, container, false)

        viewComponent = DaggerHotelDetailsViewComponent.factory().create(
            fragmentComponent = fragmentComponent,
            binding = binding,
            lifecycleOwner = viewLifecycleOwner,
        ).apply {
            viewController.setUpViews()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewComponent = null
    }
}