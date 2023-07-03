package com.enesselcuk.moviesui.screens.movie.profile

sealed class ProfileUiState {
    object Initial : ProfileUiState()
    object Loading : ProfileUiState()
    data class Success<T>(val response: T) : ProfileUiState()
    data class Failure(val error: String) : ProfileUiState()
}
