package com.isidroid.b21.clean.domain

import com.isidroid.b21.network.ApiPosts

interface IPostsUseCase: IBaseUseCase {
    fun load(cursorNext: String?, sort: ApiPosts.SortOptions, callback: Callback)

    interface Callback : CallbackUseCase {

    }
}