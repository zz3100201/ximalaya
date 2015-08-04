package com.zz.zhufengtest.app.Tasks.impl;

import android.util.Log;
import com.zz.zhufengtest.app.Constants;
import com.zz.zhufengtest.app.Tasks.BaseTask;
import com.zz.zhufengtest.app.Tasks.TaskCallback;
import com.zz.zhufengtest.app.Tasks.TaskResult;
import com.zz.zhufengtest.app.client.ClientDiscoverAPI;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao11 on 2015/7/29.
 * User:zz
 * Date:2015/7/29
 */

/**
 * 发现部分 分类 获取任务
 */
public class DiscoverCategoryTask extends BaseTask{
    public DiscoverCategoryTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.taskId = Constants.TASK_DISCOVER_CATEGORIES;
        //调API
        String str = ClientDiscoverAPI.getDiscoverCategories();
        try {
            ret.data = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
