package com.enesselcuk.moviesui.screens.userScreen.signIn


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

    private val _loginStateFlow = MutableStateFlow<SignInState>(SignInState.Initial)
    val loginStateFlow = _loginStateFlow.asStateFlow()


    fun login(userName: String, password: String, auth: FirebaseAuth?) {
        viewModelScope.launch {
            _loginStateFlow.value = SignInState.Loading
            auth?.signInWithEmailAndPassword(userName, password)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        _loginStateFlow.value = SignInState.Success(auth)
                    }
                } else {
                    _loginStateFlow.value =
                        SignInState.Failure("Giriş Bilgilerinizi Doğru Giriniz.")
                }
            }
        }
    }
}

