package com.enesselcuk.moviesui.domain.search

import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.source.model.response.MoviesPeople
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPeopleUseCase @Inject constructor(private val repos: Repos) {

    suspend operator fun invoke(language: String, page: Int, query: String): Flow<NetworkResult<MoviesPeople>> {
        return repos.getSearchPeople(language, page, query)
    }
}