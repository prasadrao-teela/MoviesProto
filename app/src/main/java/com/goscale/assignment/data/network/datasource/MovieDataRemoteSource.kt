package com.goscale.assignment.data.network.datasource

import com.goscale.assignment.data.network.service.MovieService
import javax.inject.Inject

/**
 * Created by Prasad Rao on 24-05-2020 15:35
 **/
class MovieDataRemoteSource @Inject constructor(private val movieService: MovieService) :
    BaseDataSource() {

    suspend fun findAllMovies(movieName: String) = getResult {
        movieService.getAllMovies(movieName)
    }

    suspend fun getMovieDetails(movieName: String) = getResult {
        movieService.getMovieDetails(movieName)
    }
}