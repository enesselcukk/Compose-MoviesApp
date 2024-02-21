package com.enesselcuk.moviesui.screensauth.userScreen.signIn


import android.annotation.SuppressLint
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.di.UserSettingsModule.UserSettingDataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignInScreen(
    isTopBarVisibility: (visible: Boolean) -> Unit,
    goHome: () -> Unit,
    goSignUp: () -> Unit,
    isBottomVisible: (visible: Boolean) -> Unit
) {

    val signInViewModel = hiltViewModel<SignInViewModel>()
    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val checked = rememberSaveable { mutableStateOf(false) }
    val isLoading = rememberSaveable { mutableStateOf(false) }

    isTopBarVisibility.invoke(false)
    isBottomVisible.invoke(false)

    val context = LocalContext.current
    val loginObserver by signInViewModel.loginStateFlow.collectAsStateWithLifecycle()
    val dataStoreRemember = rememberCoroutineScope()

    when (loginObserver) {
        is SignTmdbInState.Initial -> {
            isLoading.value = false
        }
        is SignTmdbInState.Loading -> {
            isLoading.value = true
        }
        is SignTmdbInState.Success -> {
            isLoading.value = false
            dataStoreRemember.launch {
                if (checked.value) {
                    context.UserSettingDataStore.updateData { users ->
                        users.toBuilder()
                            .setUsername(emailValue.value)
                            .setPassword(passwordValue.value)
                            .build()
                    }
                }
            }
            goHome.invoke()
        }
        is SignTmdbInState.Failure -> {
            val fail = (loginObserver as SignTmdbInState.Failure).errorMessage
            Toast.makeText(context, fail, Toast.LENGTH_SHORT).show()
            isLoading.value = false
        }
    }

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
            value = emailValue.value,
            onValueChange = { emailValue.value = it },
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
                signInViewModel.login(emailValue.value, passwordValue.value)
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

