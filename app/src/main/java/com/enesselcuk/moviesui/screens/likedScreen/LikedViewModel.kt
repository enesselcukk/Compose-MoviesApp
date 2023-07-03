package com.enesselcuk.moviesui.screens.likedScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.repos.reposLocal.ReposLocal
import com.enesselcuk.moviesui.source.model.response.DetailResponse
import com.enesselcuk.moviesui.source.model.response.TvDetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikedViewModel @Inject constructor(private val localRepos: ReposLocal) : ViewModel() {

    val chooseList = mutableStateListOf<Int>()
    val chooseListTv = mutableStateListOf<Int>()

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


    fun delete(id:Int) {
        viewModelScope.launch {
            localRepos.delete(id)
        }
    }


    private val _getFlowTvFavorite = MutableStateFlow<List<TvDetailResponse>?>(null)
    val getFlowTvFavorite = _getFlowTvFavorite.asStateFlow()


    fun getTvFavorite() {
        viewModelScope.launch {
            localRepos.getTvLiked().collectLatest { likeds ->
                when (likeds) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        _getFlowTvFavorite.emit(likeds.data)
                    }
                    is NetworkResult.Error -> {
                        val error = likeds.message
                    }
                }

            }
        }
    }


    fun deleteTv(id:Int) {
        viewModelScope.launch {
            localRepos.deleteTv(id)
        }
    }


}