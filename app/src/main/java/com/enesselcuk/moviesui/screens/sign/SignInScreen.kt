package com.enesselcuk.moviesui.screens.sign


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.data.model.authresponse.CreateResponseToken
import com.enesselcuk.moviesui.data.model.authresponse.LoginResponse
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.util.Constant.LOGIN_URL
import com.enesselcuk.moviesui.util.UiState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun SignInScreen(
    goHomeCallback: () -> Unit
) {
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    val isLoading = rememberSaveable { mutableStateOf(false) }

    val signInViewModel = hiltViewModel<SignInViewModel>()

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        Text(
            text = "Welcome Back",
            modifier = Modifier
                .padding(top = 70.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Login to your account",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 30.dp),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.outline
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            shape = RoundedCornerShape(3.dp),
            value = signInViewModel.usernameValue,
            onValueChange = { signInViewModel.setUserName(it) },
            label = { Text(text = "User Name") },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.person), contentDescription = "")
            },
            maxLines = 1
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            value = signInViewModel.passwordValue,
            shape = RoundedCornerShape(3.dp),
            onValueChange = { signInViewModel.setPassword(it) },
            label = { Text(text = "Password") },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.lock), contentDescription = ""
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        painter = if (passwordVisible.value) painterResource(id = R.drawable.eye)
                        else painterResource(id = R.drawable.eye_not), contentDescription = ""
                    )
                }
            },
            maxLines = 1
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                signInViewModel.checked = !signInViewModel.checked
            }) {
            Checkbox(
                checked = signInViewModel.checked,
                onCheckedChange = { signInViewModel.checked = it },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.clip(RoundedCornerShape(6.dp))
            )

            Text(
                text = "Remember Me", color = MaterialTheme.colorScheme.onSurface
            )
        }

        OutlinedButton(
            onClick = {
                if (signInViewModel.usernameValue.text.isEmpty()
                        .not() && signInViewModel.passwordValue.text.isEmpty().not()
                ) {
                    signInViewModel.setShowBottom(true)
                    signInViewModel.getToken()
                } else {
                    Toast.makeText(context, R.string.emailpasswordfailer, Toast.LENGTH_LONG).show()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 50.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(12.dp),
            colors = buttonColors(MaterialTheme.colorScheme.surface)
        ) {
            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }

        if (signInViewModel.showBottomSheet) {
            val createToken by signInViewModel.tokenStateFlow.collectAsStateWithLifecycle()
            BottomSheet(
                signInViewModel,
                context,
                createToken
            )

        }

        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(40.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(alignment = Alignment.CenterHorizontally), strokeWidth = 5.dp
            )
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    signInViewModel: SignInViewModel,
    context: Context = LocalContext.current,
    uiState: UiState
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    when (uiState) {
        is UiState.Initial -> {}
        is UiState.Success<*> -> {
            val response = uiState.response as CreateResponseToken
            response.requestToken?.let {
                signInViewModel.login(
                    LoginRequest(
                        signInViewModel.usernameValue.text,
                        signInViewModel.passwordValue.text,
                        it
                    )
                )
                signInViewModel.setResponseToken(it)
                Log.i("token:", signInViewModel.token)
            }
        }

        is UiState.Failure -> {}
        is UiState.Loading -> {}
    }

    if (signInViewModel.token.isEmpty().not()) {
        ModalBottomSheet(
            onDismissRequest = { signInViewModel.setShowBottom(false) },
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
        ) {

            val loginObserver by signInViewModel.loginStateFlow.collectAsStateWithLifecycle()

            val client = object : CustomWebViewClient({
                if (it) {
                    when (loginObserver) {
                        is UiState.Initial -> {}
                        is UiState.Loading -> {}
                        is UiState.Success<*> -> {
                            val loginResponse =
                                (loginObserver as UiState.Success<*>).response as LoginResponse

                            if (loginResponse.success == true) {
                             //   goHomeCallback.invoke()
                                signInViewModel.setShowBottom(false)
                                signInViewModel.saveUser()
                            } else {
                                Toast.makeText(context, "hata", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }

                        is UiState.Failure -> {}
                    }
                } else {
                    signInViewModel.setShowBottom(false)
                }
            }) {}



            AndroidView(
                factory = { context ->
                    android.webkit.WebView(context).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = client

                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                        settings.setSupportZoom(true)

                        this.clearCache(true)
                        this.clearFormData()
                        this.clearHistory()
                        this.clearSslPreferences()
                        this.loadUrl(LOGIN_URL.plus(signInViewModel.token))
                        signInViewModel.setResponseToken("")
                    }
                })


        }
    }
}