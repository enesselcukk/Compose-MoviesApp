package com.enesselcuk.moviesui.screens.sign


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.data.model.authresponse.CreateResponseToken
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.response.LoginResponse
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.domain.useCase.login.LoginUseCase
import com.enesselcuk.moviesui.domain.useCase.token.CreateTokenUseCase
import com.enesselcuk.moviesui.util.Constant
import com.enesselcuk.moviesui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val createTokenUseCase: CreateTokenUseCase,
    private val loginUseCase: LoginUseCase,
    private val localDataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<LoginResponse?>(null)
    val loginStateFlow = _loginStateFlow.asStateFlow()

    private val _tokenStateFlow = MutableStateFlow<CreateResponseToken?>(null)
    val tokenStateFlow = _tokenStateFlow.asStateFlow()

    val checked =  mutableStateOf(false)

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

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginUseCase.invoke(loginRequest).collectLatest {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        _loginStateFlow.emit(it.data)
                    }

                    is NetworkResult.Error -> {
                        it.message
                    }
                }
            }
        }
    }

    fun setLogin(value:Boolean) {
        viewModelScope.launch {
            localDataStoreUseCase.invoke(Constant.USERS_KEY,value)
        }
    }
}

