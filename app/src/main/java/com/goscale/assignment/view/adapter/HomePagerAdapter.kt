package com.goscale.assignment.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.goscale.assignment.view.ui.main.MovieListFragment
import com.goscale.assignment.view.ui.main.TvShowListFragment

const val MOVIE_LIST_PAGE_INDEX = 0
const val TV_SHOW_LIST_PAGE_INDEX = 1

/**
 * Created by Prasad Rao on 23-05-2020 16:58
 **/
class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MOVIE_LIST_PAGE_INDEX to { MovieListFragment() },
        TV_SHOW_LIST_PAGE_INDEX to { TvShowListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}