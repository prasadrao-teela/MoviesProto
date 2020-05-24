package com.goscale.assignment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.data.network.Result.Status.ERROR
import com.goscale.assignment.data.network.Result.Status.SUCCESS
import kotlinx.coroutines.Dispatchers

/**
 * Created by Prasad Rao on 24-05-2020 13:26
 **/
fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> = liveData(Dispatchers.IO) {
    emit(Result.loading<T>())
    val source = databaseQuery.invoke().map { Result.success(it) }
    emitSource(source)
    val responseStatus = networkCall.invoke()
    if (responseStatus.status == SUCCESS) {
        saveCallResult(responseStatus.data!!)
    } else if (responseStatus.status == ERROR) {
        emit(Result.error<T>(responseStatus.message!!))
        emitSource(source)
    }
}