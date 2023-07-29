package com.enesselcuk.moviesui.screensauth.userScreen.signIn

import com.google.firebase.auth.FirebaseAuth

sealed class SignTmdbInState {
    object Initial : SignTmdbInState()
    object Loading : SignTmdbInState()
    data class Success(val auth: FirebaseAuth) : SignTmdbInState()
    data class Failure(val errorMessage: String) : SignTmdbInState()
}

