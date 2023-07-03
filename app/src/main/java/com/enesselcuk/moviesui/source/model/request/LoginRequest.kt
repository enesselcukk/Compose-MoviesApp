package com.enesselcuk.moviesui.source.model.request

data class LoginRequest(
    val username: String? = null,
    val password: String? = null,
    val request_token: String? = null
)
