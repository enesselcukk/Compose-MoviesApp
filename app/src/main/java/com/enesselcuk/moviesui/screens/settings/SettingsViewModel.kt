package com.enesselcuk.moviesui.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.datastore.LocalDataStore
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val localDataStoreUseCase: DataStoreUseCase) : ViewModel() {
    val themeChange = mutableStateOf(false)
    private val theme = mutableStateOf(false)
    fun getTheme():Boolean{
        viewModelScope.launch {
            theme.value = localDataStoreUseCase.invoke(Constant.THEME_KEY) == true
        }
        return theme.value
    }

    fun putTheme(value:Boolean){
        viewModelScope.launch {
           localDataStoreUseCase.invoke(Constant.THEME_KEY,value)
        }
    }

}
