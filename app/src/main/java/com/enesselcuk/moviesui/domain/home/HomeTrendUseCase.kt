package com.enesselcuk.moviesui.domain.home

import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.source.model.response.MoviesResponse
import com.enesselcuk.moviesui.source.model.response.TrendingResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeTrendUseCase @Inject constructor(private val repos: Repos) {
    suspend operator fun invoke():Flow<NetworkResult<TrendingResponse>>{
        return repos.getMoviesTrending()
    }
}