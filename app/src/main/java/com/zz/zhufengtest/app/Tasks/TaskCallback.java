package com.zz.zhufengtest.app.Tasks;

/**
 * Created by zhangzhao11 on 2015/7/28.
 * User:zz
 * Date:2015/7/28
 */

/**
 * TaskCallback异步任务执行成功之后，由onPostExcute来回调
 */
public interface TaskCallback {
    /**
     * 当异步任务执行成功，进行回调
     * @param result
     */
    void onTaskFinished(TaskResult result);
}
