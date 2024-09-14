package com.enesselcuk.moviesui.ui.screen.player

import androidx.lifecycle.ViewModel
import com.enesselcuk.moviesui.domain.repository.Repos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val repos: Repos) : ViewModel() {

}