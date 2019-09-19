package com.exmple.baselib.base

import android.app.Application
import android.content.Context
import android.os.Handler
import com.exmple.baselib.utils.LogCatStrategy
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import kotlin.properties.Delegates

/**
 * FileName: com.sihaiwanlian.baselib.base.LibApplication.java
 * Author: mouxuefei
 * date: 2018/3/5
 * version: V1.0
 * desc:
 */
open class LibApplication : Application() {
    companion object {
        var mContext: Context by Delegates.notNull()
            private set
        var appHandler: Handler?=null
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        //初始化生命周期
        appHandler = Handler()
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
}