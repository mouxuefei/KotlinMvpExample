package com.exmple.baselib.state

interface IListBean<out T> {
    val list: List<T>
}