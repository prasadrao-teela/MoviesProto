package com.goscale.assignment.data.network.datasource

import com.goscale.assignment.data.network.Result
import retrofit2.Response
import timber.log.Timber

/**
 * Created by Prasad Rao on 24-05-2020 15:31
 **/
abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(
                    body
                )
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e(message)
        return Result.error("Network call has failed for a following reason: $message")
    }
}