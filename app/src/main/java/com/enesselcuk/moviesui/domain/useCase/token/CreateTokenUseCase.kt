package com.enesselcuk.moviesui.domain.useCase.token

import com.enesselcuk.moviesui.data.model.authresponse.CreateResponseToken
import com.enesselcuk.moviesui.di.DefaultDispatcher
import com.enesselcuk.moviesui.domain.base.BaseUiSate
import com.enesselcuk.moviesui.domain.base.BaseUseCase
import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateTokenUseCase @Inject constructor(
    private val repos: Repos,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : BaseUseCase<Any, CreateResponseToken> {

    override suspend fun execute(input: Any?): BaseUiSate<CreateResponseToken> {
        var uiState: BaseUiSate<CreateResponseToken> = BaseUiSate.Initial

        withContext(defaultDispatcher) {
            repos.createToken().collect {
                uiState = when (it) {
                    is NetworkResult.Loading -> {
                        BaseUiSate.Loading(it.isLoading)
                    }
                    is NetworkResult.Success -> {
                        BaseUiSate.Success(it.data)
                    }
                    is NetworkResult.Error -> {
                        BaseUiSate.Failure(it.message)
                    }
                }
            }
        }
        return uiState
    }
}