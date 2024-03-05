package com.enesselcuk.moviesui.util.state

import com.google.firebase.auth.FirebaseAuth

sealed class SignInState {
    object Initial : SignInState()
    object Loading : SignInState()
    data class Success<T>(val response: T) : SignInState()
    data class Failure(val errorMessage: String) : SignInState()
}

