package com.enesselcuk.moviesui.domain.useCase.search

import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.MoviesPeople
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPeopleUseCase @Inject constructor(private val repos: Repos) {

    suspend operator fun invoke(language: String, page: Int, query: String): Flow<NetworkResult<MoviesPeople>> {
        return repos.getSearchPeople(language, page, query)
    }
}