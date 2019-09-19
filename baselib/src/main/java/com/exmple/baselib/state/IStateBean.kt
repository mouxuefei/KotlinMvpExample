package com.exmple.baselib.state

interface IStateBean<out T, out DATA : IListBean<T>> {
    val code: Int
    val result: DATA?
    fun isOk(): Boolean = 1 == code
}