package com.enesselcuk.moviesui.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
   @SerializedName("success") val success:Boolean,
   @SerializedName("expires_at") val expiresAt:String,
   @SerializedName("request_token") val requestToken:String
)
