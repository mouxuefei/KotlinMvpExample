package com.exmple.baseprojectmvp.http

import io.reactivex.Observable
import retrofit2.http.POST

/**
 * FileName: com.beijing.zhagen.meiqi.http.api.MainApi.java
 * Author: mouxuefei
 * date: 2018/3/20
 * version: V1.0
 * desc:
 */
interface MainApi {

    /**
     * 主页接口
     */
    @POST("main/init")
    fun getMainData(): Observable<MainDataBean>

}