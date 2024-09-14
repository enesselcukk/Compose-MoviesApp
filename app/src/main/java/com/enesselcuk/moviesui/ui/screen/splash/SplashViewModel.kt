package com.enesselcuk.moviesui.ui.screen.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.ui.screen.base.BaseViewModel
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.domain.useCase.login.LoginUseCase
import com.enesselcuk.moviesui.util.Constant
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
) : BaseViewModel() {

    private val _loginStateFlow = MutableStateFlow<Boolean?>(false)
    val loginStateFlow = _loginStateFlow.asStateFlow()

    private var setUsers = mutableStateOf(LoginRequest())


    fun getUser() {
        viewModelScope.launch {
            localDataStoreUseCase.getUser(Constant.USERS_KEY)?.toList().let {
                if (it.isNullOrEmpty()) {
                    _loginStateFlow.value = false
                } else {
                    setUsers.value = LoginRequest(it[0], it[1], it[2])
                    login()
                }
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            val useCase = loginUseCase.invoke(setUsers.value)

            collectFlow(useCase, onSuccess = {
                _loginStateFlow.value = it.success
            }, error = {}, loading = {})


        }
    }


}
