package com.exmple.baselib.mvp

import io.reactivex.disposables.CompositeDisposable

open class BaseModelKt {
    val mDisposablePool: CompositeDisposable by lazy { CompositeDisposable() }
}