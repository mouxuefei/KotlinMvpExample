package com.exmple.baseprojectmvp.mvp.contract

import com.exmple.baseprojectmvp.http.MainDataBean
import com.exmple.corelib.mvp.IListView
import com.exmple.corelib.mvp.IModel
import com.exmple.corelib.mvp.IPresenter
import io.reactivex.Observable

/**
 * @FileName: IMainContact.java
 * @author: villa_mou
 * @date: 06-16:34
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
interface IMainContact {
    interface View : IListView<Presenter> {
        fun getDataSuccess()
    }

    interface Presenter : IPresenter<View, Model> {
        fun getDataByNet()
    }

    interface Model : IModel {
        fun getMainData(): Observable<MainDataBean>
    }
}