package com.enesselcuk.moviesui.screens.movie.actorDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.source.model.response.ActorDetailResponse
import com.enesselcuk.moviesui.source.model.response.ActorMoviesResponse
import com.enesselcuk.moviesui.source.model.response.ActorTvResponse
import com.enesselcuk.moviesui.util.CustomNetwork.Companion.network
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActorViewModel @Inject constructor(private val repos: Repos) : ViewModel() {

    private val _actorDetailFlow = MutableStateFlow<ActorDetailResponse?>(null)
    val actorDetailFlow = _actorDetailFlow.asStateFlow()

    fun getActor(id: Int, language: String) {
        viewModelScope.launch {
            repos.getActorDetail(id = id, language).collectLatest {
                network(it, _actorDetailFlow)
            }
        }
    }

    private val _actorMovieDetailFlow = MutableStateFlow<ActorMoviesResponse?>(null)
    val actorMovieDetailFlow = _actorMovieDetailFlow.asStateFlow()

    fun getActorMovie(id: Int, language: String) {
        viewModelScope.launch {
            repos.getActorMovies(id = id, language).collectLatest {
                network(it, _actorMovieDetailFlow)
            }
        }
    }

    private val _actorTvDetailFlow = MutableStateFlow<ActorTvResponse?>(null)
    val actorTvDetailFlow = _actorTvDetailFlow.asStateFlow()

    fun getActorTv(id: Int, language: String) {
        viewModelScope.launch {
            repos.getActorTv(id = id, language).collectLatest {
                network(it, _actorTvDetailFlow)
            }
        }
    }

}

