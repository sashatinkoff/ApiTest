package com.isidroid.b21.clean.data

import com.isidroid.b21.models.Image
import com.isidroid.b21.models.Post
import com.isidroid.b21.network.responses.ContentResponse
import com.isidroid.b21.network.responses.ContentType
import com.isidroid.b21.network.responses.PostResponse
import com.isidroid.b21.network.responses.PostsResponse
import java.util.*

object PostRepository {
    fun toPosts(response: PostsResponse) = response.data.items.map { toPost(it) }
    fun toPost(response: PostResponse) = Post(
        id = response.id,
        _status = response.status,
        createdAt = Date(response.createdAt * 1000L),
        user = UserRepository.toUser(response.author)
    )
        .apply {
            likes = response.stats?.likes?.count ?: 0
            views = response.stats?.views?.count ?: 0
            shares = response.stats?.shares?.count ?: 0

            images = response.contents.filter { isImage(it) }.map {
                Image(
                    small = it.data.small?.url.orEmpty(),
                    extraSmall = it.data.extraSmall?.url.orEmpty()
                )
            }
            text = response.contents.firstOrNull { isText(it) }?.data?.value
            tags = response.contents.firstOrNull { isTags(it) }?.data?.values
        }

    private fun isTags(response: ContentResponse) =
        with(response) { type == ContentType.Tags.api && data.values != null }

    private fun isText(response: ContentResponse) =
        with(response) { type == ContentType.Text.api && data.value != null }

    private fun isImage(response: ContentResponse) =
        with(response) { type == ContentType.Image.api && data.extraSmall?.url != null && data.small?.url != null }
}
