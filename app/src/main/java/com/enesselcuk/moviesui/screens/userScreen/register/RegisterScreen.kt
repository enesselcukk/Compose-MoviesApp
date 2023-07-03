package com.enesselcuk.moviesui.screens.userScreen.register

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.enesselcuk.moviesui.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.compose.material3.MaterialTheme.colorScheme


@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    goHome: () -> Unit
) {

    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    val nameValue = rememberSaveable { mutableStateOf("") }
    val countryValue = rememberSaveable { mutableStateOf("") }

    var passwprdVisible = rememberSaveable { mutableStateOf(false) }
    val isLoadingVisible = rememberSaveable { mutableStateOf(false) }

    val register by viewModel.registerUiState.collectAsStateWithLifecycle()

    when (register) {
        is RegisterUiState.Initial -> {
            isLoadingVisible.value = false
        }
        is RegisterUiState.Loading -> {
            isLoadingVisible.value = true
        }
        is RegisterUiState.Success<*> -> {
            isLoadingVisible.value = false
            goHome.invoke()
        }
        is RegisterUiState.Failure -> {
            val fail = (register as RegisterUiState.Failure).error
            Toast.makeText(LocalContext.current, fail, Toast.LENGTH_SHORT).show()
            isLoadingVisible.value = false
        }
    }


    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Register", fontSize = 26.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 70.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "Create your new account", fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 30.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )


        OutlinedTextField(
            value = nameValue.value,
            onValueChange = { nameValue.value = it },
            label = { Text(text = "Name") },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.person), contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = emailValue.value,
            shape = RoundedCornerShape(3.dp),
            onValueChange = { emailValue.value = it },
            label = { Text(text = "Email") },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.email), contentDescription = "")
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = passwordValue.value,
            onValueChange = { passwordValue.value = it },
            shape = RoundedCornerShape(3.dp),
            label = { Text(text = "Password") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.lock),
                    contentDescription = ""
                )
            },

            visualTransformation = if (passwprdVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwprdVisible.value = !passwprdVisible.value }) {
                    Icon(
                        painter = if (passwprdVisible.value) painterResource(id = R.drawable.eye) else painterResource(
                            id = R.drawable.eye_not
                        ),
                        contentDescription = "",
                    )
                }
            }
        )

        OutlinedTextField(
            value = countryValue.value,
            onValueChange = { countryValue.value = it },
            label = { Text(text = "Country") },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.location), contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        OutlinedButton(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(top = 50.dp),
            colors = buttonColors(MaterialTheme.colorScheme.surface),
            onClick = {
                val firebase = Firebase
                viewModel.register(
                    emailValue.value,
                    passwordValue.value,
                    nameValue.value,
                    countryValue.value,
                    firebase,
                    firebase.auth
                )
            }) {
            Text(text = "Sign Up", color = MaterialTheme.colorScheme.onSurface)
        }
        if (isLoadingVisible.value) {
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


