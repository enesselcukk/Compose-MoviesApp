package com.enesselcuk.moviesui.util

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val message: Exception?) : NetworkResult<T>()
    data class Loading<T>(val isLoading: Boolean? = null) : NetworkResult<T>()
}

