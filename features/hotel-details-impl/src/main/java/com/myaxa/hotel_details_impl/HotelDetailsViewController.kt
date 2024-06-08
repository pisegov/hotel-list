package com.myaxa.hotel_details_impl

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import coil.load
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.collectOnLifecycle
import com.myaxa.hotel_details_impl.databinding.FragmentHotelDetailsBinding
import com.myaxa.hotel_details_impl.hotel_details_list.HotelDetailsAdapter
import com.myaxa.hotel_details_impl.model.HotelDetailListItem
import com.myaxa.hotel_details_impl.model.HotelDetailsUi
import com.myaxa.hotel_details_impl.util.CropTransformation
import javax.inject.Inject

internal class HotelDetailsViewController @Inject constructor(
    private val fragment: Fragment,
    private val viewModel: HotelDetailsViewModel,
    private val binding: FragmentHotelDetailsBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val hotelDetailsAdapter: HotelDetailsAdapter,
    private val spaceItemDecoration: SpaceItemDecoration,
    private val imageLoader: ImageLoader,
) {

    private val context get() = fragment.requireContext()

    fun setUpViews() {

        setUpHotelDetailList()

        setUpSwipeToRefresh()

        setUpBackButton()

        setUpScreenStateObserver()
    }

    private fun setUpHotelDetailList() = with(binding.hotelDetailList) {
        adapter = hotelDetailsAdapter
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        addItemDecoration(spaceItemDecoration)
    }

    private fun setUpSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.loadHotelDetails()
        }

        binding.appbar?.addOnOffsetChangedListener { _, verticalOffset ->
            binding.swipeToRefresh.isEnabled = verticalOffset == 0
        }
    }

    private fun setUpBackButton() {
        val expandedColor = ContextCompat.getColor(context, R.color.back_button_color_expanded)
        val collapsedColor = ContextCompat.getColor(context, R.color.back_button_color_collapsed)

        binding.appbar?.addOnOffsetChangedListener { appbar, verticalOffset ->
            binding.backButton.setColorFilter(
                ColorUtils.blendARGB(expandedColor, collapsedColor, -verticalOffset.toFloat() / appbar.totalScrollRange)
            )
        }

        binding.backButton.setOnClickListener {
            fragment.parentFragmentManager.popBackStack()
        }
    }

    private fun setUpScreenStateObserver() {

        viewModel.stateFlow.collectOnLifecycle(lifecycleOwner) { screenState ->

            setItemsVisibility(screenState.isLoading)

            binding.swipeToRefresh.isRefreshing = screenState.isRefreshing

            screenState.hotel?.let { setUpHotelDetails(it) }

            screenState.errorText?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.setErrorWasShown()
            }
        }
    }

    private fun setItemsVisibility(isLoading: Boolean) = with(binding) {

        progressBar.isVisible = isLoading

        listOf(collapsingToolbar, backButton).forEach {
            it?.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        }

        listOf(title, rating, image, hotelDetailList).forEach {
            it?.isVisible = !isLoading
        }
    }

    private fun setUpHotelDetails(hotel: HotelDetailsUi) = with(binding) {

        collapsingToolbar?.title = hotel.name
        title?.text = hotel.name

        image.load(hotel.imageUrl, imageLoader) {
            lifecycle(lifecycleOwner)
            placeholder(R.drawable.hotel_placeholder)
            transformations(CropTransformation())
            error(R.drawable.hotel_placeholder)
        }

        rating.text = hotel.stars.toString()

        setHotelDetailsList(hotel)
    }

    private fun setHotelDetailsList(hotel: HotelDetailsUi) {
        val list = mutableListOf(
            HotelDetailListItem.AddressCard(hotel.address, distanceToTheCenter = hotel.distance.toString()),
            HotelDetailListItem.FreeRoomsItem(rooms = hotel.suitesAvailability),
        )
        if (hotel.latitude != null && hotel.longitude != null) {
            list.add(HotelDetailListItem.CoordinatesCard(hotel.latitude.toString(), hotel.longitude.toString()))
        }
        hotelDetailsAdapter.submitList(list)
    }
}