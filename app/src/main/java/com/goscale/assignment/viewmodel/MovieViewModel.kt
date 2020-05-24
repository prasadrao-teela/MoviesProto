package com.goscale.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.data.repository.MovieRepository
import com.goscale.assignment.model.Movie
import javax.inject.Inject

/**
 * Created by Prasad Rao on 23-05-2020 19:44
 **/
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieName: MutableLiveData<String> = MutableLiveData()

    val movies: LiveData<Result<List<Movie>>> = Transformations.switchMap(_movieName) { movieName ->
        movieRepository.findMovies(movieName)
    }

    fun updateMovieName(movieName: String) {
        if (_movieName.value == movieName) return

        _movieName.value = movieName
    }

    fun cancelJobs() {
        movieRepository.cancelJobs()
    }
}