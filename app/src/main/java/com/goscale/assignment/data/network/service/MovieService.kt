package com.goscale.assignment.data.network.service

import com.goscale.assignment.common.constant.Constant
import com.goscale.assignment.data.network.dto.MovieResponse
import com.goscale.assignment.data.network.dto.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Prasad Rao on 22-05-2020 16:09
 **/
interface MovieService {

    @GET("?apikey=b4ba412d")
    suspend fun getAllMovies(
        @Query("s") movieName: String = Constant.DEFAULT_MOVIE_NAME,
        @Query("Type") type: String = "movie"
    ): Response<SearchResponse<MovieResponse>>

    @GET("?apikey=b4ba412d")
    suspend fun getMovieDetails(
        @Query("t") movieName: String = Constant.DEFAULT_MOVIE_NAME,
        @Query("Type") type: String = "movie"
    ): Response<MovieResponse>
}