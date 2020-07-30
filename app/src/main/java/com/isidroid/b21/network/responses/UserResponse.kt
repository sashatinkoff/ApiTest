package com.isidroid.b21.network.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("photo") val photo: ContentResponse?
)