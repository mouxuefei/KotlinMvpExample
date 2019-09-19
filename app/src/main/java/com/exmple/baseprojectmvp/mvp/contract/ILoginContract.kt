package com.exmple.baseprojectmvp.mvp.contract

import com.exmple.baselib.mvp.IModel
import com.exmple.baselib.mvp.IPresenter
import com.exmple.baselib.mvp.IView

/**
 * Description :
 * @author  mouxuefei
 * @date 2018/7/6  14:10
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface ILoginContract {
    interface View : IView<Presenter> {}
    interface Presenter : IPresenter<View, Model> {}
    interface Model : IModel {}
}
