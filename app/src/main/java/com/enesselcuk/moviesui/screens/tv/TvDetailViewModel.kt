package com.enesselcuk.moviesui.screens.tv

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.repos.reposLocal.ReposLocal
import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.source.model.response.DetailResponse
import com.enesselcuk.moviesui.source.model.response.TvDetailResponse
import com.enesselcuk.moviesui.source.model.response.TvRecommendationsResponse
import com.enesselcuk.moviesui.util.CustomNetwork.Companion.network
import com.enesselcuk.moviesui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val repos: Repos,
    private val reposLocal: ReposLocal
) : ViewModel() {

    val isClickRecommended = mutableStateOf(false)
    var isVisible = mutableStateOf(false)


    private val _tvDetailFlow = MutableStateFlow<TvDetailResponse?>(null)
    val tvDetailFlow = _tvDetailFlow.asStateFlow()


    fun getSearchMovies(id: Int, language: String) {
        viewModelScope.launch {
            repos.getTvDetail(id = id, language = language).collectLatest {
                network(it, _tvDetailFlow)
            }
        }
    }


    private val _tvRecommendationsFlow = MutableStateFlow<TvRecommendationsResponse?>(null)
    val tvRecommendationsFlow = _tvRecommendationsFlow.asStateFlow()


    fun getRecommendationsMovies(id: Int, language: String, page: Int) {
        viewModelScope.launch {
            repos.getTvRecommendations(id = id, language = language, page = page).collectLatest {
                network(it, _tvRecommendationsFlow)
            }
        }
    }


    private val _likedTv = MutableStateFlow<Any?>(null)
    val likedTv = _likedTv.asStateFlow()

    fun setLikedTv(moviesResponse: TvDetailResponse) {
        viewModelScope.launch {
            reposLocal.insertTvLiked(moviesResponse).collectLatest { setMovies ->
                when (setMovies) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        _likedTv.emit(setMovies.data)
                    }
                    is NetworkResult.Error -> {
                        val e = setMovies.message
                    }
                }
            }
        }
    }

    private val _getTvFlowFavorite = MutableStateFlow<List<TvDetailResponse>?>(null)
    val getTvFlowFavorite = _getTvFlowFavorite.asStateFlow()

    fun getFavorite() {
        viewModelScope.launch {
            reposLocal.getTvLiked().collectLatest { likeds ->
                when (likeds) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        _getTvFlowFavorite.emit(likeds.data)
                    }
                    is NetworkResult.Error -> {
                        val error = likeds.message
                    }
                }

            }
        }
    }

}