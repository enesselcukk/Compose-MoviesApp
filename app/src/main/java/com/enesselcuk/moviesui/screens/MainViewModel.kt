package com.enesselcuk.moviesui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enesselcuk.moviesui.datastore.LocalDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val localDataStore: LocalDataStore) : ViewModel() {

    private val theme = mutableStateOf(false)

    fun getTheme():Boolean{
        viewModelScope.launch {
          theme.value = localDataStore.getBoolean("theme") == true
        }
       return theme.value
    }


}
