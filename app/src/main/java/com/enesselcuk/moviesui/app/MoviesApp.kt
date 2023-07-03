package com.enesselcuk.moviesui.app

import android.app.Application
import com.enesselcuk.moviesui.util.PreferencesDataStoreStatus
import com.enesselcuk.moviesui.util.PreferencesDataStoreStatus.dataStore
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collectLatest

@HiltAndroidApp
class MoviesApp : Application()