package com.enesselcuk.moviesui.screens.userScreen.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _registerFlow = MutableStateFlow<RegisterUiState>(RegisterUiState.Initial)
    val registerUiState = _registerFlow.asStateFlow()

    var registerInfo = mutableStateOf("")
        private set

    fun register(
        email: String,
        password: String,
        name: String,
        lokasyon: String,
        store: Firebase,
        auth: FirebaseAuth
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            _registerFlow.value = RegisterUiState.Loading
            if (task.isSuccessful) {
                val usersInfo = hashMapOf(
                    "name" to name,
                    "lokasyon" to lokasyon
                )
                store.firestore.collection("users").document("user").set(usersInfo)
                    .addOnSuccessListener {
                        _registerFlow.value = RegisterUiState.Success(store,auth)
                    }
                    .addOnFailureListener { exception ->
                        _registerFlow.value = RegisterUiState.Failure(exception.message.toString())
                    }

            } else {
                _registerFlow.value = RegisterUiState.Failure("Giriş Bilgilerinizi Doğru Giriniz.")
            }
        }

    }
}

const val SUCCESS = "Success"

