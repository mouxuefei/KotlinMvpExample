package com.exmple.corelib

import android.content.Context
import android.support.annotation.StringRes
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.exmple.corelib.base.LibApplication
import com.exmple.corelib.http.constant.CodeStatus
import com.exmple.corelib.http.entity.BaseBean
import com.exmple.corelib.mvp.IListView
import com.exmple.corelib.mvp.IModel
import com.exmple.corelib.mvp.ITopPresenter
import com.exmple.corelib.mvp.IView
import com.exmple.corelib.scheduler.SchedulerUtils
import com.exmple.corelib.utils.NetworkUtils
import com.google.gson.JsonParseException
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import org.jetbrains.annotations.NotNull
import java.net.ConnectException
import java.net.SocketTimeoutException


fun showToastBottom(@NotNull msg: String) {
    val sToast = Toast.makeText(LibApplication.mContext, msg, Toast.LENGTH_SHORT)
    sToast.show()
}

fun showToastBottom(@StringRes msgResId: Int) {
    val sToast = Toast.makeText(LibApplication.mContext, LibApplication.mContext.resources.getString(msgResId), Toast.LENGTH_SHORT)
    sToast.show()
}

fun showToastCenter(@NotNull msg: String) {
    val sToast = Toast.makeText(LibApplication.mContext, msg, Toast.LENGTH_SHORT)
    sToast.setGravity(Gravity.CENTER, 0, 0)
    sToast.setText(msg)
    sToast.show()
}

fun showToastCenter(@StringRes msgResId: Int) {
    val sToast = Toast.makeText(LibApplication.mContext, LibApplication.mContext.resources.getString(msgResId), Toast.LENGTH_SHORT)
    sToast.setGravity(Gravity.CENTER, 0, 0)
    sToast.setText(LibApplication.mContext.resources.getString(msgResId))
    sToast.show()
    sToast.show()
}

fun register(@NotNull obj: Any) {
    if (!EventBus.getDefault().isRegistered(obj)) {
        EventBus.getDefault().register(obj)
    }
}

fun unregister(@NotNull obj: Any) {
    if (EventBus.getDefault().isRegistered(obj)) {
        EventBus.getDefault().unregister(obj)
    }
}

fun sendEvent(@NotNull obj: Any) {
    EventBus.getDefault().post(obj)
}

fun TextView.stringTrim(): String {
    return this.text.toString().trim()
}


/**
 * dp转px
 */
fun View.dp2px(dipValue: Float): Float {
    return (dipValue * this.resources.displayMetrics.density + 0.5f)
}

/**
 * px转dp
 */
fun View.px2dp(pxValue: Float): Float {
    return (pxValue / this.resources.displayMetrics.density + 0.5f)
}

/**
 * sp转px
 */
fun Context.sp2px(spValue: Float): Float {
    return (spValue * this.resources.displayMetrics.scaledDensity + 0.5f)
}

fun <T : BaseBean, P : ITopPresenter> Observable<T>.mSubscribe(
        iBaseView: IView<P>? = null
        , iModel: IModel? = null
        , msg: String = ""
        , onSuccess: (T) -> Unit) {
    this.compose(SchedulerUtils.ioToMain())
            .subscribe(object : Observer<T> {
                override fun onComplete() {
                    iBaseView?.dismissLoading()
                }

                override fun onSubscribe(d: Disposable) {
                    iModel?.addDisposable(d)
                    iBaseView?.showLoading(if (msg.isEmpty()) "请求中..." else msg)
                    if (!NetworkUtils.isConnected()) {
                        showToastBottom("连接失败,请检查网络状况!")
                        onComplete()
                    }
                }

                override fun onNext(t: T) {
                    if (t.code == CodeStatus.SUCCESS) {
                        onSuccess.invoke(t)
                    } else if (t.code == CodeStatus.LOGIN_OUT) {//重新登录
//                val currentActivity = ActivityUtils.currentActivity()
//                UserManager.getInstance().clear()
//                EMClient.getInstance().logout(true)
//                showToastBottom("登录过期，请重新登录")
//                val intent = Intent(currentActivity, LoginActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                currentActivity?.startActivity(intent)
                    } else {
                        if (!t.msg.isNullOrEmpty()) {
                            t.msg?.let { showToastBottom(it) }
                        } else {
                            showToastBottom("请求失败")
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    iBaseView?.dismissLoading()
                    if (e is SocketTimeoutException || e is ConnectException) {
                        showToastBottom("连接失败,请检查网络状况!")
                    } else if (e is JsonParseException) {
                        showToastBottom("数据解析失败")
                    } else {
                        showToastBottom("请求失败")
                    }
                }
            })
}

fun <T : BaseBean, P : ITopPresenter> Observable<T>.listSubcribe(
        iBaseView: IListView<P>? = null
        , iModel: IModel? = null
        , isRefresh: Boolean
        , isLoadMore: Boolean
        , onSuccess: (T) -> Unit) {
    this.compose(SchedulerUtils.ioToMain())
            .subscribe(object : Observer<T> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {
                    iModel?.addDisposable(d)
                    if (!isRefresh && !isLoadMore) {
                        iBaseView?.mStateView?.showLoading()
                    }
                }

                override fun onNext(t: T) {
                    if (t.code == CodeStatus.SUCCESS) {
                        iBaseView?.mStateView?.showSuccess()
                        onSuccess.invoke(t)
                    } else if (t.code == CodeStatus.LOGIN_OUT) {//重新登录
//                    UserManager.getInstance().clear()
//                    showToastBottom("登录过期，请重新登录")
//                    EMClient.getInstance().logout(true)
//                    val intent = Intent(currentActivity, LoginActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                    currentActivity.startActivity(intent)
                    } else {
                        iBaseView?.mStateView?.showError()
                    }
                }

                override fun onError(e: Throwable) {
                    if (!isLoadMore) {
                        iBaseView?.mStateView?.showError()
                    } else {
                        iBaseView?.loadMoreFail(isRefresh)
                    }
                }
            })
}