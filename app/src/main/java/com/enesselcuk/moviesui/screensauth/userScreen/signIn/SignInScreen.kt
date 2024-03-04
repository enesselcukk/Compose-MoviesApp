package com.enesselcuk.moviesui.screensauth.userScreen.signIn


import android.annotation.SuppressLint
import android.webkit.CookieManager
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.response.LoginResponse
import com.enesselcuk.moviesui.util.Constant.LOGIN_URL


@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun SignInScreen(
    goHome: () -> Unit,
    goSignUp: () -> Unit,
) {

    val signInViewModel = hiltViewModel<SignInViewModel>()
    val usernameValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val checked = rememberSaveable { mutableStateOf(false) }
    val isLoading = rememberSaveable { mutableStateOf(false) }

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
            value = usernameValue.value,
            onValueChange = { usernameValue.value = it },
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
            value = passwordValue.value,
            shape = RoundedCornerShape(3.dp),
            onValueChange = { passwordValue.value = it },
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
            modifier = Modifier.clickable { checked.value = !checked.value }) {
            Checkbox(
                checked = checked.value,
                onCheckedChange = { checked.value = it },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.clip(RoundedCornerShape(6.dp))
            )

            Text(
                text = "Remember Me", color = MaterialTheme.colorScheme.onSurface
            )
        }

        OutlinedButton(
            onClick = {
                if (usernameValue.value.isEmpty().not() && passwordValue.value.isEmpty().not()) {
                    signInViewModel.showBottomSheet = true
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
            BottomSheet(
                { signInViewModel.showBottomSheet = it },
                signInViewModel,
                usernameValue.value,
                passwordValue.value,
                goHome::invoke
            )
        }

        SignUpView(signUp = { goSignUp.invoke() })

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


@Composable
fun SignUpView(signUp: () -> Unit) {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
            )
        ) { append("Don't Have Account?") }


        pushStringAnnotation(tag = "navigation", annotation = "sign_up")
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold
            )
        ) { append("Sign Up") }
        pop()
    }

    ClickableText(
        text = text, onClick = { offset ->
            val annotations = text.getStringAnnotations(
                tag = "navigation", start = offset, end = offset
            )
            if (annotations.isNotEmpty() && annotations[0].item == "sign_up") {
                signUp.invoke()
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 20.dp, end = 20.dp)
    )
}

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    showBottom: (Boolean) -> Unit,
    signInViewModel: SignInViewModel,
    username: String,
    password: String,
    goHome: () -> Unit,
) {

    signInViewModel.getToken()
    val createToken by signInViewModel.tokenStateFlow.collectAsState()


    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = { showBottom.invoke(false) },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {


        signInViewModel.login(LoginRequest(username, password, createToken?.requestToken))
        val loginObserver by signInViewModel.loginStateFlow.collectAsState()

        val client = object : CustomWebViewClient({
            if (loginObserver?.success == true) {
                goHome.invoke()
                showBottom.invoke(it)
            } else {
                Toast.makeText(context, "OOPS", Toast.LENGTH_LONG).show()
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

                }
            },
            update = { webView ->


                CookieManager.getInstance().removeAllCookies(null)
                CookieManager.getInstance().flush()

                webView.clearCache(true)
                webView.clearFormData()
                webView.clearHistory()
                webView.clearSslPreferences()
                webView.loadUrl(LOGIN_URL.plus(createToken?.requestToken))
            })
    }

}
