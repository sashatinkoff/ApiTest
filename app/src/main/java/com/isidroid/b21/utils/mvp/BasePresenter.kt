package com.isidroid.b21.utils.mvp

import androidx.lifecycle.Lifecycle
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BasePresenter<V : IBaseView> {
    protected lateinit var viewState: V
    protected lateinit var lifecycle: Lifecycle


    fun attach(viewState: V, lifecycle: Lifecycle) {
        this.viewState = viewState
        this.lifecycle = lifecycle
    }

    abstract fun detach()
}