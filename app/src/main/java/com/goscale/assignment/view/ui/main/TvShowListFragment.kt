package com.goscale.assignment.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.goscale.assignment.R
import com.goscale.assignment.common.constant.Constant
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.di.Injectable
import com.goscale.assignment.view.adapter.TvShowListAdapter
import com.goscale.assignment.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_tv_show_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TvShowListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowViewModel = ViewModelProvider(this, viewModelFactory).get(TvShowViewModel::class.java)

        val adapter = TvShowListAdapter(emptyList())
        recyclerViewTvShows.adapter = adapter

        tvShowViewModel.tvShows.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    progressBarTvShows.visibility = View.GONE
                    result.data?.let { adapter.updateTvShowList(it) }
                }
                Result.Status.LOADING -> progressBarTvShows.visibility = View.VISIBLE
                Result.Status.ERROR -> {
                    progressBarTvShows.visibility = View.GONE
                    Snackbar.make(view, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        tvShowViewModel.updateTvShow(Constant.DEFAULT_TV_SHOW_NAME)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tvShowViewModel.cancelJobs()
    }
}
