package com.goscale.assignment.view.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import com.goscale.assignment.R
import com.goscale.assignment.common.constant.Constant
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.extension.applyToolbarMargin
import com.goscale.assignment.extension.simpleToolbarWithHome
import com.goscale.assignment.model.Movie
import com.goscale.assignment.viewmodel.MovieDetailViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.layout_movie_detail_body.*
import kotlinx.android.synthetic.main.layout_movie_detail_header.*
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var movieDetailsViewModel: MovieDetailViewModel

    private var movieDetails: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val title = intent.extras?.getString("movie_name", Constant.DEFAULT_MOVIE_NAME)
            ?: Constant.DEFAULT_MOVIE_NAME
        applyToolbarMargin(movie_detail_toolbar)
        simpleToolbarWithHome(movie_detail_toolbar, title)

        movieDetailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        // TODO: Instead of setting explicitly we can use the Data binding
        movieDetailsViewModel.movieDetails.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    progressBarMovieDetails.visibility = View.GONE
                    result.data?.let { movieDetails ->
                        this.movieDetails = movieDetails
                        if (movieDetails.poster.isNotBlank()) {
                            Glide.with(this@MovieDetailsActivity)
                                .load(movieDetails.poster)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(movie_detail_poster)
                        }
                        detail_header_title.text = movieDetails.title
                        val releasedOn = "Release Date : ${movieDetails.releasedOn}"
                        detail_header_release.text = releasedOn

                        detail_header_rating.rating = movieDetails.rating

                        textWriters.text = movieDetails.writer
                        textActors.text = movieDetails.actors
                        textAwards.text = movieDetails.awards
                        textGenre.text = movieDetails.genre
                        textPlot.text = movieDetails.plot

                        fabBookmark.isActivated = movieDetails.isBookmarked
                    }
                }
                Result.Status.LOADING -> progressBarMovieDetails.visibility = View.VISIBLE
                Result.Status.ERROR -> {
                    progressBarMovieDetails.visibility = View.GONE
                    Snackbar.make(parentLayout, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        fabBookmark.setOnClickListener {
            this.movieDetails?.run {
                isBookmarked = !isBookmarked
                fabBookmark.isActivated = isBookmarked
                movieDetailsViewModel.updateBookmarkStatus(title, isBookmarked)
            }
        }

        movieDetailsViewModel.updateMovieName(title)
    }

    override fun onDestroy() {
        super.onDestroy()
        movieDetailsViewModel.cancelJobs()
    }
}
