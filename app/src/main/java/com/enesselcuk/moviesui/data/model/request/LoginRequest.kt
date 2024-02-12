package com.enesselcuk.moviesui.data.model.request

data class LoginRequest(
    val username: String? = null,
    val password: String? = null,
    val request_token: String? = null
)
