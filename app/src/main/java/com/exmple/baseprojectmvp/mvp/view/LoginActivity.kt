package com.exmple.baseprojectmvp.mvp.view

import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.contract.ILoginContract
import com.exmple.baseprojectmvp.mvp.presenter.LoginPresenter
import com.exmple.baselib.utils.CommonUtil
import com.sihaiwanlian.baselib.mvp.BaseMvpTitleActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Description :
 * @author  mouxuefei
 * @date 2018/7/6  14:10
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class LoginActivity : BaseMvpTitleActivity<ILoginContract.View, ILoginContract.Presenter>(), ILoginContract.View {
    override fun childView() = R.layout.activity_login
    override var mPresenter: ILoginContract.Presenter = LoginPresenter()
    override fun initData() {
        setActivityTitle(R.string.app_name)
        initListner()
    }

    private fun initListner() {
        button.setOnClickListener {
            CommonUtil.startActivtiy(this, MainActivity::class.java)
        }
    }
}

