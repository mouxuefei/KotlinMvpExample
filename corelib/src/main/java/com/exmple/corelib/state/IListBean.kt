package com.exmple.corelib.state

interface IListBean<out T> {
    val list: List<T>
}