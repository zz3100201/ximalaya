package com.zz.zhufengtest.app.Tasks;

import android.os.AsyncTask;

/**
 * Created by zhangzhao11 on 2015/7/28.
 * User:zz
 * Date:2015/7/28
 */

/**
 * 抽象的异步任务
 */
public abstract class BaseTask extends AsyncTask<String,Integer,TaskResult>{
    private TaskCallback callback;

    public BaseTask(TaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
        if (callback != null) {
            callback.onTaskFinished(taskResult);
        }
    }
}
