package com.isidroid.b21.clean.data

import com.isidroid.b21.models.Image
import com.isidroid.b21.models.User
import com.isidroid.b21.network.responses.UserResponse

object UserRepository {
    fun toUser(response: UserResponse?) = response?.let {
        User(
            id = response.id,
            name = response.name.trim(),
            image = response.photo?.data?.original?.url
        )
    }
}