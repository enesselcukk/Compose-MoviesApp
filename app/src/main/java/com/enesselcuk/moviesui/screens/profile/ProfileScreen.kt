package com.enesselcuk.moviesui.screens.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.data.model.response.AccountDetailsResponse
import com.enesselcuk.moviesui.screens.movie.SharedViewModel
import com.enesselcuk.moviesui.util.Constant
import com.enesselcuk.moviesui.util.UiState


@Composable
fun ProfileScreen(
    isVisibleBottom: (Boolean) -> Unit,
    isVisibleTopBar: (Boolean) -> Unit,
    isVisibleTopBarBack: (Boolean) -> Unit,
    screenName: (name: String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
    isActionInTopBar: (isVisible: Boolean) -> Unit,
    isChooseLiked: (isVisible: Boolean) -> Unit,
    goToLogin: () -> Unit,
    isVisibleSetting: (isVisible: Boolean) -> Unit,
    sharedViewModel: SharedViewModel,
    goToSettings: () -> Unit
) {
    isVisibleBottom.invoke(true)
    isVisibleTopBar.invoke(true)
    isVisibleTopBarBack.invoke(false)
    screenName.invoke(stringResource(id = R.string.profile_title))
    isActionInTopBar.invoke(true)
    isChooseLiked.invoke(true)
    isVisibleSetting.invoke(true)

    if (sharedViewModel.isGoSettings.value) {
        goToSettings.invoke()
    }

    UserInformation(viewModel, login = { goToLogin.invoke() })

}

@Composable
fun UserInformation(
    profileViewModel: ProfileViewModel,
    login: () -> Unit
) {
    LaunchedEffect(Unit) {
        profileViewModel.getUser()
    }

    val isLoadingVisible = rememberSaveable { mutableStateOf(false) }
    val isVisible = rememberSaveable { mutableStateOf(false) }

    val getUsers = profileViewModel.getUserFlow.collectAsStateWithLifecycle(UiState.Initial).value
    val name = rememberSaveable { mutableStateOf("") }
    val mail = rememberSaveable { mutableStateOf("") }
    val country = rememberSaveable { mutableStateOf("") }
    val image = rememberSaveable { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {

        // TODO burada kaldın fotoğraf ve diğer bilgileri doldur.

        LaunchedEffect(getUsers) {
            when (getUsers) {
                is UiState.Initial -> {
                    isLoadingVisible.value = false
                }

                is UiState.Loading -> {
                    isLoadingVisible.value = true
                }

                is UiState.Success<AccountDetailsResponse> -> {
                    val response = getUsers.response

                    name.value = response.name.orEmpty()
                    country.value = response.country.orEmpty()
                    image.value = response.avatar?.tmdb?.avatarPath.orEmpty()
                    mail.value = "oops"

                    isLoadingVisible.value = false
                    isVisible.value = true

                }

                is UiState.Failure -> {
                    isLoadingVisible.value = false
                }
            }
        }

        AsyncImage(
            model = "${Constant.IMAGE_BASE_W500}${image.value}",
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
                .size(80.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )

        Text(
            text = name.value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        )
        Text(
            text = mail.value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Name", color = MaterialTheme.colorScheme.primary) },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.person), contentDescription = "")
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        OutlinedTextField(
            value = mail.value,
            onValueChange = { mail.value = it },
            label = { Text(text = "E-Mail", color = MaterialTheme.colorScheme.primary) },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.email), contentDescription = "")
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = country.value,
            onValueChange = { country.value = it },
            label = {
                Text(
                    text = "Country",
                    color = MaterialTheme.colorScheme.primary
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = ""
                )
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )


        OutlinedButton(
            onClick = {
                profileViewModel.clearUsers()
                login.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logout),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Logout",
                modifier = Modifier.padding(start = 5.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }


        if (isLoadingVisible.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(40.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(alignment = Alignment.CenterHorizontally), strokeWidth = 5.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
