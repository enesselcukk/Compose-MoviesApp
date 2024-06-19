package com.enesselcuk.moviesui.data.model.response

import com.google.gson.annotations.SerializedName

data class AccountDetailsResponse(
    @SerializedName("avatar") val avatar: Avatar? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("iso_639_1") val language: String? = null,
    @SerializedName("iso_3166_1") val country: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("include_adult") val includeAdult: Boolean? = null,
    @SerializedName("username") val username: String? = null,
)

data class Avatar(
    @SerializedName("gravatar") val gravatar: Gravatar? = null,
    @SerializedName("tmdb") val tmdb: Tmdb? = null,
    )

data class Tmdb(
    @SerializedName("avatar_path") val avatarPath: String? = null,
)

data class Gravatar(
    @SerializedName("hash") val hash: String? = null,
)

