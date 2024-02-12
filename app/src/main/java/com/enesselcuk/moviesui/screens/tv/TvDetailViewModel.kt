package com.enesselcuk.moviesui.screens.tv

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.domain.tvDetail.TvDetailUseCase
import com.enesselcuk.moviesui.domain.tvDetail.TvFavoriteUseCase
import com.enesselcuk.moviesui.domain.tvDetail.TvLikedUseCase
import com.enesselcuk.moviesui.domain.tvDetail.TvRecommendationsUseCase
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
    private val tvDetailUseCase: TvDetailUseCase,
    private val tvFavoriteUseCase: TvFavoriteUseCase,
    private val tvLikedUseCase: TvLikedUseCase,
    private val tvRecommendationsUseCase: TvRecommendationsUseCase
) : ViewModel() {

    val isClickRecommended = mutableStateOf(false)
    var isVisible = mutableStateOf(false)


    private val _tvDetailFlow = MutableStateFlow<TvDetailResponse?>(null)
    val tvDetailFlow = _tvDetailFlow.asStateFlow()

    private val _tvRecommendationsFlow = MutableStateFlow<TvRecommendationsResponse?>(null)
    val tvRecommendationsFlow = _tvRecommendationsFlow.asStateFlow()

    private val _likedTv = MutableStateFlow<Any?>(null)
    val likedTv = _likedTv.asStateFlow()

    private val _getTvFlowFavorite = MutableStateFlow<List<TvDetailResponse>?>(null)
    val getTvFlowFavorite = _getTvFlowFavorite.asStateFlow()


    fun getTvDetail(id: Int, language: String) {
        viewModelScope.launch {
           tvDetailUseCase.invoke(id = id, language = language).collectLatest {
                network(it, _tvDetailFlow)
            }
        }
    }

    fun getRecommendationsTv(id: Int, language: String, page: Int) {
        viewModelScope.launch {
            tvRecommendationsUseCase.invoke(id = id, language = language, page = page).collectLatest {
                network(it, _tvRecommendationsFlow)
            }
        }
    }

    fun setLikedTv(moviesResponse: TvDetailResponse) {
        viewModelScope.launch {
           tvLikedUseCase.invoke(moviesResponse).collectLatest { setMovies ->
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

    fun getFavorite() {
        viewModelScope.launch {
           tvFavoriteUseCase.invoke().collectLatest { likeds ->
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