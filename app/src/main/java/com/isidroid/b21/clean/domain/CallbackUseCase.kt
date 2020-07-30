package com.isidroid.b21.clean.domain

import com.isidroid.b21.models.Post

interface CallbackUseCase {
    fun onError(t: Throwable)
    fun onPostsReady(posts: List<Post>?, cursor: String?, hasNext: Boolean)
}