package com.enesselcuk.moviesui.screens.splash

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.di.UserSettingsModule.UserSettingDataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    isVisibleBottom: (Boolean) -> Unit,
    isVisibleTopBar: (Boolean) -> Unit,
    goHome: () -> Unit,
    goLogin: () -> Unit,
) {

    val viewModel = hiltViewModel<SplashViewModel>()

    isVisibleBottom.invoke(false)
    isVisibleTopBar.invoke(false)




    UiObserver(goHome = { goHome.invoke() }, goLogin = { goLogin.invoke() })

}

@Composable
fun UiObserver(
    viewModel: SplashViewModel = hiltViewModel(),
    goHome: () -> Unit,
    goLogin: () -> Unit
) {
    val data = viewModel.authFlow.collectAsStateWithLifecycle().value

    val isClose = remember { mutableStateOf(true) }

    when (data) {
        is MainUiState.Initial -> {}
        is MainUiState.Loading -> {}
        is MainUiState.Success -> {
            goHome.invoke()
            isClose.value = false
        }
        is MainUiState.Failure -> {}
    }

    LaunchedEffect(isClose) {
        delay(8000L)
        goLogin.invoke()
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.movie))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier.fillMaxSize()
    )


}
