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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val localDataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<LoginResponse?>(null)
    val loginStateFlow = _loginStateFlow.asStateFlow()

    private var setUsers = mutableStateOf(LoginRequest())


    fun getUser() {
        viewModelScope.launch {
            localDataStoreUseCase.getUser(Constant.USERS_KEY)?.toList()?.apply {
                setUsers.value = LoginRequest(get(0), get(1), get(2))
                login()
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase.invoke(setUsers.value).collectLatest {
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


}
