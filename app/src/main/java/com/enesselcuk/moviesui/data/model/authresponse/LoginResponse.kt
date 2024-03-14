package com.enesselcuk.moviesui.data.model.authresponse

import com.google.gson.annotations.SerializedName

data class LoginResponse(
   @SerializedName("success") val success:Boolean?= null,
   @SerializedName("expires_at") val expiresAt:String?= null,
   @SerializedName("request_token") val requestToken:String?= null
)
