package com.enesselcuk.moviesui.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.moviesui.datastore.LocalDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val localDataStore: LocalDataStore) : ViewModel() {
    val themeChange = mutableStateOf(false)
    private val theme = mutableStateOf(false)
    fun getTheme():Boolean{
        viewModelScope.launch {
            theme.value = localDataStore.getBoolean("theme") == true
        }
        return theme.value
    }

    fun putTheme(key:String,value:Boolean){
        viewModelScope.launch {
            localDataStore.putBoolean(key,value)
        }
    }

    fun remove(key: String){
        viewModelScope.launch {
            localDataStore.remove(key)
        }
    }

    fun clear(){
        viewModelScope.launch{
            localDataStore.clear()
        }
    }
}
