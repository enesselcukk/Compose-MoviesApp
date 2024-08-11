package com.enesselcuk.moviesui.domain.useCase.login

import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.authresponse.LoginResponse
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.di.DefaultDispatcher
import com.enesselcuk.moviesui.domain.base.BaseUiSate
import com.enesselcuk.moviesui.domain.base.BaseUseCase
import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repos: Repos,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : BaseUseCase<LoginUseCase.InputParams, LoginResponse> {

    override suspend fun execute(input: InputParams?): BaseUiSate<LoginResponse> {
        var uiState: BaseUiSate<LoginResponse> = BaseUiSate.Initial
        withContext(defaultDispatcher) {
            input?.let {
                repos.loginRequest(it.loginRequest).collect { networkResult ->
                    uiState = when (networkResult) {
                        is NetworkResult.Loading -> {
                            BaseUiSate.Loading(networkResult.isLoading)
                        }

                        is NetworkResult.Success -> {
                            BaseUiSate.Success(networkResult.data)
                        }

                        is NetworkResult.Error -> {
                            BaseUiSate.Failure(networkResult.message)
                        }
                    }
                }
            }
        }
        return uiState
    }

    data class InputParams(
        val loginRequest: LoginRequest
    )
}