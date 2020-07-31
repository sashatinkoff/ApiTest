package com.isidroid.b21.clean.presentation.detail

import com.isidroid.b21.models.Post
import com.isidroid.b21.utils.mvp.BasePresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor() : BasePresenter<IDetailView>() {
    fun create(post: Post) {
        viewState.onReady(post)
    }

    override fun detach() {

    }
}