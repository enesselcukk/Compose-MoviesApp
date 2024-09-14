package com.enesselcuk.moviesui.ui.screen.movie.allSee

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.enesselcuk.moviesui.domain.useCase.allMoviesAndTv.AllMoviesAndTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.enesselcuk.moviesui.data.model.response.Result
import kotlinx.coroutines.flow.flowOf


@HiltViewModel
class AllSeeViewModel @Inject constructor(private val allMoviesAndTvUseCase: AllMoviesAndTvUseCase) : ViewModel() {

    var pageFlow by mutableStateOf<Flow<PagingData<Result>>>(flowOf())
        private set

    fun allMovies(title: String, language: String):Flow<PagingData<Result>> {
        viewModelScope.launch {
            pageFlow = allMoviesAndTvUseCase.invoke(title, language).cachedIn(viewModelScope)
        }
        return pageFlow
    }

}

