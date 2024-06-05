package com.myaxa.hotel_list_impl

import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.collectOnLifecycle
import com.myaxa.common.navigator.Navigator
import com.myaxa.common.setThrottleClickListener
import com.myaxa.hotel_list_impl.databinding.FragmentHotelListBinding
import com.myaxa.hotel_list_impl.model.SortingType
import javax.inject.Inject

internal class HotelListViewController @Inject constructor(
    private val fragment: Fragment,
    private val viewModel: HotelListViewModel,
    private val binding: FragmentHotelListBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val navigator: Navigator,
    private val hotelListAdapter: HotelListAdapter,
    private val spaceItemDecoration: SpaceItemDecoration,
) {

    private val context get() = fragment.requireContext()
    fun setUpViews() {

        setUpHotelList()

        setUpSortingButton()

        setUpScreenStateObserver()
    }

    private fun setUpSortingButton() {
        binding.sortingButton.setThrottleClickListener {
            showSortingMenu(R.menu.sorting)
        }
    }

    private fun setUpHotelList() = with(binding.hotelList) {
        adapter = hotelListAdapter
        addItemDecoration(spaceItemDecoration)
        layoutManager = LinearLayoutManager(fragment.requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpScreenStateObserver() {
        viewModel.screenStateFlow.collectOnLifecycle(lifecycleOwner) {
            hotelListAdapter.submitList(it.hotelList) {
                binding.hotelList.scrollToPosition(0)
            }

            binding.progressBar.isVisible = it.isLoading

            binding.sortingLabel.isVisible = it.sortingType != SortingType.NONE
            if (it.sortingType != SortingType.NONE) {
                binding.sortingLabel.text = context.getString(R.string.sorting, it.sortingType.text)
            }
        }
    }

    private fun showSortingMenu(@MenuRes menuRes: Int) {
        val popup = PopupMenu(context, binding.sortingButton)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener {
            val menu = popup.menu
            val sortingType = when (it) {
                menu[1] -> SortingType.BY_DISTANCE
                menu[2] -> SortingType.BY_FREE_ROOMS_NUMBER
                else -> SortingType.NONE
            }

            viewModel.sortHotelList(sortingType)
            true
        }

        popup.show()
    }
}
