package com.exmple.baselib.mvp

open class BasePresenterKt<V : ITopView> {
    var mView: V? = null
}