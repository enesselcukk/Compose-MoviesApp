package com.enesselcuk.moviesui.screens.movie.homeSceen

sealed class HomeScreenUiState{
    object Initial : HomeScreenUiState()
    object Loading:HomeScreenUiState()
    data class Success<T>(val response :T):HomeScreenUiState()
    data class Error(val message:String) :HomeScreenUiState()
}