package com.enesselcuk.moviesui.screens.userScreen.signIn

import com.google.firebase.auth.FirebaseAuth

sealed class SignInState {
    object Initial : SignInState()
    object Loading : SignInState()
    data class Success(val auth: FirebaseAuth) : SignInState()
    data class Failure(val errorMessage: String) : SignInState()
}

