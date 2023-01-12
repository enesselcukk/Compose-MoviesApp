package com.enesselcuk.moviesui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.repos.Repos
import com.enesselcuk.moviesui.source.model.MoviesResponse
import com.enesselcuk.moviesui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repos: Repos) : ViewModel() {

    private val _getMoviesFlow = MutableSharedFlow<MoviesResponse>()
    val getMoviesFlow = _getMoviesFlow.asSharedFlow()

    private val _stateProgressFlow = MutableStateFlow(false)
    val stateProgressFlow = _stateProgressFlow.asSharedFlow()

    private val _getToastMessage: MutableLiveData<String> = MutableLiveData()

    private fun getMovies(title: String, language: String, page: Int) {
        viewModelScope.launch {
            repos.getMovies(title, language, page).collectLatest { moviesResponse ->
                when (moviesResponse) {
                    is NetworkResult.Loading -> {
                        _stateProgressFlow.emit(true)
                    }
                    is NetworkResult.Success -> {
                        _getMoviesFlow.emit(moviesResponse.data)
                    }
                    is NetworkResult.Error -> {
                        _getToastMessage.value = moviesResponse.message
                    }
                }
            }
        }
    }

    val getToast: LiveData<String> = _getToastMessage

}

