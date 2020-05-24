package com.goscale.assignment.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.goscale.assignment.R
import com.goscale.assignment.di.Injectable
import com.goscale.assignment.view.adapter.HomePagerAdapter
import com.goscale.assignment.view.adapter.MOVIE_LIST_PAGE_INDEX
import com.goscale.assignment.view.adapter.TV_SHOW_LIST_PAGE_INDEX
import com.goscale.assignment.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_home_view_pager.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class HomeViewPagerFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        println("============$movieViewModel=============")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view_pager.adapter = HomePagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabs, view_pager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MOVIE_LIST_PAGE_INDEX -> R.drawable.movie_list_tab_selector
            TV_SHOW_LIST_PAGE_INDEX -> R.drawable.tv_show_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MOVIE_LIST_PAGE_INDEX -> getString(R.string.movies_title)
            TV_SHOW_LIST_PAGE_INDEX -> getString(R.string.tv_shows_title)
            else -> null
        }
    }
}
