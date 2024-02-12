package com.enesselcuk.moviesui.domain.detail

import com.enesselcuk.moviesui.repos.reposLocal.ReposLocal
import com.enesselcuk.moviesui.source.model.response.DetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailLikedUseCase @Inject constructor(private val reposLocal: ReposLocal) {
    suspend operator fun invoke(moviesResponse: DetailResponse): Flow<NetworkResult<Any>>{
        return reposLocal.insertLiked(moviesResponse)
    }
}