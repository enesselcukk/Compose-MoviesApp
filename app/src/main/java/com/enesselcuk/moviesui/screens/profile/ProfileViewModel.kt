package com.enesselcuk.moviesui.screens.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {


    private val _getUserFlow = MutableStateFlow<ProfileUiState>(ProfileUiState.Initial)
    val getUserFlow = _getUserFlow.asStateFlow()


    fun getUser(firebase: Firebase) {

         val user = firebase.auth.currentUser

        val userDb = firebase.firestore.collection("users").document("user")
        userDb.get()
            .addOnSuccessListener {
                _getUserFlow.value = ProfileUiState.Success(it.data)
            }.addOnFailureListener {
                _getUserFlow.value = ProfileUiState.Failure(it.message.toString())
            }

    }

}
