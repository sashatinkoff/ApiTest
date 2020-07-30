package com.isidroid.b21.clean.presentation.main

import com.isidroid.b21.clean.domain.IPostsUseCase
import com.isidroid.b21.models.Post
import com.isidroid.b21.utils.mvp.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val postsUseCase: IPostsUseCase) :
    BasePresenter<IMainView>(), IPostsUseCase.Callback {
    private var cursorNext: String? = null

    fun load(reset: Boolean) {
        if (reset) cursorNext = null
        postsUseCase.load(cursorNext = cursorNext, callback = this)
    }

    override fun detach() {
        postsUseCase.detach()
    }

    // IPostsUseCase.Callback
    override fun onError(t: Throwable) {
        viewState.onError(t)
    }

    override fun onPostsReady(posts: List<Post>?, cursor: String?, hasNext: Boolean) {
        cursorNext = cursor
        viewState.onPostsReady(posts = posts, hasNext = hasNext)
    }
}