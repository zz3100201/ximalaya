package com.zz.zhufengtest.app;

import android.app.Application;
import com.zz.zhufengtest.app.cache.FileCache;

/**
 * Created by zhangzhao11 on 2015/7/31.
 * User:zz
 * Date:2015/7/31
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileCache.createInstance(getApplicationContext());
    }
}
