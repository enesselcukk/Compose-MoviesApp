package com.enesselcuk.moviesui.screens.userScreen.register

sealed class RegisterUiState {
    object Initial : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success<T>(val response: T,val response2: T) : RegisterUiState()
    data class Failure(val error: String) : RegisterUiState()
}
