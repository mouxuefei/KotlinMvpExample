package com.exmple.corelib.utils

import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.exmple.corelib.R

class CustomLoadMoreView : LoadMoreView() {

    override fun getLayoutId(): Int {
        return R.layout.diy_load_more
    }

    /**
     * If you return_icon2 to true, the data will be loaded more after all the data is loaded.
     * If you return_icon2 to false, the data will be displayed after all the getLoadEndViewId () layout
     */
    override fun isLoadEndGone(): Boolean {
        return true
    }

    override fun getLoadingViewId(): Int {
        return R.id.load_more_loading_view
    }

    override fun getLoadFailViewId(): Int {
        return R.id.load_more_load_fail_view
    }

    /**
     * IsLoadEndGone () for true, you can return_icon2 0
     * IsLoadEndGone () for false, can not return_icon2 0
     */
    override fun getLoadEndViewId(): Int {
        return R.id.load_more_load_end_view
    }
}