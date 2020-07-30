package com.isidroid.b21.models.dto

import com.isidroid.b21.models.Post

data class PostsDto(
    val posts: List<Post>,
    val cursor: String?,
    val hasNext: Boolean
)