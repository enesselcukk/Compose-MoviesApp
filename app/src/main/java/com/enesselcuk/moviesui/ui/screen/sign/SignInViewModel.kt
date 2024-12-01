package com.enesselcuk.moviesui.ui.screen.sign


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.enesselcuk.moviesui.data.model.authresponse.CreateResponseToken
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.authresponse.LoginResponse
import com.enesselcuk.moviesui.ui.screen.base.BaseViewModel
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.domain.useCase.login.LoginUseCase
import com.enesselcuk.moviesui.domain.useCase.token.CreateTokenUseCase
import com.enesselcuk.moviesui.util.Constant
import com.enesselcuk.moviesui.util.NetworkResult
import com.enesselcuk.moviesui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val createTokenUseCase: CreateTokenUseCase,
    private val loginUseCase: LoginUseCase,
    private val localDataStoreUseCase: DataStoreUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _loginStateFlow = MutableStateFlow<LoginResponse?>(null)
    val loginStateFlow = _loginStateFlow

    private val _tokenStateFlow = MutableStateFlow<CreateResponseToken?>(null)
    val tokenStateFlow = _tokenStateFlow.asStateFlow()

    //  save checkBox username and password
    var checked by mutableStateOf(false)


    // start token state
    private var saveToken by savedStateHandle.saveable { mutableStateOf("") }

    fun setResponseToken(updateToken: String) {
        saveToken = updateToken
    }

    fun updateToken(): String = saveToken
    // end token state

    // start bottomSheet state screen

    var showSignWebView by savedStateHandle.saveable { mutableStateOf(false) }
        private set

    fun setShowWebView(bottom: Boolean) {
        showSignWebView = bottom
    }
    // end bottom state screen

    // start username saveable state
    var usernameValue by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
        private set

    fun setUserName(userName: TextFieldValue) {
        usernameValue = userName
    }
    // end username saveable state

    //  start password saveable state
    var passwordValue by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
        private set

    fun setPassword(password: TextFieldValue) {
        passwordValue = password
    }
    // end password saveable state


    // save user function.
    fun saveUser() {
        when (checked) {
            true -> {
                clearUsers()
                setLogin(usernameValue.text, passwordValue.text, saveToken)
            }

            false -> {}
        }
    }


    // get token
    fun getToken() {
        viewModelScope.launch {
            val useCase = createTokenUseCase.invoke()
            collectFlow(useCase,
                onSuccess = {
                    _tokenStateFlow.value = it
                }, error = {

                }, loading = {

                })
        }
    }

    // get token
    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val useCase = loginUseCase.invoke(loginRequest)

            collectFlow(useCase,
                onSuccess = {
                    _loginStateFlow.value = it
                }, error = {

                }, loading = {

                })

        }
    }

    // save datastore login username and password
    private fun setLogin(username: String, password: String, token: String) {
        viewModelScope.launch {
            localDataStoreUseCase.invoke(Constant.USERS_KEY, username, password, token)
        }
    }

    // clear username and password
    private fun clearUsers() {
        viewModelScope.launch {
            localDataStoreUseCase.clearUsers(Constant.USERS_KEY)
        }
    }


}

