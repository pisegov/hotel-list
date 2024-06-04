package com.myaxa.hotel_list_impl

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.myaxa.common.collectOnLifecycle
import com.myaxa.common.navigator.Navigator
import com.myaxa.hotel_list_impl.databinding.FragmentHotelListBinding
import javax.inject.Inject

internal class HotelListViewController @Inject constructor(
    private val fragment: Fragment,
    private val viewModel: HotelListViewModel,
    private val binding: FragmentHotelListBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val navigator: Navigator,
) {

    fun setUpViews() {
        viewModel.loadHotelList()

        viewModel.listFlow.collectOnLifecycle(lifecycleOwner) {

        }
    }
}
