package com.enesselcuk.moviesui.screens.movie.moviesdetailsScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.repos.reposLocal.ReposLocal
import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.source.model.response.DetailResponse
import com.enesselcuk.moviesui.source.model.response.MoviesResponse
import com.enesselcuk.moviesui.util.CustomNetwork.Companion.network
import com.enesselcuk.moviesui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repos: Repos,
    private val localRepos: ReposLocal
) : ViewModel() {

    private val _detailsFlow = MutableStateFlow<DetailResponse?>(null)
    val detailsFlow = _detailsFlow.asStateFlow()

    private val _loadingFlow = MutableStateFlow(false)
    val loadingFlow = _loadingFlow.asStateFlow()

    var isVisible = mutableStateOf(false)
    val isClickRecommended = mutableStateOf(false)
    val ifLiked = mutableStateOf(false)


    fun getDetails(movie_id: Int?, language: String) {
        viewModelScope.launch {
            repos.getDetails(movie_id, language).collectLatest {
                when (it) {
                    is NetworkResult.Error -> {
                        _loadingFlow.value = false
                    }
                    is NetworkResult.Success -> {
                        _detailsFlow.emit(it.data)
                    }
                    is NetworkResult.Loading -> {
                        _loadingFlow.value = true
                    }
                }
            }
        }
    }

    private val _detailRecommended = MutableStateFlow<MoviesResponse?>(null)
    val detailRecommended = _detailRecommended.asStateFlow()

    fun getRecommended(id: Int?, page: Int?, language: String?) {
        viewModelScope.launch {
            repos.getRecommended(id, page, language).collectLatest {
                network(it, _detailRecommended)
            }
        }
    }

    private val _likedMovies = MutableStateFlow<Any?>(null)
    val likedMovies = _likedMovies.asStateFlow()

    fun setLikedMovies(moviesResponse: DetailResponse) {
        viewModelScope.launch {
            localRepos.insertLiked(moviesResponse).collectLatest { setMovies ->
                when (setMovies) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        _likedMovies.emit(setMovies.data)
                    }
                    is NetworkResult.Error -> {
                        val e = setMovies.message
                    }
                }
            }
        }
    }

    private val _getFlowFavorite = MutableStateFlow<List<DetailResponse>?>(null)
    val getFlowFavorite = _getFlowFavorite.asStateFlow()


    fun getFavorite() {
        viewModelScope.launch {
            localRepos.getLiked().collectLatest { likeds ->
                when (likeds) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        _getFlowFavorite.emit(likeds.data)
                    }
                    is NetworkResult.Error -> {
                        val error = likeds.message
                    }
                }

            }
        }
    }
}

