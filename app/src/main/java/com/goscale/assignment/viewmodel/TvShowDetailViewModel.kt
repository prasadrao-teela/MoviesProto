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
 * Created by Prasad Rao on 24-05-2020 01:27
 **/
class TvShowDetailViewModel @Inject constructor(
    private val repository: TvShowRepository
) : ViewModel() {

    private val _tvShowName: MutableLiveData<String> = MutableLiveData()

    val tvShowDetails: LiveData<Result<Show?>> =
        Transformations.switchMap(_tvShowName) { title ->
            repository.fetchShowDetails(title)
        }

    fun updateTvShowTitle(title: String) {
        if (_tvShowName.value == title) return

        _tvShowName.value = title
    }

    fun updateBookmarkStatus(title: String, bookmarkStatus: Boolean) {
        repository.updateBookmarkStatus(title, bookmarkStatus)
    }

    fun cancelJobs() {
        repository.cancelJobs()
    }
}