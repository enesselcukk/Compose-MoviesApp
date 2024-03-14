package com.enesselcuk.moviesui.screens.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.response.LoginResponse
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.domain.useCase.login.LoginUseCase
import com.enesselcuk.moviesui.domain.useCase.token.CreateTokenUseCase
import com.enesselcuk.moviesui.util.Constant
import com.enesselcuk.moviesui.util.NetworkResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val localDataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<LoginResponse?>(null)
    val loginStateFlow = _loginStateFlow.asStateFlow()

     var setUsers = mutableStateOf(false)
         private set
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

    fun getLogin():Boolean{
        viewModelScope.launch {
            setUsers.value = localDataStoreUseCase.invoke(Constant.USERS_KEY) == true
        }
        return setUsers.value
    }



}
