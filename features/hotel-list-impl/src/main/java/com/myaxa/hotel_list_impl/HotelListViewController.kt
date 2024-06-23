package com.myaxa.hotel_list_impl

import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.collectOnLifecycle
import com.myaxa.common.setThrottleClickListener
import com.myaxa.hotel_list_impl.databinding.FragmentHotelListBinding
import com.myaxa.hotel_list_impl.model.ScreenState
import com.myaxa.hotel_list_impl.model.SortingType
import javax.inject.Inject

internal class HotelListViewController @Inject constructor(
    private val fragment: Fragment,
    private val viewModel: HotelListViewModel,
    private val binding: FragmentHotelListBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val hotelListAdapter: HotelListAdapter,
    private val spaceItemDecoration: SpaceItemDecoration,
) {

    private val context get() = fragment.requireContext()
    fun setUpViews() {

        setUpHotelList()

        setUpSwipeToRefresh()

        setUpSortingButton()

        setUpScreenStateObserver()
    }

    private fun setUpHotelList() = with(binding.hotelList) {
        adapter = hotelListAdapter
        addItemDecoration(spaceItemDecoration)
        layoutManager = LinearLayoutManager(fragment.requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpSwipeToRefresh() = with(binding.swipeToRefresh) {
        setOnRefreshListener {
            viewModel.loadHotelList()
            isRefreshing = false
        }
    }

    private fun setUpSortingButton() {
        binding.sortingButton.setThrottleClickListener {
            showSortingMenu(R.menu.sorting)
        }
    }

    private fun setUpScreenStateObserver() {
        viewModel.screenStateFlow.collectOnLifecycle(lifecycleOwner) { state ->
            updateState(state)
        }
    }

    private fun updateState(state: ScreenState) {
        // if the current list is empty then need just to set up it
        // if it is not empty then need to update it and scroll to the top
        val needToKeepScrollState = hotelListAdapter.currentList.isEmpty()

        hotelListAdapter.submitList(state.hotelList) {
            if (!needToKeepScrollState) {
                binding.hotelList.scrollToPosition(0)
            }
        }

        binding.progressBar.isVisible = state.isLoading
        binding.swipeToRefresh.isRefreshing = false

        binding.sortingLabel.isVisible = state.sortingType != SortingType.NONE
        if (state.sortingType != SortingType.NONE) {
            binding.sortingLabel.text = context.getString(R.string.sorting, state.sortingType.text)
        }

        state.errorText?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.setErrorWasShown()
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
