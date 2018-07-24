package com.exmple.baseprojectmvp.mvp.model

import com.exmple.baseprojectmvp.http.MainDataBean
import com.exmple.baseprojectmvp.http.MainRetrofit
import com.exmple.baseprojectmvp.mvp.contract.IMainContact
import com.exmple.corelib.mvp.BaseModelKt
import io.reactivex.Observable

/**
 * @FileName: MainModel.java
 * @author: villa_mou
 * @date: 07-16:19
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class MainModel : BaseModelKt(), IMainContact.Model {
    override fun getMainData(): Observable<MainDataBean> {
        return MainRetrofit.apiService.getMainData()
    }
}