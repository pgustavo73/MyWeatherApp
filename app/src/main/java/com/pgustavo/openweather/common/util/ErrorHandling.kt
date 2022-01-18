package com.pgustavo.openweather.common.util

import android.accounts.NetworkErrorException
import com.pgustavo.openweather.common.data.resouce.Resource
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <Result> handleError(e: Throwable?, data : Result? = null): Resource<Result> {
    if (e is NetworkErrorException || e is SocketTimeoutException || e is HttpException || e is UnknownHostException) {
        return Resource.networkError(
            data,
            e.localizedMessage
        )
    }
    return Resource.error(
        e?.localizedMessage ?: "",
        data
    )
}