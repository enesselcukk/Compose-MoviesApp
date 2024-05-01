package com.enesselcuk.moviesui.util

sealed class UiState {
    object Initial : UiState()
    data class Loading(val isLoading:Boolean?) : UiState()
    data class Success<T>(val response: T) : UiState()
    data class Failure(val errorMessage: String?) : UiState()
}

