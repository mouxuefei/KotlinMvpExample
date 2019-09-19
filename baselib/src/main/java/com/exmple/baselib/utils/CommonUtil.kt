package com.exmple.baselib.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.StyleSpan
import android.view.*
import android.widget.FrameLayout
import android.widget.GridView
import android.widget.ListView
import com.exmple.baselib.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.regex.Pattern

/**
 * Created by mou on 2016/7/6.
 */
object CommonUtil {


    /**
     * 思路是先将statusbar设置成半透明，然后在contentView中添加一个和statusbar一样的高度的view作为statusbar
     * 的背景颜色
     *
     * @param activity
     */
    fun customStatusbar(activity: Activity) {
        val window = activity.window
        val mContentView = activity.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup

        //First translucent status bar.
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val statusBarHeight = getStatusBarHeight(activity)

        val mChildView = mContentView.getChildAt(0)
        if (mChildView != null) {
            val lp = mChildView.layoutParams as FrameLayout.LayoutParams
            //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
            if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                //不预留系统空间
                ViewCompat.setFitsSystemWindows(mChildView, false)
                lp.topMargin += statusBarHeight
                mChildView.layoutParams = lp
            }
        }
        // 已经添加过假背景view时，不用再次添加，只需要设置颜色即ok

        var statusBarView: View? = mContentView.getChildAt(0)
        if (statusBarView != null && statusBarView.layoutParams != null && statusBarView.layoutParams.height == statusBarHeight) {
            //避免重复调用时多次添加 View
            statusBarView.setBackgroundColor(activity.resources.getColor(R.color.white))
            //            statusBarView.setBackgroundResource(R.mipmap.ic_launcher);
            return
        }
        statusBarView = View(activity)
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight)
        statusBarView.setBackgroundColor(activity.resources.getColor(R.color.white))
        //        statusBarView.setBackgroundResource(R.mipmap.ic_launcher);
        //向 ContentView 中添加假 View
        mContentView.addView(statusBarView, 0, lp)
    }


    fun fullScreen(activity: Activity) {
        val window = activity.window
        val mContentView = activity.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup

        //首先使 ChildView 不预留空间
        var mChildView: View? = mContentView.getChildAt(0)
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false)
        }

        val statusBarHeight = getStatusBarHeight(activity)
        //需要设置这个 flag 才能设置状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //避免多次调用该方法时,多次移除了 View
        if (mChildView != null && mChildView.layoutParams != null && mChildView.layoutParams.height == statusBarHeight) {
            //移除假的 View.
            mContentView.removeView(mChildView)
            mChildView = mContentView.getChildAt(0)
        }
        if (mChildView != null) {
            val lp = mChildView.layoutParams as FrameLayout.LayoutParams
            //清除 ChildView 的 marginTop 属性
            if (lp != null && lp.topMargin >= statusBarHeight) {
                //                lp.topMargin -= statusBarHeight;
                mChildView.layoutParams = lp
            }
        }
    }

    fun getStatusBarHeight(activity: Activity): Int {
        val resId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resId > 0) {
            activity.resources.getDimensionPixelSize(resId)
        } else 0
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    fun getNavigationBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 判断手机是否有导航栏
     *
     * @param context
     * @return
     */
    fun hasNavigationBar(context: Context): Boolean {
        //是否在屏幕外有菜单键
        val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
        //屏幕外是否有返回键
        val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
        return !hasBackKey && !hasMenuKey

    }

    fun startActivtiy(context: Context, cls: Class<*>) {
        val intent = Intent(context, cls)
        context.startActivity(intent)
    }

    fun startActivtiy(context: Context, cls: Class<*>, bundle: Bundle?) {
        if (bundle == null) {
            startActivtiy(context, cls)
            return
        }
        val intent = Intent(context, cls)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }

    fun startActivtiyForResult(context: Context, cls: Class<*>, requestCode: Int) {
        val intent = Intent(context, cls)
        if (context is Activity) {
            context.startActivityForResult(intent, requestCode)
        } else {
            throw IllegalThreadStateException()
        }
    }

    fun startActivtiyForResult(context: Context, cls: Class<*>, requestCode: Int, bundle: Bundle?) {
        if (bundle == null) {
            startActivtiyForResult(context, cls, requestCode)
            return
        }
        val intent = Intent(context, cls)
        intent.putExtras(bundle)
        if (context is Activity) {
            context.startActivityForResult(intent, requestCode)
        } else {
            throw IllegalThreadStateException()
        }
    }

    /**
     * 判断是否是电话号码
     *
     * @param phoneNum
     * @return
     */
    fun isPhoneNum(phoneNum: String): Boolean {
        return if (TextUtils.isEmpty(phoneNum) || phoneNum.length != 11) {
            false
        } else phoneNum.matches("[1][34578][0-9]{9}".toRegex())
    }


    /**
     * Methods: setListViewHeight Description: 动态获取listview的高度
     *
     * @param listView void
     * @throws null
     */

    fun setListViewHeight(listView: ListView) {
        val listAdapter = listView.adapter ?: return
        var totalHeight = 0
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            totalHeight += listItem.measuredHeight
        }
        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
        listView.layoutParams = params
    }

    /**
     * 动态设置gridView的高度
     *
     * @param gridView
     * @param columns  列数
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    fun setGridViewHeight(gridView: GridView, columns: Int) {
        val adapter = gridView.adapter
        val count = adapter.count
        var row = count / columns
        row = if (count % columns == 0) row else row + 1
        var totalHeight = 0
        for (i in 0 until row) {
            val view = adapter.getView(i, null, gridView)
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            totalHeight += view.measuredHeight
        }
        val layoutParams = gridView.layoutParams
        layoutParams.height = totalHeight + gridView.verticalSpacing * (row - 1)
        gridView.layoutParams = layoutParams
    }


    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = wm.defaultDisplay
        return defaultDisplay.width
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = wm.defaultDisplay
        return defaultDisplay.width
    }


    fun getMatchTitleHeight(context: Context): Int {
        val d = context.resources.displayMetrics.density.toInt()
        val screenHeight = getScreenHeight(context)
        var myheight = 0
        when (screenHeight) {
            720 -> myheight = 146
            1080 -> myheight = 300
            1440 -> myheight = 500
            else -> myheight = 146
        }
        return myheight
    }


    /**
     * 从身份证上号码中获取生日
     *
     * @param id
     * @return
     */
    fun getBirthdayFromID(id: String): Long {
        var birthday: Long = 0
        if (TextUtils.isEmpty(id) || id.length < 18) {
            birthday = 0
        }
        val substring = id.substring(6, 15)
        val sdf = SimpleDateFormat("yyyyMMdd")
        try {
            val date = sdf.parse(substring)
            birthday = date.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return birthday
    }


    /**
     * 设置状态栏黑色字体图标，
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @param activity
     * @return 1:MIUUI 2:Flyme 3:android6.0
     */
    fun StatusBarLightMode(activity: Activity): Int {
        var result = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (MIUISetStatusBarLightMode(activity.window, true)) {
                result = 1
            } else if (FlymeSetStatusBarLightMode(activity.window, true)) {
                result = 2
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                result = 3
            }
        }
        return result
    }

    /**
     * 已知系统类型时，设置状态栏黑色字体图标。
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @param activity
     * @param type     1:MIUUI 2:Flyme 3:android6.0
     */
    fun StatusBarLightMode(activity: Activity, type: Int) {
        if (type == 1) {
            MIUISetStatusBarLightMode(activity.window, true)
        } else if (type == 2) {
            FlymeSetStatusBarLightMode(activity.window, true)
        } else if (type == 3) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

    }

    /**
     * 清除MIUI或flyme或6.0以上版本状态栏黑色字体
     */
    fun StatusBarDarkMode(activity: Activity, type: Int) {
        if (type == 1) {
            MIUISetStatusBarLightMode(activity.window, false)
        } else if (type == 2) {
            FlymeSetStatusBarLightMode(activity.window, false)
        } else if (type == 3) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }

    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    fun FlymeSetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
        var result = false
        if (window != null) {
            try {
                val lp = window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java
                        .getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                if (dark) {
                    value = value or bit
                } else {
                    value = value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                window.attributes = lp
                result = true
            } catch (e: Exception) {

            }

        }
        return result
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    fun MIUISetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
        var result = false
        if (window != null) {
            val clazz = window.javaClass
            try {
                var darkModeFlag = 0
                val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                darkModeFlag = field.getInt(layoutParams)
                val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag)//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag)//清除黑色字体
                }
                result = true
            } catch (e: Exception) {

            }

        }
        return result
    }


    /**
     * 检查设备是否存在SDCard的工具方法
     */
    fun hasSdcard(): Boolean {
        val state = Environment.getExternalStorageState()
        return state == Environment.MEDIA_MOUNTED
    }

    /**
     * make true current connect service is wifi
     *
     * @param mContext
     * @return
     */
    fun isWifi(mContext: Context): Boolean {
        val connectivityManager = mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return if (activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI) {
            true
        } else false
    }


    /**
     * 正则表达式校验邮箱
     *
     * @param emaile 待匹配的邮箱
     * @return 匹配成功返回true 否则返回false;
     */
    fun checkEmaile(emaile: String): Boolean {
        val RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"
        //正则表达式的模式
        val p = Pattern.compile(RULE_EMAIL)
        //正则表达式的匹配器
        val m = p.matcher(emaile)
        //进行正则匹配
        return m.matches()
    }


    /**
     * 把年月日数字变粗体
     */
    fun formatYearAndMonthAndDay2Bold(num: String): SpannableString {
        val spanString = SpannableString(num)
        val span = StyleSpan(Typeface.BOLD)
        val span2 = StyleSpan(Typeface.BOLD)
        val span3 = StyleSpan(Typeface.BOLD)
        spanString.setSpan(span, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanString.setSpan(span2, 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanString.setSpan(span3, 8, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spanString
    }

}
