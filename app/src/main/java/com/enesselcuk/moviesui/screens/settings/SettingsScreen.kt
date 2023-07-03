package com.enesselcuk.moviesui.screens.settings

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.enesselcuk.moviesui.screens.movie.SharedViewModel
import com.enesselcuk.moviesui.util.PreferencesDataStoreStatus
import com.enesselcuk.moviesui.util.PreferencesDataStoreStatus.dataStore
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    isVisibleBottom: (Boolean) -> Unit,
    isVisibleTopBar: (Boolean) -> Unit,
    isVisibleTopBarBack: (Boolean) -> Unit,
    screenName: (name: String) -> Unit,
    isActionInTopBar: (isVisible: Boolean) -> Unit,
    isChooseLiked: (isVisible: Boolean) -> Unit,
    isVisibleSettings: (isVisible: Boolean) -> Unit,
    sharedViewModel: SharedViewModel,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    isVisibleBottom.invoke(false)
    isVisibleTopBar.invoke(true)
    isVisibleTopBarBack.invoke(true)
    screenName.invoke("Settings")
    isActionInTopBar.invoke(true)
    isChooseLiked.invoke(true)
    isVisibleSettings.invoke(true)
    sharedViewModel.isGoSettings.value = false

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThemeStatus(viewModel = viewModel)
        LanguageAndThemeView(contentTheme = {
        }, viewModel = viewModel)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LanguageAndThemeView(
    contentTheme: (isChange: Boolean) -> Unit,
    context: Context = LocalContext.current,
    viewModel: SettingsViewModel
) {
    val composableScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {

        val (language, languageName, theme, themeName) = createRefs()

        Text(
            text = "Language",
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.constrainAs(language) {
                top.linkTo(parent.top, 5.dp)
                start.linkTo(parent.start)
            })

        Text(
            text = "Turkiye",
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.constrainAs(languageName) {
                top.linkTo(parent.top, 5.dp)
                end.linkTo(parent.end)
            })

        Text(
            text = "Theme",
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .constrainAs(theme) {
                    top.linkTo(language.bottom, 15.dp)
                    start.linkTo(parent.start)
                }
                .clickable {
                    viewModel.themeChange.value = !viewModel.themeChange.value
                    composableScope.launch {
                        if (viewModel.themeChange.value) {
                            PreferencesDataStoreStatus.status(context, true)
                        } else {
                            PreferencesDataStoreStatus.status(context, false)
                        }
                    }
                })

        Text(
            text = if (viewModel.themeChange.value) "Dark" else "Light",
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.constrainAs(themeName) {
                top.linkTo(languageName.bottom, 15.dp)
                end.linkTo(parent.end)
            })


    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ThemeStatus(context: Context = LocalContext.current, viewModel: SettingsViewModel) {
    val rememberScope = rememberCoroutineScope()

    rememberScope.launch {
        context.dataStore.data.collectLatest {
            when (it[PreferencesDataStoreStatus.dataStoreDarkKey]) {
                true -> viewModel.themeChange.value = true
                false -> viewModel.themeChange.value = false
                else -> viewModel.themeChange.value = false

            }
        }
    }


}

