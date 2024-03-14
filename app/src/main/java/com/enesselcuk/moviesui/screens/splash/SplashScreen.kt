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
import com.airbnb.lottie.compose.LottieConstants
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
    goHome: () -> Unit,
    goLogin: () -> Unit,
) {

    val viewModel = hiltViewModel<SplashViewModel>()

    UiObserver(goHome = { goHome.invoke() }, goLogin = { goLogin.invoke() })

}

@Composable
fun UiObserver(
    goHome: () -> Unit,
    goLogin: () -> Unit
) {

    val viewModel = hiltViewModel<SplashViewModel>()

    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.movie))

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = 1
    )

    LaunchedEffect(preloaderProgress) {
        if(preloaderProgress >= 1F){
            goLogin.invoke()
        }
    }

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
    )


}
