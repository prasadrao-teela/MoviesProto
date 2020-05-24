package com.goscale.assignment.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Prasad Rao on 22-05-2020 22:09
 **/
data class RatingResponse(
    @SerializedName("Source")
    val source: String? = null,

    @SerializedName("Value")
    val value: String? = null
)