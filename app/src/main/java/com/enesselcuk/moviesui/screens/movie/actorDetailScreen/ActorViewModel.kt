package com.enesselcuk.moviesui.screens.movie.actorDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.domain.useCase.actor.ActorDetailUseCase
import com.enesselcuk.moviesui.domain.useCase.actor.ActorMoviesUseCase
import com.enesselcuk.moviesui.domain.useCase.actor.ActorTvUseCase
import com.enesselcuk.moviesui.data.model.response.ActorDetailResponse
import com.enesselcuk.moviesui.data.model.response.ActorMoviesResponse
import com.enesselcuk.moviesui.data.model.response.ActorTvResponse
import com.enesselcuk.moviesui.util.CustomNetwork.Companion.network
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActorViewModel @Inject constructor(
    private val actorDetailUseCase: ActorDetailUseCase,
    private val actorMoviesUseCase: ActorMoviesUseCase,
    private val actorTvUseCase: ActorTvUseCase
) : ViewModel() {

    private val _actorDetailFlow = MutableStateFlow<ActorDetailResponse?>(null)
    val actorDetailFlow = _actorDetailFlow.asStateFlow()

    fun getActor(id: Int, language: String) {
        viewModelScope.launch {
            actorDetailUseCase.invoke(id, language).collectLatest {
                network(it, _actorDetailFlow)
            }
        }
    }

    private val _actorMovieDetailFlow = MutableStateFlow<ActorMoviesResponse?>(null)
    val actorMovieDetailFlow = _actorMovieDetailFlow.asStateFlow()

    fun getActorMovie(id: Int, language: String) {
        viewModelScope.launch {
           actorMoviesUseCase.invoke(id, language).collectLatest {
                network(it, _actorMovieDetailFlow)
            }
        }
    }

    private val _actorTvDetailFlow = MutableStateFlow<ActorTvResponse?>(null)
    val actorTvDetailFlow = _actorTvDetailFlow.asStateFlow()

    fun getActorTv(id: Int, language: String) {
        viewModelScope.launch {
            actorTvUseCase.invoke(id, language).collectLatest {
                network(it, _actorTvDetailFlow)
            }
        }
    }

}

