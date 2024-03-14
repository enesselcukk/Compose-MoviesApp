package com.enesselcuk.moviesui.screens.activity

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.util.Constant.THEME_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val localDataStoreUseCase: DataStoreUseCase) : ViewModel() {

    private val theme = mutableStateOf(false)

    fun getTheme():Boolean{
        viewModelScope.launch {
          theme.value = localDataStoreUseCase.invoke(THEME_KEY) == true
        }
       return theme.value
    }

}
