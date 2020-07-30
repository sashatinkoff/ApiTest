package com.isidroid.b21.network.responses

import com.google.gson.annotations.SerializedName

data class ContentResponse(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("data") val data: ContentData
)

data class ContentData(
    @SerializedName("extraSmall") val extraSmall: ContentImage?,
    @SerializedName("small") val small: ContentImage?,
    @SerializedName("original") val original: ContentImage?,
    @SerializedName("value") val value: String?,
    @SerializedName("values") val values: List<String>?
)

data class ContentImage(
    @SerializedName("url") val url: String?
)