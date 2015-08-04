package com.zz.zhufengtest.app.Tasks.impl;

import com.zz.zhufengtest.app.Constants;
import com.zz.zhufengtest.app.Tasks.BaseTask;
import com.zz.zhufengtest.app.Tasks.TaskCallback;
import com.zz.zhufengtest.app.Tasks.TaskResult;
import com.zz.zhufengtest.app.client.ClientDiscoverAPI;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */

/**
 * 获取 发现-》推荐部分的网络数据
 */
public class DiscoverRecomendTask extends BaseTask {
    public DiscoverRecomendTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.taskId = Constants.TASK_DISCOVER_RECOMEND;
        String str = ClientDiscoverAPI.getDiscoverRecomend();
        if (str != null) {
            try {
                ret.data = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
