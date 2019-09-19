package com.exmple.baselib.utils

import android.util.Log

import com.orhanobut.logger.LogStrategy

/**
 * 解决 as3.0之后logcat显示问题
 */
class LogCatStrategy : LogStrategy {

    private var last: Int = 0
    override fun log(priority: Int, tag: String?, message: String) {
        Log.println(priority, randomKey() + tag!!, message)
    }

    private fun randomKey(): String {
        var random = (10 * Math.random()).toInt()
        if (random == last) {
            random = (random + 1) % 10
        }
        last = random
        return random.toString()
    }
}