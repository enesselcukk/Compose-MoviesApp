package com.enesselcuk.moviesui.ui.screen.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import com.enesselcuk.moviesui.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val localDataStoreUseCase: DataStoreUseCase,
    var savedStateHandle: SavedStateHandle
) : ViewModel() {
    val themeChange = mutableStateOf(false)
    private val theme = mutableStateOf(false)
    fun getTheme(): Boolean {
        viewModelScope.launch {
            theme.value = localDataStoreUseCase.invoke(Constant.THEME_KEY) == true
        }
        return theme.value
    }

    fun putTheme(value: Boolean) {
        viewModelScope.launch {
            localDataStoreUseCase.invoke(Constant.THEME_KEY, value)
        }
    }

    private var updateLocalLanguage by savedStateHandle.saveable { mutableStateOf("") }

    fun setUpdateLanguage(languageName: String) {
        updateLocalLanguage = languageName
    }

    fun getLanguage(): String = updateLocalLanguage




}
