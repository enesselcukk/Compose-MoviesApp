package com.enesselcuk.moviesui.domain.base

sealed class BaseUiSateUseCase<out T> {
    object Initial : BaseUiSateUseCase<Nothing>()
    data class Loading<T>(val isLoading:Boolean?) : BaseUiSateUseCase<T>()
    data class Success<T>(val response: T) : BaseUiSateUseCase<T>()
    data class Failure<T>(val errorMessage: Exception?) : BaseUiSateUseCase<T>()
}