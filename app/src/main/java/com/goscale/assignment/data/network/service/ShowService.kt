package com.goscale.assignment.data.network.service

import com.goscale.assignment.common.constant.Constant
import com.goscale.assignment.data.network.dto.SearchResponse
import com.goscale.assignment.data.network.dto.ShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Prasad Rao on 22-05-2020 16:09
 **/
interface ShowService {

    @GET("?apikey=b4ba412d")
    suspend fun fetchAllShows(
        @Query("s") title: String = Constant.DEFAULT_SHOW_TITLE,
        @Query("Type") type: String = Constant.SHOW_TYPE_MOVIE
    ): Response<SearchResponse<ShowResponse>>

    @GET("?apikey=b4ba412d")
    suspend fun fetchAllShowsByTitle(
        @Query("s") title: String = Constant.DEFAULT_SHOW_TITLE
    ): Response<SearchResponse<ShowResponse>>

    @GET("?apikey=b4ba412d")
    suspend fun fetchShowDetails(
        @Query("t") title: String = Constant.DEFAULT_SHOW_TITLE,
        @Query("Type") type: String = Constant.SHOW_TYPE_MOVIE
    ): Response<ShowResponse>
}