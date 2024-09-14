package com.enesselcuk.moviesui.ui.screen.movie.homeSceen


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.domain.useCase.home.HomeTrendUseCase
import com.enesselcuk.moviesui.domain.useCase.home.HomeUseCase
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.data.model.response.TrendingResponse
import com.enesselcuk.moviesui.ui.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val homeTrendUseCase: HomeTrendUseCase
) : BaseViewModel() {

    private val _getMoviesFlow = MutableStateFlow<MoviesResponse?>(null)
    val getMoviesFlow = _getMoviesFlow.asStateFlow()

    private val _getUpComingFlow = MutableStateFlow<MoviesResponse?>(null)
    val getUpComingFlow = _getUpComingFlow.asStateFlow()

    private val _getMoviesTrendingFlow = MutableStateFlow<TrendingResponse?>(null)
    val getMoviesTrendingFlow = _getMoviesTrendingFlow.asStateFlow()

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    var isVisible = mutableStateOf(false)
        private set

    fun getMovies(title: String, language: String, page: Int) {
        viewModelScope.launch {
            val useCase = homeUseCase.invoke(title, language, page)
            collectFlow(useCase, onSuccess = {
                _getMoviesFlow.value = it
            }, error = {}, loading = {})
        }
    }

    fun getMoviesUpComing(title: String, language: String, page: Int) {
        viewModelScope.launch {
            val useCase = homeUseCase.invoke(title, language, page)
            collectFlow(useCase, onSuccess = {
                _getUpComingFlow.value = it
            }, error = {}, loading = {})
        }
    }

    fun getMoviesTrending() {
        viewModelScope.launch {
            val useCase = homeTrendUseCase.invoke()
            collectFlow(useCase, onSuccess = {
                _getMoviesTrendingFlow.value = it
            }, error = {}, loading = {})

        }
    }
}
