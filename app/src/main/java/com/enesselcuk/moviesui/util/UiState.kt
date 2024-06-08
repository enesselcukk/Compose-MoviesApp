package com.enesselcuk.moviesui.util

sealed class UiState<out T> {
    object Initial : UiState<Nothing>()
    data class Loading<T>(val isLoading:Boolean?) : UiState<T>()
    data class Success<T>(val response: T) : UiState<T>()
    data class Failure<T>(val errorMessage: String?) : UiState<T>()
}

