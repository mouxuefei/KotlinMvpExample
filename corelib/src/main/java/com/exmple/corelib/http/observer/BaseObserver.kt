package com.exmple.corelib.http.observer

import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import com.exmple.corelib.showToastBottom
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * @FileName: com.mou.demo.http.observer.BaseObserver.java
 * @author: mouxuefei
 * @date: 2017-12-21 16:40
 * @version V1.0 不封装BaseModel,服务器状态码统一返回的observer
 * @desc
 */
abstract class BaseObserver<T> : Observer<T> {
    var mDisposable: Disposable? = null
    override fun onSubscribe(d: Disposable) {
        mDisposable = d
        OnBegin()
    }

    override fun onNext(value: T) {
        onHandleSuccess(value)
    }

    override fun onError(e: Throwable) {
        requestError()
        showErrorToast(e)
    }

    override fun onComplete() {

    }

    open fun OnBegin() {}

    protected abstract fun onHandleSuccess(t: T?)

    open fun requestError() {}

    private fun showErrorToast(e: Throwable) {
        Logger.e("exception=${e.toString()}")
        if (e is SocketTimeoutException || e is ConnectException) {
            showToastBottom("连接失败,请检查网络状况!")
        } else if (e is JsonParseException) {
            showToastBottom("数据解析失败")
        } else {
            showToastBottom("请求失败")
        }
    }
}
