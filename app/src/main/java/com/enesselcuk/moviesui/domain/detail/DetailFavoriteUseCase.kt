package com.enesselcuk.moviesui.domain.detail

import com.enesselcuk.moviesui.repos.reposLocal.ReposLocal
import com.enesselcuk.moviesui.source.model.response.DetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailFavoriteUseCase @Inject constructor(private val reposLocal: ReposLocal) {
    suspend operator fun invoke() : Flow<NetworkResult<List<DetailResponse>>>{
        return reposLocal.getLiked()
    }
}