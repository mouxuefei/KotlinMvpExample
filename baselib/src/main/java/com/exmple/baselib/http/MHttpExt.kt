package com.exmple.baselib.http

import android.widget.Toast
import com.exmple.baselib.base.LibApplication
import com.exmple.baselib.http.constant.CodeStatus
import com.exmple.baselib.http.entity.BaseBean
import com.exmple.baselib.mvp.IModel
import com.exmple.baselib.mvp.ITopView
import com.exmple.baselib.scheduler.SchedulerUtils
import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.NotNull
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.mSubscribe(
        iBaseView: ITopView? = null
        , iModel: IModel? = null
        , msg: String = ""
        , onSuccess: (T) -> Unit) {
    this.compose(SchedulerUtils.ioToMain())
            .subscribe(object : Observer<T> {
                override fun onComplete() {
                    iBaseView?.dismissLoading()
                }

                override fun onSubscribe(d: Disposable) {
                    iModel?.mDisposablePool?.add(d)
                    iBaseView?.showLoading(if (msg.isEmpty()) "请求中..." else msg)

                }

                override fun onNext(t: T) {
                    val bean = t as BaseBean<Any>
                    if (bean.errorCode == CodeStatus.SUCCESS) {
                        onSuccess.invoke(t)
                    } else if (bean.errorCode == CodeStatus.LOGIN_OUT) {//重新登录
//                val currentActivity = MActivityUtils.currentActivity()
//                UserManager.getInstance().clear()
//                EMClient.getInstance().logout(true)
//                showToastBottom("登录过期，请重新登录")
//                val intent = Intent(currentActivity, LoginActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                currentActivity?.startActivity(intent)
                    } else {
                        if (!t.errorMsg.isEmpty()) {
                            t.errorMsg.let { toast(it) }
                        } else {
                            toast("请求失败")
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.toString())
                    iBaseView?.dismissLoading()
                    if (e is SocketTimeoutException || e is ConnectException || e is UnknownHostException) {
                        toast("连接失败,请检查网络状况!")
                    } else if (e is JsonParseException) {
                        toast("数据解析失败")
                    } else {
                        toast("未知错误")
                    }
                }
            })
}


/**
 * 统一处理方法,弹窗,内存泄漏,线程调度
 */
fun <T> Observable<T>.bindDialogAndDisposable(view: ITopView?=null, iModel: IModel? = null, msg:String?=null): Observable<T>
        =this.async().bindDialog(view,msg).bindDisposable(iModel)

/**
 * 线程切换,回调回主线程
 */
fun <T> Observable<T>.async(withDelay: Long = 0): Observable<T> =
        this.subscribeOn(Schedulers.io())
                .delay(withDelay, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
/**
 * 线程切换,回调回子线程
 */
fun <T> Observable<T>.asyncIo(withDelay: Long = 0): Observable<T> =
        this.subscribeOn(Schedulers.io())
                .delay(withDelay, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())

/**
 * 绑定弹窗
 */
 fun <T> Observable<T>.bindDialog(view: ITopView?=null,msg:String?=null): Observable<T> =
        this.doOnSubscribe {
            view?.showLoading(msg?:"加载中...")
        }.doOnComplete {
            view?.dismissLoading()
        }
/**
 * 绑定内存泄漏
 */
 fun <T> Observable<T>.bindDisposable( iModel: IModel? = null): Observable<T> =
        this.doOnSubscribe {
            iModel?.mDisposablePool?.add(it)
        }
/**
 * 处理结果
 */
fun <T> Observable<T>.onResult(subErr: ((Throwable) -> Unit)? = null, success: ((T) -> Unit)? = null)=
    this.subscribe({
        val bean = it as BaseBean<Any>
        when {
            bean.errorCode == CodeStatus.SUCCESS -> success?.invoke(it)
            bean.errorCode == CodeStatus.LOGIN_OUT -> {//重新登录
            }
            else -> {
                toast( if(it.errorMsg.isNotEmpty()) it.errorMsg else "请求失败")
            }
        }
        success?.invoke(it)
    }, {
        toast("请求失败")
        subErr?.invoke(it)
    })
fun toast(@NotNull msg: String) {
    val sToast = Toast.makeText(LibApplication.mContext, msg, Toast.LENGTH_SHORT)
    sToast.show()
}
