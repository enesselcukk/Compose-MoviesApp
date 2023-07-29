package com.enesselcuk.moviesui.screens.player

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.source.model.response.MoviesVideoResponse
import com.enesselcuk.moviesui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val repos: Repos) : ViewModel() {
    val isPlaying = mutableStateOf(false)

    private val _playerMovieFlow = MutableStateFlow<MoviesVideoResponse?>(null)
    val playerMovieFlow = _playerMovieFlow.asStateFlow()

    fun getPlayerMovie(id: Int) {
        viewModelScope.launch {
            repos.getPlayerMovies(id = id).collectLatest {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        _playerMovieFlow.emit(it.data)
                    }
                    is NetworkResult.Error -> {

                    }
                }
            }
        }
    }
}