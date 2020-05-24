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
import com.goscale.assignment.model.Show
import com.goscale.assignment.viewmodel.ShowDetailViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_show_details.*
import kotlinx.android.synthetic.main.layout_movie_detail_body.*
import kotlinx.android.synthetic.main.layout_movie_detail_header.*
import javax.inject.Inject

class ShowDetailsActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var showDetailsViewModel: ShowDetailViewModel

    private var movieDetails: Show? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        val extras = intent.extras
        val title = extras?.getString(Constant.KEY_SHOW_TITLE, Constant.DEFAULT_SHOW_TITLE)
            ?: Constant.DEFAULT_SHOW_TITLE
        val type: String = extras?.getString(Constant.KEY_SHOW_TYPE, Constant.DEFAULT_SHOW_TYPE)
            ?: Constant.DEFAULT_SHOW_TYPE
        applyToolbarMargin(movie_detail_toolbar)
        simpleToolbarWithHome(movie_detail_toolbar, title)

        showDetailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(ShowDetailViewModel::class.java)

        // TODO: Instead of setting explicitly we can use the Data binding
        showDetailsViewModel.fetchShowDetails(type).observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    progressBarMovieDetails.visibility = View.GONE
                    result.data?.let { showDetails ->
                        this.movieDetails = showDetails
                        if (showDetails.poster.isNotBlank()) {
                            Glide.with(this@ShowDetailsActivity)
                                .load(showDetails.poster)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(movie_detail_poster)
                        }
                        detail_header_title.text = showDetails.title
                        val releasedOn = "Release Date : ${showDetails.releasedOn}"
                        detail_header_release.text = releasedOn

                        detail_header_rating.rating = showDetails.rating

                        textWriters.text = showDetails.writer
                        textActors.text = showDetails.actors
                        textAwards.text = showDetails.awards
                        textGenre.text = showDetails.genre
                        textPlot.text = showDetails.plot

                        fabBookmark.isActivated = showDetails.isBookmarked
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
                showDetailsViewModel.updateBookmarkStatus(title, type, isBookmarked)
            }
        }

        showDetailsViewModel.updateShowTitle(title)
    }

    override fun onDestroy() {
        super.onDestroy()
        showDetailsViewModel.cancelJobs()
    }
}
