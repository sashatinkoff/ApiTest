package com.isidroid.b21.clean.presentation.main

import com.isidroid.b21.clean.domain.IPostsUseCase
import com.isidroid.b21.models.Post
import com.isidroid.b21.network.ApiPosts
import com.isidroid.b21.utils.mvp.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(private val postsUseCase: IPostsUseCase) :
    BasePresenter<IMainView>(), IPostsUseCase.Callback {
    private var cursorNext: String? = null
    private var sort = ApiPosts.SortOptions.CREATED_AT

    fun load(reset: Boolean, sort: ApiPosts.SortOptions? = null) {
        sort?.let { this.sort = sort }
        if (reset) cursorNext = null
        if (reset) viewState.clearList()
        postsUseCase.load(cursorNext = cursorNext, sort = this.sort, callback = this)
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