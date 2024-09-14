package com.enesselcuk.moviesui.ui.screen.base

import androidx.lifecycle.ViewModel
import com.enesselcuk.moviesui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


abstract class BaseViewModel:ViewModel() {

    suspend fun <T : Any> collectFlow(
        flow: Flow<NetworkResult<T>>,
        onSuccess: (T) -> Unit,
        error: (Exception) -> Unit,
        loading: (Boolean) -> Unit
    ) {
        flow.collect {
            when (it) {
                is NetworkResult.Success -> {
                    onSuccess(it.data)
                }

                is NetworkResult.Error -> {
                    error(it.message ?: Exception())
                }

                is NetworkResult.Loading -> {
                    loading(it.isLoading ?: false)
                }
            }
        }
    }
}