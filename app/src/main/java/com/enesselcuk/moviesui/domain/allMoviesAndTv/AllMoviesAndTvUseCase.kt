package com.enesselcuk.moviesui.domain.allMoviesAndTv

import androidx.paging.PagingData
import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.source.model.response.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllMoviesAndTvUseCase @Inject constructor(private val repos: Repos) {
   suspend operator fun invoke(title: String, language: String): Flow<PagingData<Result>> {
       return repos.getMovie(title,language)
   }
}