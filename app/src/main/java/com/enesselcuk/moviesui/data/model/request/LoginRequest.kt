package com.enesselcuk.moviesui.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
   @SerializedName("username") val username: String? = null,
   @SerializedName("password") val password: String? = null,
   @SerializedName("request_token") val requestToken: String? = null
)
