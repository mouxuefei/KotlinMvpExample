package com.sihaiwanlian.baselib.mvp

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.exmple.corelib.R
import com.exmple.corelib.mvp.IListView
import com.exmple.corelib.mvp.ITopPresenter
import com.exmple.corelib.mvp.ITopView
import com.exmple.corelib.state.IStateView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.layout_list.*

/**
 * @FileName: {NAME}.java
 * @author: villa_mou
 * @date: {MONTH}-{HOUR}:03
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
abstract class BaseMvpTitleListActivity<V : ITopView, P : ITopPresenter> : BaseMvpTitleActivity<V, P>(), IListView<P> {
    override fun childView()= R.layout.layout_list
    override val mStateView: IStateView by lazy { list_sv }
    override val mRecyclerView: RecyclerView by lazy { list_rv }
    override val mRefreshLayout: SmartRefreshLayout by lazy { refreshLayout }
    override fun initView() {
        super.initView()
        //设置背景色
        list_rv.setBackgroundColor(ContextCompat.getColor(this, setRecyclerViewBgColor))
        //重试
        list_sv.onRetry = { onRetry() }
        //刷新
        refreshLayout.setOnRefreshListener { onRefresh() }
        //设置下拉刷新是否可用
        refreshLayout.isEnabled = setRefreshEnable
    }
    abstract fun onRefresh()
    abstract fun onRetry()
    open val setRecyclerViewBgColor = R.color.white
    open val setRefreshEnable = true
}