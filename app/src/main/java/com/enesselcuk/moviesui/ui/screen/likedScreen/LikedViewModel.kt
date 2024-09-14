package com.enesselcuk.moviesui.ui.screen.likedScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.enesselcuk.moviesui.domain.repository.ReposLocal
import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class LikedViewModel @Inject constructor(
    private val localRepos: ReposLocal,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val chooseList = mutableStateListOf<Int>()
    val chooseListTv = mutableStateListOf<Int>()

    private val _getFlowFavorite = MutableStateFlow<List<DetailResponse>?>(null)
    val getFlowFavorite = _getFlowFavorite.asStateFlow()

    private var isDeleteTvOrMovies by savedStateHandle.saveable { mutableStateOf(false) }

    fun setRemove(isRemove: Boolean) {
        isDeleteTvOrMovies = isRemove
    }

    fun updateDb(): Boolean = isDeleteTvOrMovies

    fun getFavorite() {
        viewModelScope.launch {
            localRepos.getLiked().collectLatest { likeds ->
                when (likeds) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        _getFlowFavorite.value = likeds.data
                    }

                    is NetworkResult.Error -> {
                        val error = likeds.message
                    }
                }

            }
        }
    }


    fun delete(id: Int) {
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
                        _getFlowTvFavorite.value = likeds.data
                    }

                    is NetworkResult.Error -> {
                        val error = likeds.message
                    }
                }

            }
        }
    }


    fun deleteTv(id: Int) {
        viewModelScope.launch {
            localRepos.deleteTv(id)
        }
    }


}