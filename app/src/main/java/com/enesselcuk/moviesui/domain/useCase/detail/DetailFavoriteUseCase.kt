package com.enesselcuk.moviesui.domain.useCase.detail

import com.enesselcuk.moviesui.domain.repository.ReposLocal
import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailFavoriteUseCase @Inject constructor(private val reposLocal: ReposLocal) {
    suspend operator fun invoke() : Flow<NetworkResult<List<DetailResponse>>>{
        return reposLocal.getLiked()
    }
}