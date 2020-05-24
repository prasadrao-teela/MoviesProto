package com.goscale.assignment.data.network.service

import com.goscale.assignment.common.constant.Constant
import com.goscale.assignment.data.network.dto.SearchResponse
import com.goscale.assignment.data.network.dto.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Prasad Rao on 22-05-2020 16:11
 **/
interface TvShowService {

    @GET("?apikey=b4ba412d")
    suspend fun getAllTvShows(
        @Query("s") tvShowName: String = Constant.DEFAULT_TV_SHOW_NAME,
        @Query("Type") type: String = "series"
    ): Response<SearchResponse<TvShowResponse>>

    @GET("?apikey=b4ba412d")
    suspend fun getTvShowDetails(
        @Query("t") movieName: String = Constant.DEFAULT_TV_SHOW_NAME,
        @Query("Type") type: String = "series"
    ): Response<TvShowResponse>
}