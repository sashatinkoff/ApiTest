package com.isidroid.b21.models

import java.util.Date

data class Post(
    val id: String,
    val createdAt: Date,
    val user: User? = null,
    private val _status: String
) {
    enum class Status(val api: String) {
        Published("PUBLISHED")
    }

    var images: List<Image>? = null
    var text: String? = null
    var tags: List<String>? = null
    var likes = 0
    var views = 0
    var shares = 0

    val status = Status.values().firstOrNull { it.api == _status }
}