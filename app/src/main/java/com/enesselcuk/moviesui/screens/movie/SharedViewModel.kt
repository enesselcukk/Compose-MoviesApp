package com.enesselcuk.moviesui.screens.movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    val isBottomNavVisible = mutableStateOf(true)
    val isTopVisible = mutableStateOf(true)
    val isTopBackVisible = mutableStateOf(true)
    val isScreenName = mutableStateOf("")
    val isSelect = mutableStateOf(false)
    val isActionInTopBar = mutableStateOf(false)

    val isOpenDialog = mutableStateOf(false)
    val isChooseClick = mutableStateOf(false)
    val isVisibleSetting = mutableStateOf(false)

    val isGoSettings = mutableStateOf(false)

    val playerId = mutableStateOf(0)

    val themeChange = mutableStateOf(false)

}