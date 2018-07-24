package com.exmple.corelib.utils;

import android.content.Context;
import android.content.Intent;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.exmple.corelib.ExtensionsKt.showToastCenter;


/**
 * Created by mou on 2017/8/30.
 */

public class AppExit2Back {


    private static Boolean isExit = false;

    /**
     * 退出App程序应用
     * @return boolean True退出|False提示
     */
    public static boolean exitApp(Context context) {
        ScheduledExecutorService pool = null;
        if (isExit == false) {
            isExit = true;
            //信息提示
           showToastCenter("再按一次退出程序");
            //创建定时器
            pool = Executors.newScheduledThreadPool(1);
            //如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
            pool.schedule(new TimerTask() {
                @Override
                public void run() {
                    //取消退出
                    isExit = false;
                }
            }, 2000, TimeUnit.MILLISECONDS);
        } else {
            ActivityUtils.INSTANCE.popAllActivity();
            //创建ACTION_MAIN
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //启动ACTION_MAIN
            context.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        return isExit;
    }
}
