package com.enesselcuk.moviesui.screens.settings

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.screens.movie.SharedViewModel
import kotlinx.coroutines.launch
import java.util.Locale

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
    screenName.invoke(stringResource(id = R.string.settings_title))
    isActionInTopBar.invoke(true)
    isChooseLiked.invoke(true)
    isVisibleSettings.invoke(true)
    sharedViewModel.isGoSettings.value = false

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LanguageAndThemeView(settingsViewModel = viewModel)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LanguageAndThemeView(
    settingsViewModel: SettingsViewModel,
) {
    val context = LocalContext.current
    settingsViewModel.themeChange.value = settingsViewModel.getTheme()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {


        val (language,languageName, theme, themeName) = createRefs()

        Text(text = stringResource(id = R.string.language),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.constrainAs(language) {
                top.linkTo(parent.top, 5.dp)
                start.linkTo(parent.start)
            })


        TextButton(onClick = {
            if (Locale.getDefault().language.equals(context.getString(R.string.tr))) {
                setLocale(context, context.getString(R.string.en))
                settingsViewModel.setUpdateLanguage(context.getString(R.string.language_en))
            } else {
                setLocale(context, context.getString(R.string.tr))
                settingsViewModel.setUpdateLanguage(context.getString(R.string.language_tr))
            }

        }, modifier = Modifier.constrainAs(languageName) {
            top.linkTo(parent.top, 5.dp)
            end.linkTo(parent.end)
        }) {
            Text(
                text = settingsViewModel.getLanguage().ifEmpty {
                    Locale.getDefault().displayLanguage
                },
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Text(text = stringResource(id = R.string.theme),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .constrainAs(theme) {
                    top.linkTo(language.bottom, 25.dp)
                    start.linkTo(parent.start)
                }
                .clickable {
                    settingsViewModel.themeChange.value = !settingsViewModel.themeChange.value
                })

        Switch(checked = settingsViewModel.themeChange.value, onCheckedChange = {
            settingsViewModel.themeChange.value = it
            when {
                settingsViewModel.themeChange.value -> settingsViewModel.putTheme(true)
                else -> settingsViewModel.putTheme(false)
            }
        }, thumbContent = {
            if (settingsViewModel.themeChange.value) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        }, modifier = Modifier.constrainAs(themeName) {
            top.linkTo(languageName.bottom, 15.dp)
            end.linkTo(parent.end)
        })
    }
}

fun setLocale(context: Context, lang: String) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
    val resources = context.resources
    val configuration = resources.configuration
    configuration.setLocale(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)
}

