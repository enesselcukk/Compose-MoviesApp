package com.enesselcuk.moviesui.ui.screen.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.enesselcuk.moviesui.ui.screen.splash.SplashScreen
import com.enesselcuk.moviesui.ui.theme.MoviesUiTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel = hiltViewModel<ActivityViewModel>()
            MoviesUiTheme(darkTheme = mainViewModel.getTheme()) {
                SplashScreen(
                    goHome = {
                        startActivity(Intent(baseContext, MainActivity::class.java))
                        finish()
                    },
                    goLogin = {
                        startActivity(Intent(baseContext, LoginActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}
