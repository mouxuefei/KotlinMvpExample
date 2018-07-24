package com.exmple.corelib.mvp

open class BasePresenterKt<V : ITopView> {
    var mView: V? = null
}