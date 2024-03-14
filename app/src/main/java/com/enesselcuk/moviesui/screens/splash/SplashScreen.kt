package com.enesselcuk.moviesui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.enesselcuk.moviesui.R


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    goHome: () -> Unit,
    goLogin: () -> Unit,
) {
    val viewModel = hiltViewModel<SplashViewModel>()

    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.movie))

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = 1
    )

    LaunchedEffect(preloaderProgress) {
        when {
            preloaderProgress >= 1F && viewModel.getLogin() -> {
                goHome.invoke()
            }
            preloaderProgress >= 1F && viewModel.getLogin().not() -> {
                goLogin.invoke()
            }
        }
    }

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
    )
}
