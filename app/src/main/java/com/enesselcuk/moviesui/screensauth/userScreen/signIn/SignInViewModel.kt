package com.enesselcuk.moviesui.screensauth.userScreen.signIn


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
class SignInViewModel @Inject constructor(private val createTokenUseCase: CreateTokenUseCase) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<SignTmdbInState>(SignTmdbInState.Initial)
    val loginStateFlow = _loginStateFlow.asStateFlow()


    private val _tokenStateFlow = MutableStateFlow<CreateRequestToken?>(null)
    val tokenStateFlow = _tokenStateFlow.asStateFlow()


    fun getToken() {
        viewModelScope.launch {
            createTokenUseCase.invoke().collectLatest {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        _tokenStateFlow.emit(it.data)
                    }
                    is NetworkResult.Error -> {
                        it.message
                    }
                }
            }
        }
    }
}

