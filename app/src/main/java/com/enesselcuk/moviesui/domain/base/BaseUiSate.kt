package com.enesselcuk.moviesui.domain.base

sealed class BaseUiSate<out T> {
    object Initial : BaseUiSate<Nothing>()
    data class Loading<T>(val isLoading:Boolean?) : BaseUiSate<T>()
    data class Success<T>(val response: T) : BaseUiSate<T>()
    data class Failure<T>(val errorMessage: Exception?) : BaseUiSate<T>()
}