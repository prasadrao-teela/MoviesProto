package com.goscale.assignment.data.network.datasource

import com.goscale.assignment.data.network.service.ShowService
import javax.inject.Inject

/**
 * Created by Prasad Rao on 24-05-2020 15:35
 **/
class ShowsDataRemoteSource @Inject constructor(
    private val showService: ShowService
) : BaseDataSource() {

    suspend fun fetchAllShows(title: String, type: String) = getResult {
        showService.fetchAllShows(title = title, type = type)
    }

    suspend fun fetchAllShowsByTitle(title: String) = getResult {
        showService.fetchAllShowsByTitle(title)
    }

    suspend fun fetchShowDetails(title: String) = getResult {
        showService.fetchShowDetails(title)
    }
}