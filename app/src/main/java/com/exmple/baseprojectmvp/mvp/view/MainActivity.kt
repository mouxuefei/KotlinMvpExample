package com.exmple.baseprojectmvp.mvp.view

import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.adapter.DemoAdapter
import com.exmple.baseprojectmvp.mvp.contract.IMainContact
import com.exmple.baseprojectmvp.mvp.presenter.MainPresenter
import com.exmple.baselib.mvp.BaseMvpListActivity


class MainActivity : BaseMvpListActivity<IMainContact.View, IMainContact.Presenter>(), IMainContact.View {

    override var mPresenter: IMainContact.Presenter = MainPresenter()
    override val setRefreshEnable = true
    override val setRecyclerViewBgColor = R.color.white
    override fun initData() {
        super.initData()
        val data = ArrayList<String>()
        data.add("")
        data.add("")
        data.add("")
        val demoAdapter = DemoAdapter(data = data)
        mRecyclerView.adapter = demoAdapter
        demoAdapter.setOnLoadMoreListener({ demoAdapter.loadMoreEnd() }, mRecyclerView)
    }

    override fun onRetry() {

    }

    override fun onRefresh() {
        mRefreshLayout.finishRefresh(false)
    }

    override fun loadMoreFail(isRefresh: Boolean) {

    }


    override fun getDataSuccess() {

    }
}
