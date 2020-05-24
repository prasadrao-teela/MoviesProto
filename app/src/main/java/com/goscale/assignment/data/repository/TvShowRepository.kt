package com.goscale.assignment.data.repository

import androidx.lifecycle.LiveData
import com.goscale.assignment.data.network.service.TvShowService
import com.goscale.assignment.model.TvShow
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Created by Prasad Rao on 24-05-2020 00:09
 **/
class TvShowRepository @Inject constructor(
    private val tvShowService: TvShowService
) {
    private var backgroundJob: CompletableJob? = null

    fun getTvShows(tvShowName: String): LiveData<List<TvShow>> {
        backgroundJob = Job()

        return object : LiveData<List<TvShow>>() {
            override fun onActive() {
                super.onActive()

                backgroundJob?.let { job ->

                    CoroutineScope(Dispatchers.IO + job).launch {
                        val response = tvShowService.getAllTvShows(tvShowName)

                        withContext(Dispatchers.Main) {
                            value = response.results?.map { tvShowResponse ->
                                TvShow(
                                    title = tvShowResponse.title!!,
                                    year = tvShowResponse.yearOfRelease!!,
                                    imdbId = tvShowResponse.imdbId!!,
                                    poster = tvShowResponse.poster!!
                                )
                            } ?: emptyList()
                            job.cancel()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        backgroundJob?.cancel()
    }
}