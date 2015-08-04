package com.zz.zhufengtest.app.util;

/**
 * Created by zhangzhao11 on 2015/7/29.
 * User:zz
 * Date:2015/7/29
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 软件包工具类，获取版本号信息
 */
public final class PackageUtil {
    private PackageUtil(){

    }

    /**
     * 获取应用程序版本号
     * @return
     */
    public static String getPackageVersionName(Context context){
        String ret = "1.0";
        if (context != null){
            PackageManager packageManager
                    = context.getPackageManager();
            //参数1：当前软件包package信息
            //      通过Context.getPackageName()获取
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(
                        context.getPackageName(),
                        PackageManager.GET_ACTIVITIES
                );
                ret = packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
