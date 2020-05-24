package com.goscale.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.data.repository.TvShowRepository
import com.goscale.assignment.model.Show
import javax.inject.Inject

/**
 * Created by Prasad Rao on 24-05-2020 00:09
 **/
class TvShowViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    private val _tvShowName: MutableLiveData<String> = MutableLiveData()

    val tvShows: LiveData<Result<List<Show>>> =
        Transformations.switchMap(_tvShowName) { tvShowName ->
            tvShowRepository.fetchAllShows(tvShowName)
        }

    fun updateTvShow(tvShowName: String) {
        if (_tvShowName.value == tvShowName) return

        _tvShowName.value = tvShowName
    }

    fun cancelJobs() {
        tvShowRepository.cancelJobs()
    }
}