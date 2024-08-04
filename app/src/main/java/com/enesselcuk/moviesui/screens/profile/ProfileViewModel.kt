package com.enesselcuk.moviesui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.data.model.authresponse.LoginResponse
import com.enesselcuk.moviesui.data.model.response.AccountDetailsResponse
import com.enesselcuk.moviesui.domain.useCase.ProfileUseCase
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.util.Constant
import com.enesselcuk.moviesui.util.NetworkResult
import com.enesselcuk.moviesui.util.UiState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val localDataStoreUseCase: DataStoreUseCase,
) : ViewModel() {


    private val _getUserFlow = MutableStateFlow<UiState<AccountDetailsResponse>>(UiState.Initial)
    val getUserFlow = _getUserFlow.asStateFlow()

    private val _loginStateFlow = MutableStateFlow<UiState<LoginResponse>>(UiState.Initial)
    val loginStateFlow = _loginStateFlow.asStateFlow()
    fun getUser() {
        viewModelScope.launch {
            profileUseCase.invoke().collect { response ->
                when (response) {
                    is NetworkResult.Loading -> {
                        _getUserFlow.value = UiState.Loading(response.isLoading)
                    }

                    is NetworkResult.Success -> {
                        _getUserFlow.value = UiState.Success(response.data)
                    }

                    is NetworkResult.Error -> {
                        _getUserFlow.value = UiState.Failure(response.message.toString())
                    }
                }
            }
        }
    }


    fun clearUsers() {
        viewModelScope.launch {
            localDataStoreUseCase.clearUsers(Constant.USERS_KEY)
        }
    }

}
