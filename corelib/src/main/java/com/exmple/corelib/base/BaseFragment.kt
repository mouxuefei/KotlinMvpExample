package com.exmple.corelib.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exmple.corelib.R
import com.exmple.corelib.utils.ProgressDialogUtils
import com.exmple.corelib.register
import com.exmple.corelib.unregister
import com.squareup.leakcanary.RefWatcher

/**
 * @FileName: com.mou.demo.basekotlin.BaseFragment.java
 * @author: mouxuefei
 * @date: 2017-12-19 15:48
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
abstract class BaseFragment : Fragment() {
    private var isViewPrepare = false
    private var hasLoadData = false
    open var mContext: Context? = null
    private var progressDialog: ProgressDialogUtils? = null
    private var refWatcher: RefWatcher? = null
    protected abstract fun lazyLoad()
    open val useEventBus: Boolean = false
    @LayoutRes
    protected abstract fun getContentView(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = getContentView()
        val rootView = inflater.inflate(layout, container, false)
        this.mContext = context
        if (useEventBus) {
            register(this)
        }
        refWatcher = LibApplication.getRefWatcher(context)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        initView(view)
        progressDialog = ProgressDialogUtils(context, R.style.dialog_transparent_style)
        savedStanceState(savedInstanceState)
        initData()
        lazyLoadDataIfPrepared()
    }

    protected abstract fun initView(view: View)
    open fun savedStanceState(savedInstanceState: Bundle?) {}
    open fun initData() {}
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    fun showProgressDialog(text: String) {
        progressDialog?.showProgressDialogWithText(text)
    }

    fun dismissProgressDialog() {
        progressDialog?.dismissProgressDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus) {
            unregister(this)
        }
        refWatcher?.watch(this)
    }
}

