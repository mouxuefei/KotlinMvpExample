package com.exmple.corelib.mvp

import android.os.Bundle
import com.exmple.corelib.base.BaseActivity
import com.exmple.corelib.showToastBottom

/**
 * @FileName: BaseMvpActivity.java
 * @author: villa_mou
 * @date: 06-15:48
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
abstract class BaseMvpActivity<V : ITopView, P : ITopPresenter> : BaseActivity(), IView<P> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inited()
    }
    override fun getCtx() = this
    override fun showLoading(msg: String) {
        progressDialog?.showProgressDialogWithText(msg)
    }

    override fun finish(resultCode: Int) {
        finish()
    }

    override fun showLoading(srtResId: Int) {
        progressDialog?.showProgressDialogWithText(resources.getString(srtResId))
    }

    override fun dismissLoading() {
        progressDialog?.dismissProgressDialog()
    }

    override fun showToast(message: String) {
        showToastBottom(message)
    }

    override fun showToast(srtResId: Int) {
        showToast(resources.getString(srtResId))
    }
}