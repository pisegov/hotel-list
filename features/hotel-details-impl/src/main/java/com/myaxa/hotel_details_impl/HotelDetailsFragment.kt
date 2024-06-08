package com.myaxa.hotel_details_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.CreationExtras
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

    private val dependencies
        get() = (requireActivity().applicationContext as HotelDetailsDependenciesProvider)
            .provideHotelDetailsDependencies()

    private val viewModel: HotelDetailsViewModel by viewModels(
        extrasProducer = { provideHotelIdToViewModel() },
        factoryProducer = { dependencies.viewModelFactory }
    )

    private val fragmentComponent: HotelDetailsFragmentComponent by unsafeLazy {
        DaggerHotelDetailsFragmentComponent.factory().create(
            fragment = this,
            viewModel = viewModel,
            imageLoader = dependencies.imageLoader
        )
    }

    private var viewComponent: HotelDetailsViewComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            viewModel.initStateFlow()
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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

    private fun provideHotelIdToViewModel(): CreationExtras {

        val id = arguments?.getLong(HOTEL_ID_KEY)
            ?: throw NoSuchElementException("No hotel id found in arguments")

        return MutableCreationExtras().apply {
            set(HotelDetailsViewModel.CREATION_EXTRA_HOTEL_ID_KEY, id)
        }
    }
}