package com.enesselcuk.moviesui.domain.useCase

import com.enesselcuk.moviesui.data.model.response.AccountDetailsResponse
import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val repos: Repos) {
    suspend operator fun invoke():Flow<NetworkResult<AccountDetailsResponse>>{
        return repos.accountDetails()
    }
}