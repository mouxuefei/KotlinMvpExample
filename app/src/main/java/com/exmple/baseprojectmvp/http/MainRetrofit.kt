package com.exmple.baseprojectmvp.http

import com.exmple.baselib.http.retrofit.RetrofitFactory
import okhttp3.Request

/**
 * FileName: com.beijing.zhagen.meiqi.http.retrofit.MainRetrofit.java
 * Author: mouxuefei
 * date: 2018/3/20
 * version: V1.0
 * desc:
 */

object MainRetrofit : RetrofitFactory<MainApi>() {
    override fun getBaseUrl()= "http://www.baidu.com"

    override fun getHeader(builder: Request.Builder): Request.Builder {
        builder.addHeader("token","XXXXXXXXXXXXXXXXXXXXX")
        return  builder
    }

    override fun getApiService(): Class<MainApi> {
        return MainApi::class.java
    }

}