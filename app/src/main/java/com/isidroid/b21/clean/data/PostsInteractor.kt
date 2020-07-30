package com.isidroid.b21.clean.data

import com.isidroid.b21.clean.domain.IPostsUseCase
import com.isidroid.b21.models.dto.PostsDto
import com.isidroid.b21.network.ApiPosts
import timber.log.Timber
import javax.inject.Inject

class PostsInteractor @Inject constructor(private val apiPosts: ApiPosts) : BaseInteractor(),
    IPostsUseCase {

    override fun load(cursorNext: String?, callbackUseCase: IPostsUseCase.Callback) {
        disposable = apiPosts.posts(after = cursorNext)
            .map {
                PostsDto(
                    posts = PostRepository.toPosts(it),
                    cursor = it.data.cursor,
                    hasNext = it.data.cursor != null
                )
            }
            .subscribeOn(schedulerIo)
            .observeOn(schedulerMain)
            .subscribe(
                {
                    callbackUseCase.onPostsReady(
                        posts = it.posts,
                        cursor = it.cursor,
                        hasNext = it.hasNext
                    )
                },
                { callbackUseCase.onError(it) }
            )
    }

    override fun detach() {
        disposable?.dispose()
    }
}