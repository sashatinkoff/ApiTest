package com.isidroid.b21.clean.data

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractor {
    protected val schedulerIo: Scheduler by lazy { Schedulers.io() }
    protected val schedulerMain: Scheduler by lazy { AndroidSchedulers.mainThread() }
    protected var disposable: Disposable? = null
}