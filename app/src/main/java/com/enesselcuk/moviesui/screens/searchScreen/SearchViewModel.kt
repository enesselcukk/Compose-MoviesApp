package com.enesselcuk.moviesui.screens.searchScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.domain.useCase.search.SearchMoviesUseCase
import com.enesselcuk.moviesui.domain.useCase.search.SearchPeopleUseCase
import com.enesselcuk.moviesui.data.model.response.MoviesPeople
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchMoviesUseCase: SearchMoviesUseCase,
                                          private val searchPeopleUseCase: SearchPeopleUseCase
) : ViewModel() {

    private val _searchMoviesFlow = MutableStateFlow<MoviesResponse?>(null)
    val searchMoviesFlow = _searchMoviesFlow.asStateFlow()

    private val _getToastMessage: MutableLiveData<String> = MutableLiveData()
    val getToast: LiveData<String> = _getToastMessage

    var isVisible = mutableStateOf(false)
        private set


    fun getSearchMovies(language: String, page: Int, query: String) {
        viewModelScope.launch {
           searchMoviesUseCase.invoke(language, page, query).collectLatest {
                when (it) {
                    is NetworkResult.Error -> {
                        _getToastMessage.value = it.message
                        delay(1000)
                        isVisible.value = false
                    }
                    is NetworkResult.Success -> {
                        _searchMoviesFlow.emit(it.data)
                        isVisible.value = true
                    }
                    is NetworkResult.Loading -> {

                    }
                }
            }
        }
    }

    private val _searchPeopleFlow = MutableStateFlow<MoviesPeople?>(null)
    val searchPeopleFlow = _searchPeopleFlow.asStateFlow()

    fun getSearchPeople(language: String, page: Int, query: String) {
        viewModelScope.launch {
           searchPeopleUseCase.invoke(language, page, query).collectLatest {
                when (it) {
                    is NetworkResult.Error -> {
                        _getToastMessage.value = it.message
                        delay(1000)
                        isVisible.value = false
                    }
                    is NetworkResult.Success -> {
                        _searchPeopleFlow.emit(it.data)
                        isVisible.value = true
                    }
                    is NetworkResult.Loading -> {

                    }
                }
            }
        }
    }


}