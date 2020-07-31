package com.isidroid.b21.clean.presentation.detail

import com.isidroid.b21.models.Post
import com.isidroid.b21.utils.mvp.IBaseView

interface IDetailView: IBaseView {
    fun onReady(post: Post)
}