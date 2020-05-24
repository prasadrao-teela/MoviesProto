package com.goscale.assignment.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Prasad Rao on 23-05-2020 20:39
 **/
data class SearchResponse<T>(
    @SerializedName("Search")
    val results: List<T>? = null,

    @SerializedName("totalResults")
    val totalResults: String? = null,

    @SerializedName("Response")
    val response: String? = null,

    @SerializedName("Error")
    val error: String? = null
)