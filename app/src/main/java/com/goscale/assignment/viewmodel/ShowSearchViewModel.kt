package com.goscale.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.data.repository.ShowsDataRepository
import com.goscale.assignment.model.Show
import javax.inject.Inject

/**
 * Created by Prasad Rao on 24-05-2020 23:14
 **/
class ShowSearchViewModel @Inject constructor(
    private val showsDataRepository: ShowsDataRepository
) : ViewModel() {
    private val _showTitle: MutableLiveData<String> = MutableLiveData()

    val shows: LiveData<Result<List<Show>>> = Transformations.switchMap(_showTitle) { movieName ->
        showsDataRepository.fetchAllShows(movieName)
    }

    fun updateShowTitle(title: String) {
        if (_showTitle.value == title) return

        _showTitle.value = title
    }

    val bookmarks = showsDataRepository.fetchAllBookmarkedShows()

    fun updateBookmarkStatus(title: String, type: String, bookmarkStatus: Boolean) {
        showsDataRepository.updateBookmarkStatus(title, type, bookmarkStatus)
    }

    fun cancelJobs() {
        showsDataRepository.cancelJobs()
    }
}