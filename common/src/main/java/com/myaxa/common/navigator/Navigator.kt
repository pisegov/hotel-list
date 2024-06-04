package com.myaxa.common.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

class Navigator(private val containerId: Int){
    fun navigateToFragment(
        fragmentManager: FragmentManager,
        destinationFragment: Fragment,
        tag: String? = destinationFragment.tag
    ) {
        fragmentManager.commit {
            replace(containerId, destinationFragment, tag)
            addToBackStack(tag)
        }
    }
}