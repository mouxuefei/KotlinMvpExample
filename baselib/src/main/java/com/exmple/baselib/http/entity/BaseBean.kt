package com.exmple.baselib.http.entity

/**
 * @version V1.0 <描述当前版本功能>
 * @FileName: BaseBean.java
 * @author: villa_mou
 * @date: 05-19:24
 * @desc
</描述当前版本功能> */
data class BaseBean<T> constructor(var data: T, var errorCode: Int, var errorMsg: String)

data class ListBean<T>(
val curPage: Int,
val datas: List<T>,
val offset: Int,
val over: Boolean,
val pageCount: Int,
val size: Int,
val total: Int
)
