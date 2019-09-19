
package com.exmple.baselib.utils

import android.app.Activity
import android.util.Log
import java.util.*

/**
 * @FileName: com.exmple.baselib.utils.ActivityUtils.java
 * @author: mouxuefei
 * @date: 2017-12-21 16:08
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
object ActivityUtils {
    private val TAG = "ActivityUtils"
    private val activityStack: Stack<Activity> = Stack()

    /**
     * <获取当前栈顶Activity>
     */
    fun currentActivity(): Activity? {
        return if (activityStack.size == 0) {
            null
        } else activityStack.lastElement()
    }


    /**
     * <获取当前栈顶Activity>
     * <功能详细描述>
     * @see [类、类.方法、类.成员]
     */
    fun popCurrentActivity() {
        popActivity(currentActivity())
    }


    /**
     * <将Activity入栈>
     * <功能详细描述>
     *
     * @param activity
     * @see [类、类.方法、类.成员]
    </功能详细描述></将Activity入栈> */
    fun pushActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * <退出栈顶Activity>
     * <功能详细描述>
     * @see [类、类.方法、类.成员]
     */
    fun popActivity(activity: Activity?) {
        if (activity != null) {
            activity.finish()
            Log.e(TAG, "remove current activity:" + activity.javaClass.simpleName)
            activityStack.remove(activity)
        }
    }


    /**
     * 将制定activity从栈中移除
     */
    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            activityStack.remove(activity)
        }
    }


    /**
     * <退出栈中所有Activity></退出栈中所有Activity>,当前的activity除外>
     * <功能详细描述>
     *
     * @param cls
     * @see [类、类.方法、类.成员]
    </功能详细描述> */
    fun popAllActivityExceptMain(cls: Class<*>) {
        while (true) {
            val activity = currentActivity() ?: break
            if (activity.javaClass == cls) {
                break
            }
            popActivity(activity)
        }
    }


    fun popAllActivity() {
        while (true) {
            val activity = currentActivity() ?: break
            popActivity(activity)
        }
    }

    /**
     * 如果栈顶是这个 class 就finish掉
     */
    fun popActivity(activityClass: Class<out Activity>) {
        val activity = currentActivity()
        if (activity != null && activity.javaClass.name == activityClass.name) {
            popActivity(activity)
        }
    }

    fun getSize(): Int {
        return activityStack.size
    }
}