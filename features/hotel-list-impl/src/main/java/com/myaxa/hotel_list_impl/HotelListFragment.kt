package com.myaxa.hotel_list_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.myaxa.common.unsafeLazy
import com.myaxa.hotel_details_api.HotelDetailsApiProvider
import com.myaxa.hotel_list_impl.databinding.FragmentHotelListBinding
import com.myaxa.hotel_list_impl.di.DaggerHotelListFragmentComponent
import com.myaxa.hotel_list_impl.di.DaggerHotelListViewComponent
import com.myaxa.hotel_list_impl.di.HotelListDependencies
import com.myaxa.hotel_list_impl.di.HotelListDependenciesProvider
import com.myaxa.hotel_list_impl.di.HotelListFragmentComponent
import com.myaxa.hotel_list_impl.di.HotelListViewComponent

internal class HotelListFragment : Fragment(R.layout.fragment_hotel_list) {

    private val dependencies: HotelListDependencies
        get() =
        (requireActivity().applicationContext as HotelListDependenciesProvider)
            .provideHotelListDependencies()

    private val viewModel: HotelListViewModel by viewModels {
        dependencies.viewModelFactory
    }

    private val fragmentComponent: HotelListFragmentComponent by unsafeLazy {
        DaggerHotelListFragmentComponent.factory().create(
            fragment = this,
            viewModel = viewModel,
            navigator = dependencies.navigator,
            hotelDetailsApi = (requireActivity().applicationContext as HotelDetailsApiProvider).provideHotelDetailsApi()
        )
    }

    private var viewComponent: HotelListViewComponent? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentHotelListBinding.inflate(inflater, container, false)

        viewComponent = DaggerHotelListViewComponent.factory().create(
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