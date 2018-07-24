package com.exmple.corelib.http.observer

import com.exmple.corelib.http.constant.CodeStatus
import com.exmple.corelib.http.entity.BaseBean
import com.exmple.corelib.showToastBottom
import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * @FileName: com.mou.demo.http.observer.BaseObserver.java
 * @author: mouxuefei
 * @date: 2017-12-21 16:40
 * @version V1.0 配置了baseModel,状态码统一处理的observer
 * @desc
 */
abstract class BaseModelObserver<T: BaseBean> : Observer<T> {
    open  val onBegin: (() -> Unit)?=null
    open  val failError: (() -> Unit)?=null
    open  val onHandleSuccess: ((t: T?) -> Unit)?=null
    override fun onSubscribe(d: Disposable) {
        onBegin?.invoke()
    }

    override fun onNext(value: T) {
        if (value.code == CodeStatus.SUCCESS) {
            onHandleSuccess?.invoke(value)
        } else {
            onHandleError(value.msg!!)
        }
    }

    override fun onError(e: Throwable) {
        showErrorToast(e)
        failError?.invoke()
    }

    override fun onComplete() {}

    open fun codeError(code: Int) {

    }

    private fun onHandleError(msg: String) {
        if (!msg.isEmpty()) {
            showToastBottom(msg)
        } else {
            showToastBottom("未知错误")
        }
//        val message: String
//        if (code == CodeStatus.REQUEST_TIME_OUT || code == CodeStatus.REQUEST_TIME_ERROR) {
//            message = "请求超时"
//            showToastBottom(message)
//        } else if (code == CodeStatus.SERVER_ERROR) {
//            message = "服务器异常"
//            showToastBottom(message)
//        } else if (code == CodeStatus.PARAMETER_MISSING) {
//            message = "参数缺失"
//            showToastBottom(message)
//        } else if (code == CodeStatus.LOGIN_STATUS_NO_ACCOUNT) {
//            message = "您的手机号码还未注册"
//            showToastBottom(message)
//        } else if (code == CodeStatus.LOGIN_STATUS_ACCOUNT_OR_PASSWORD_ERROR) {
//            message = "账号或密码错误"
//            showToastBottom(message)
//        } else if (code == CodeStatus.VERIFICATION_CODE_ERROR || code == CodeStatus.VERIFICATION_CODE_OUT_TIME) {
//            message = "验证码错误"
//            showToastBottom(message)
//        } else if (code == CodeStatus.ACCOUNT_EXSIT) {
//            message = "您的手机号码已注册"
//            showToastBottom(message)
//        } else if (code == CodeStatus.PIN_ERROR) {
//            message = "PIN码错误"
//
//        }else{
//            codeError(code)
//        }
    }


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