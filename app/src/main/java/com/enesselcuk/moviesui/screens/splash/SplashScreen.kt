package com.enesselcuk.moviesui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.data.model.authresponse.LoginResponse
import com.enesselcuk.moviesui.util.UiState


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    goHome: () -> Unit,
    goLogin: () -> Unit,
) {

    val viewModel = hiltViewModel<SplashViewModel>()
    val isSuccessState = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getUser()
    }

    val getUser = viewModel.loginStateFlow.collectAsStateWithLifecycle()

    handleUiState(getUser.value, callback = {
        isSuccessState.value = it
    })

    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.movie)
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = 1
    )

    LaunchedEffect(preloaderProgress) {
        when {
            preloaderProgress >= 1F && isSuccessState.value -> {
                goHome.invoke()
            }

            preloaderProgress >= 1F &&  isSuccessState.value.not() -> {
                goLogin.invoke()
            }

        }
    }

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
    )
}

private fun handleUiState(uiState: UiState<Boolean>, callback: (loginResponse: Boolean) -> Unit) {
    when (uiState) {
        is UiState.Initial -> {}
        is UiState.Loading -> {}
        is UiState.Success<Boolean> -> {
            callback(uiState.response)
        }

        is UiState.Failure -> {}
    }
}
