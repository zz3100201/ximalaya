package com.zz.zhufengtest.app.Tasks.impl;

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
public class DiscoverTagTask extends BaseTask{

    public DiscoverTagTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.taskId = Constants.TASK_CATEGORY_TAG_TABS;
        String str = ClientDiscoverAPI.getDiscoverTabs();
        if (str != null) {
            try {
                //返回JSON是为了让接受数据的接口实现，来检查数据的情况，
                //不直接返回数据的实体
                ret.data = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
