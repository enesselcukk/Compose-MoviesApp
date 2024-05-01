package com.enesselcuk.moviesui.screens.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.authresponse.LoginResponse
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.domain.useCase.login.LoginUseCase
import com.enesselcuk.moviesui.util.Constant
import com.enesselcuk.moviesui.util.NetworkResult
import com.enesselcuk.moviesui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val localDataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<UiState>(UiState.Initial)
    val loginStateFlow = _loginStateFlow.asStateFlow()

    private var setUsers = mutableStateOf(LoginRequest())


    fun getUser()  {
        viewModelScope.launch {
            localDataStoreUseCase.getUser(Constant.USERS_KEY)?.toList().let {
                if (it.isNullOrEmpty()){
                    _loginStateFlow.emit(UiState.Success(false))
                }else {
                    setUsers.value = LoginRequest(it[0], it[1], it[2])
                    login()
                }
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase.invoke(setUsers.value).collectLatest {
                when (it) {
                    is NetworkResult.Loading -> {
                        _loginStateFlow.emit(UiState.Loading(it.isLoading))
                    }
                    is NetworkResult.Success -> {
                        _loginStateFlow.emit(UiState.Success(it.data.success))
                    }
                    is NetworkResult.Error -> {
                       _loginStateFlow.emit(UiState.Failure(it.message))
                    }
                }
            }
        }
    }


}
