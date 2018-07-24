package com.exmple.corelib.base

import android.app.Application
import android.content.Context
import android.os.Handler
import com.exmple.corelib.utils.LogCatStrategy
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

/**
 * FileName: com.sihaiwanlian.baselib.base.LibApplication.java
 * Author: mouxuefei
 * date: 2018/3/5
 * version: V1.0
 * desc:
 */
open class LibApplication : Application() {
    private var refWatcher: RefWatcher? = null
    companion object {
        var mContext: Context by Delegates.notNull()
            private set
        fun getRefWatcher(context: Context?): RefWatcher? {
            val myApplication = context?.applicationContext as LibApplication
            return myApplication.refWatcher
        }
        var appHandler: Handler?=null
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        //LeakCanary初始化
        refWatcher = setupLeakCanary()
        //初始化生命周期
        appHandler= Handler()
        initLogger()
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .logStrategy(LogCatStrategy())
                .showThreadInfo(false)   // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("villa")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return true
            }
        })
    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

}