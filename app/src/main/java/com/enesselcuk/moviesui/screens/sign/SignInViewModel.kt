package com.enesselcuk.moviesui.screens.sign


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.data.model.authresponse.CreateResponseToken
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.authresponse.LoginResponse
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.domain.useCase.login.LoginUseCase
import com.enesselcuk.moviesui.domain.useCase.token.CreateTokenUseCase
import com.enesselcuk.moviesui.util.Constant
import com.enesselcuk.moviesui.util.NetworkResult
import com.enesselcuk.moviesui.util.UiState
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

    private val _loginStateFlow = MutableStateFlow<UiState>(UiState.Initial)
    val loginStateFlow = _loginStateFlow.asStateFlow()

    private val _tokenStateFlow = MutableStateFlow<UiState>(UiState.Initial)
    val tokenStateFlow = _tokenStateFlow.asStateFlow()

    val checked = mutableStateOf(false)


    fun getToken() {
        viewModelScope.launch {
            createTokenUseCase.invoke().collectLatest { response ->
                when (response) {
                    is NetworkResult.Loading -> {
                        _tokenStateFlow.emit(UiState.Loading(response.isLoading))
                    }

                    is NetworkResult.Success -> {
                        _tokenStateFlow.emit(UiState.Success(response.data))
                    }

                    is NetworkResult.Error -> {
                        _tokenStateFlow.emit(UiState.Failure(response.message))
                    }
                }
            }
        }
    }

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginUseCase.invoke(loginRequest).collectLatest {
                when (it) {
                    is NetworkResult.Loading -> {
                        _loginStateFlow.emit(UiState.Loading(it.isLoading))
                    }

                    is NetworkResult.Success -> {
                        _loginStateFlow.emit(UiState.Success(it.data))
                    }

                    is NetworkResult.Error -> {
                        _loginStateFlow.emit(UiState.Failure(it.message))
                    }
                }
            }
        }
    }

    fun setLogin(username: String, password: String, token: String) {
        viewModelScope.launch {
            localDataStoreUseCase.invoke(Constant.USERS_KEY, username, password, token)
        }
    }

    fun clearUsers() {
        viewModelScope.launch {
            localDataStoreUseCase.clearUsers(Constant.USERS_KEY)
        }
    }



}

