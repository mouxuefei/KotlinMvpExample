package com.exmple.baselib.utils

import org.greenrobot.eventbus.EventBus
import org.jetbrains.annotations.NotNull

/**
 * @FileName: EventBusUtils.java
 * @author: villa_mou
 * @date: 08-10:15
 * @version V1.0 <描述当前版本功能>
 * @desc
 */

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
