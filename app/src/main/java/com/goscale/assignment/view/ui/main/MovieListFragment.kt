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
import com.goscale.assignment.view.adapter.ShowListAdapter
import com.goscale.assignment.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        val movieListAdapter = ShowListAdapter(emptyList())
        recyclerViewMovies.adapter = movieListAdapter

        movieViewModel.movies.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    result.data?.let { movieListAdapter.updateShowsData(it) }
                }
                Result.Status.LOADING -> progressBar.visibility = View.VISIBLE
                Result.Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Snackbar.make(view, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        movieViewModel.updateMovieName(Constant.DEFAULT_MOVIE_NAME)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieViewModel.cancelJobs()
    }
}
