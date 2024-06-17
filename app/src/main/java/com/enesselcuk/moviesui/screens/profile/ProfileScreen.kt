package com.enesselcuk.moviesui.screens.profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.di.UserSettingsModule.UserSettingDataStore
import com.enesselcuk.moviesui.screens.movie.SharedViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    isVisibleBottom: (Boolean) -> Unit,
    isVisibleTopBar: (Boolean) -> Unit,
    isVisibleTopBarBack: (Boolean) -> Unit,
    screenName: (name: String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
    firebase: Firebase = Firebase,
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
    screenName.invoke("Profile")
    isActionInTopBar.invoke(true)
    isChooseLiked.invoke(true)
    isVisibleSetting.invoke(true)

    if (sharedViewModel.isGoSettings.value) {
        goToSettings.invoke()
    }

    UserInformation(viewModel, firebase, login = { goToLogin.invoke() })

}

@Composable
fun UserInformation(
    viewModel: ProfileViewModel,
    firebase: Firebase,
    context: Context = LocalContext.current,
    login: () -> Unit
) {

    viewModel.getUser(firebase)
    val isLoadingVisible = rememberSaveable { mutableStateOf(false) }
    val isVisible = rememberSaveable { mutableStateOf(false) }

    val getUsers = viewModel.getUserFlow.collectAsStateWithLifecycle().value
    val name = rememberSaveable { mutableStateOf("") }
    val mail = rememberSaveable { mutableStateOf("") }
    val country = rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {


        when (getUsers) {
            is ProfileUiState.Initial -> {
                isLoadingVisible.value = false
            }
            is ProfileUiState.Loading -> {
                isLoadingVisible.value = true
            }
            is ProfileUiState.Success<*> -> {
                val data = getUsers.response as Map<String, Any>
                val hashMapData: HashMap<String, Any> = HashMap(data)
                val user = firebase.auth.currentUser

                name.value = hashMapData.filter { it.key == "name" }.values.toString()
                    .replace("[", "").replace("]", "")
                country.value = hashMapData.filter { it.key == "lokasyon" }.values.toString()
                    .replace("[", "").replace("]", "")

                mail.value = user?.email.orEmpty()

                isLoadingVisible.value = false
                isVisible.value = true

            }
            is ProfileUiState.Failure -> {
                isLoadingVisible.value = false
            }
        }

        if (isVisible.value) {
            Image(
                painter = painterResource(id = R.drawable.person),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            OutlinedButton(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        context.UserSettingDataStore.updateData {
                            it.toBuilder().clear().build()
                        }
                    }
                    login.invoke()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "",
                    tint =  MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Logout",
                    modifier = Modifier.padding(start = 5.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
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
