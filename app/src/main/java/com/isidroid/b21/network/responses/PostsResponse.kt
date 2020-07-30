package com.isidroid.b21.network.responses

import com.google.gson.annotations.SerializedName
import com.isidroid.b21.models.Post
import java.util.*

data class PostsResponse(
    @SerializedName("data") val data: PostsDataResponse
)

data class PostsDataResponse(
    @SerializedName("items") val items: List<PostResponse>,
    @SerializedName("cursor") val cursor: String?
)

data class PostResponse(
    @SerializedName("id") val id: String,
    @SerializedName("status") val status: String,
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("contents") val contents: List<ContentResponse>,
    @SerializedName("author") val author: UserResponse?,
    @SerializedName("stats") val stats: PostStatsResponse?
)

data class PostStatsResponse(
    @SerializedName("likes") val likes: PostStatItemResponse,
    @SerializedName("views") val views: PostStatItemResponse,
    @SerializedName("comments") val comments: PostStatItemResponse,
    @SerializedName("shares") val shares: PostStatItemResponse
)

data class PostStatItemResponse(@SerializedName("count") val count: Int)