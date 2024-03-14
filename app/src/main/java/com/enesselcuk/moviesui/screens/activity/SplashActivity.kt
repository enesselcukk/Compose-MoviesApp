package com.enesselcuk.moviesui.screens.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.enesselcuk.moviesui.screens.splash.SplashScreen
import com.enesselcuk.moviesui.ui.theme.MoviesUiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel = hiltViewModel<ViewModel>()
            MoviesUiTheme(darkTheme = mainViewModel.getTheme()) {
                SplashScreen(
                    goHome = {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    },
                    goLogin = {
                        val intent = Intent(baseContext, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}