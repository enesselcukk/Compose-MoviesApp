package com.enesselcuk.moviesui.screens.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.enesselcuk.moviesui.screens.sign.SignInScreen
import com.enesselcuk.moviesui.ui.theme.MoviesUiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel = hiltViewModel<ActivityViewModel>()
            MoviesUiTheme(darkTheme = mainViewModel.getTheme()) {
                SignInScreen(
                    goHomeCallback = {
                        startActivity(Intent(baseContext, MainActivity::class.java))
                        finish()
                    }

                )
            }
        }
    }
}
