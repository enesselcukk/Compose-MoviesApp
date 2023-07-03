package com.enesselcuk.moviesui.screens.player

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor():ViewModel() {
   val isPlaying = mutableStateOf(false)

}