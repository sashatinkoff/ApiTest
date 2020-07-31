package com.isidroid.b21.clean.presentation.main

import android.widget.ImageView
import com.isidroid.b21.models.Post
import com.isidroid.b21.utils.mvp.IBaseView

interface IMainView : IBaseView {
    fun onPostsReady(
        posts: List<Post>?,
        hasNext: Boolean
    )

    fun clearList()
    fun open(post: Post)
}