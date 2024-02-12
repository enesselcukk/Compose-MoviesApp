package com.enesselcuk.moviesui.domain.useCase.allMoviesAndTv

import androidx.paging.PagingData
import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllMoviesAndTvUseCase @Inject constructor(private val repos: Repos) {
   suspend operator fun invoke(title: String, language: String): Flow<PagingData<Result>> {
       return repos.getMovie(title,language)
   }
}