package com.enesselcuk.moviesui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.enesselcuk.moviesui.screens.movie.SharedViewModel
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
) {

    val viewModel = hiltViewModel<SettingsViewModel>()
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
        LanguageAndThemeView(viewModel = viewModel)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LanguageAndThemeView(
    viewModel: SettingsViewModel,
) {
    val composableScope = rememberCoroutineScope()

    viewModel.themeChange.value = viewModel.getTheme()

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

                    }
                })

        Switch(
            checked = viewModel.themeChange.value,
            onCheckedChange = {
                viewModel.themeChange.value = it
                when {
                    viewModel.themeChange.value -> viewModel.putTheme("theme", true)
                    else -> viewModel.putTheme("theme", false)
                }
            },
            thumbContent = {
                if (viewModel.themeChange.value) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            },
            modifier = Modifier.constrainAs(themeName) {
                top.linkTo(languageName.bottom, 15.dp)
                end.linkTo(parent.end)
            })


    }
}

