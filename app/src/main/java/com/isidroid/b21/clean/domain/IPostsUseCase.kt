package com.isidroid.b21.clean.domain

interface IPostsUseCase: IBaseUseCase {
    fun load(cursorNext: String?, callback: Callback)

    interface Callback : CallbackUseCase {

    }
}