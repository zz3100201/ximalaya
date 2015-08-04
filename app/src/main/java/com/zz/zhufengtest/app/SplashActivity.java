package com.zz.zhufengtest.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import com.zz.zhufengtest.app.Tasks.TaskCallback;
import com.zz.zhufengtest.app.Tasks.TaskResult;
import com.zz.zhufengtest.app.Tasks.impl.CategoryTagMenuTask;
import com.zz.zhufengtest.app.model.CategoryTagMenu;
import com.zz.zhufengtest.app.parsers.DataParser;
import com.zz.zhufengtest.app.util.MyLog;
import com.zz.zhufengtest.app.util.PackageUtil;
import com.zz.zhufengtest.app.util.UncaughtExceptionHandlerImpl;
import org.json.JSONObject;

import java.util.List;

/**
 * 启动扉页，程序入口点
 */
public class SplashActivity extends FragmentActivity implements TaskCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        //为当前线程，设置未捕获异常的处理器
        //这个处理器就是用来保存未捕获异常的信息
        UncaughtExceptionHandlerImpl handler =
                new UncaughtExceptionHandlerImpl(getApplicationContext());
        Thread.setDefaultUncaughtExceptionHandler(handler);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //启动扉页，进行网络检查与网络请求，下载数据
        //最终显示主界面
//        int a = 3;
//        int b = 0;
//        int i = a/b;
//        MyLog.d("Splash","3/0="+i);
        CategoryTagMenuTask task = new CategoryTagMenuTask(this);
        task.execute();

    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if(result != null){
            int taskId = result.taskId;
            Object data = result.data;
            if (taskId == Constants.TASK_CATEGORY_TAG_MENU){
                //TODO 获取category_tag_menu的数据
                if (data != null) {
                    if (data instanceof JSONObject){
                        JSONObject json = (JSONObject)data;
                        List<CategoryTagMenu> categoryTagMenuList =
                                DataParser.parseCategoryTagMenuResult(json);

                    }
                }
                //TODO 处理之后，判断教程的启动
                SharedPreferences sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
                //获取上一次的版本号
                String lastVer = sp.getString(Constants.SP_KEY_GUIDE_LAST_SHOW_VER, null);
                String versionName = PackageUtil.getPackageVersionName(this);
                Intent intent = null;
                if (!versionName.equals(lastVer)){
                    //TODO 显示教程
                    intent = new Intent(this,GuideActivity.class);
                }else{
                    //TODO 显示主界面
                    intent = new Intent(this,MainActivity.class);
                }
                startActivity(intent);
                finish();
                //使用API 11的CLEAR_TASK可以实现清空任务栈
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
        }
    }

}
