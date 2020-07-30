package com.isidroid.b21.di

import com.isidroid.b21.clean.data.PostsInteractor
import com.isidroid.b21.clean.domain.IPostsUseCase
import com.isidroid.b21.clean.presentation.main.MainPresenter
import com.isidroid.b21.network.ApiPosts
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @JvmStatic
    @Provides
    @Singleton
    fun providePost(apiPosts: ApiPosts): IPostsUseCase = PostsInteractor(apiPosts)
}