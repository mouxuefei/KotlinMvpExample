package com.sihaiwanlian.baselib.mvp

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import com.exmple.baselib.R
import com.exmple.baselib.mvp.BaseMvpActivity
import com.exmple.baselib.mvp.ITopPresenter
import com.exmple.baselib.mvp.ITopView


/**
 * 所有标题的activity的父类
 * 在这里主要统一处理标题
 * Created by mou on 2016/7/13.
 */
abstract class BaseMvpTitleActivity<V : ITopView, P : ITopPresenter> : BaseMvpActivity<V, P>() {
    private var rightMenuTexts: String? = null
    private var rightMenuIcons: Int? = null
    private var titleTv: TextView? = null
    @LayoutRes
    protected abstract fun childView(): Int

    override fun getContentView() = R.layout.activtiy_base_title
    override fun initView() {
        val container = this.findViewById<FrameLayout>(R.id.base_container)
        container.addView(layoutInflater.inflate(childView(), null))
        val toolbar = this.findViewById<Toolbar>(R.id.base_toolbar)
        titleTv = this.findViewById(R.id.base_title_tv)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        if (hasBackIcon()) {
            toolbar.setNavigationIcon(R.drawable.return_icon)
            toolbar.setNavigationOnClickListener { finish() }
        }
    }

    open fun hasBackIcon() = true
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        rightMenuIcons?.let {
            val item = menu.add(0, 0, 0, "")
            item.icon = ContextCompat.getDrawable(this, it)
            item.setShowAsAction(Menu.FLAG_ALWAYS_PERFORM_CLOSE)
        }
        rightMenuTexts?.let {
            val item = menu.add(0, 0, 0, "")
            item.title = it
            item.setShowAsAction(Menu.FLAG_ALWAYS_PERFORM_CLOSE)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onRightMenuClick(item.itemId)
        return false
    }

    /**
     * 设置toolbar右边的文字
     */
    fun setRightMenuTexts(rightMenuText: String) {
        this.rightMenuTexts = rightMenuText
    }

    /**
     * 设置toolbar右边的icon
     */
    fun setRightMenuIcons(@DrawableRes rightIconResId: Int) {
        this.rightMenuIcons = rightIconResId
    }

    /**
     * 当toolbar右边的icon，被点击，数据0,1,2,3
     */
    open fun onRightMenuClick(itemId: Int) {
    }

    /**
     * 设置中间的title
     */
    protected fun setActivityTitle(@StringRes strResId: Int) {
        titleTv?.setText(strResId)
    }

    protected fun setActivityTitle(text: String) {
        titleTv?.text = text
    }

    /**
     * 设置中间title的颜色
     */
    fun setActivityTitleColor(@ColorRes colorId: Int) {
        titleTv?.setTextColor(resources.getColor(colorId))
    }
}
