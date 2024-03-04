package com.enesselcuk.moviesui.screens.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.enesselcuk.moviesui.screensauth.userScreen.signIn.SignInScreen
import com.enesselcuk.moviesui.ui.theme.MoviesUiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MoviesUiTheme(darkTheme = mainViewModel.getTheme()) {
                SignInScreen(
                    goHome = {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        startActivity(intent)
                    },
                    goSignUp = { /*TODO*/ },
                )
            }
        }
    }
}
