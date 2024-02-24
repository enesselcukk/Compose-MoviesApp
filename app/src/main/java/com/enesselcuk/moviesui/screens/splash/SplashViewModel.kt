package com.enesselcuk.moviesui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.data.model.authresponse.CreateRequestToken
import com.enesselcuk.moviesui.domain.useCase.token.CreateTokenUseCase
import com.enesselcuk.moviesui.util.NetworkResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val createTokenUseCase: CreateTokenUseCase):ViewModel(){

    private val _authFlow = MutableStateFlow<MainUiState>(MainUiState.Initial)
    val authFlow =  _authFlow.asStateFlow()


    fun login(mail:String,password:String, auth: FirebaseAuth) {
        viewModelScope.launch {
            _authFlow.value = MainUiState.Loading
            auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null) {
                            _authFlow.value = MainUiState.Success(auth)
                        }
                    } else {
                        _authFlow.value = MainUiState.Failure(task.exception.toString())
                    }
                }
        }
    }

}


sealed class MainUiState{
    object Initial : MainUiState()
    object Loading : MainUiState()
    data class Success(val auth: FirebaseAuth) : MainUiState()
    data class Failure(val errorMessage: String) : MainUiState()
}
