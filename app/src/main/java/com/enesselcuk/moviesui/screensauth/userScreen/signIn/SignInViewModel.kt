package com.enesselcuk.moviesui.screensauth.userScreen.signIn


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<SignTmdbInState>(SignTmdbInState.Initial)
    val loginStateFlow = _loginStateFlow.asStateFlow()


    fun login(userName: String, password: String) {
        viewModelScope.launch {
            _loginStateFlow.value = SignTmdbInState.Loading

        }
    }
}

