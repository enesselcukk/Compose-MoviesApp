package com.enesselcuk.moviesui.screens.sign


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TextInputService
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val createTokenUseCase: CreateTokenUseCase,
    private val loginUseCase: LoginUseCase,
    private val localDataStoreUseCase: DataStoreUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow<UiState>(UiState.Initial)
    val loginStateFlow = _loginStateFlow.asStateFlow()

    private val _tokenStateFlow = MutableStateFlow<UiState>(UiState.Initial)
    val tokenStateFlow = _tokenStateFlow.asStateFlow()

    var checked by mutableStateOf(false)

    var token by savedStateHandle.saveable { mutableStateOf("") }
        private set

    fun setResponseToken(updateToken: String) {
        token = updateToken
    }

    var showBottomSheet by savedStateHandle.saveable { mutableStateOf(false) }
        private set

    fun setShowBottom(bottom: Boolean) {
        showBottomSheet = bottom
    }

    var usernameValue by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
        private set

    fun setUserName(userName: TextFieldValue) {
        usernameValue = userName
    }

    var passwordValue by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
        private set

    fun setPassword(password: TextFieldValue) {
        passwordValue = password
    }

    fun saveUser(){
        when(checked){
            true ->{
                clearUsers()
                setLogin(usernameValue.text,passwordValue.text,token)
            }
            false -> {}
        }
    }


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

