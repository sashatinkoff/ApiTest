package com.isidroid.b21.di

import com.isidroid.b21.clean.domain.IPostsUseCase
import com.isidroid.b21.clean.presentation.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
object MvpModule {
    @JvmStatic
    @Provides
    fun provideMainPresenter(postsUseCase: IPostsUseCase) = MainPresenter(postsUseCase)
}