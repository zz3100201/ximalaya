package com.zz.zhufengtest.app.util;

/**
 * Created by zhangzhao11 on 2015/7/31.
 * User:zz
 * Date:2015/7/31
 */


import android.util.Log;
import com.zz.zhufengtest.app.BuildConfig;

/**
 * 封装Android Log工具，能够增加日志的开关
 */
public class MyLog {
    /**
     * 日志的开关,在Release（发布软件包）的时候，关闭日志
     */
    private static final boolean ON = BuildConfig.DEBUG;
    /**
     * 通过变量ture、false进行日志输出控制
     */
    private static final boolean DEBUG = true;
    private static final boolean INFO = true;
    private MyLog(){

    }
    public static void d(String tag,String msg){
        if (ON){
            if (DEBUG){
                Log.d(tag,msg);
            }
        }

    }
    public static void i(String tag,String msg){
        if (ON){
            if (INFO){
                Log.i(tag,msg);
            }
        }
    }
}
