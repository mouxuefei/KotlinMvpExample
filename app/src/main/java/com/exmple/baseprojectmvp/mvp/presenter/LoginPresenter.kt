package com.exmple.baseprojectmvp.mvp.presenter

import com.exmple.baseprojectmvp.mvp.contract.ILoginContract
import com.exmple.baseprojectmvp.mvp.model.LoginModel
import com.exmple.corelib.mvp.BasePresenterKt

/**
 * Description :
 * @author  mouxuefei
 * @date 2018/7/6  14:10
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class LoginPresenter : BasePresenterKt<ILoginContract.View>(), ILoginContract.Presenter {
    override var mModel: ILoginContract.Model? =LoginModel()

}

