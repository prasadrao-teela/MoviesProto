package com.goscale.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.data.repository.MovieRepository
import com.goscale.assignment.model.Show
import javax.inject.Inject

/**
 * Created by Prasad Rao on 24-05-2020 01:26
 **/
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieName: MutableLiveData<String> = MutableLiveData()

    val movieDetails: LiveData<Result<Show?>> =
        Transformations.switchMap(_movieName) { movieName ->
            movieRepository.fetchShowDetails(movieName)
        }

    fun updateMovieName(movieName: String) {
        if (_movieName.value == movieName) return

        _movieName.value = movieName
    }

    fun updateBookmarkStatus(movieName: String, bookmarkStatus: Boolean) {
        movieRepository.updateBookmarkStatus(movieName, bookmarkStatus)
    }

    fun cancelJobs() {
        movieRepository.cancelJobs()
    }
}