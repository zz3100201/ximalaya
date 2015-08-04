package com.zz.zhufengtest.app.Tasks.impl;

import com.zz.zhufengtest.app.Constants;
import com.zz.zhufengtest.app.Tasks.BaseTask;
import com.zz.zhufengtest.app.Tasks.TaskCallback;
import com.zz.zhufengtest.app.Tasks.TaskResult;
import com.zz.zhufengtest.app.client.ClientDiscoverAPI;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao11 on 2015/7/28.
 * User:zz
 * Date:2015/7/28
 */

/**
 * 这个任务支持一个参数，参数的内容是type的值，目前只可以写user
 */
public class CategoryTagMenuTask extends BaseTask{

    public CategoryTagMenuTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        //TaskResult 必须创建，用来描述任务类型以及数据
        TaskResult ret = new TaskResult();
        ret.taskId = Constants.TASK_CATEGORY_TAG_MENU;
        ////////////////////
        String type = null;
        if(params != null&& params.length != 0){
            type = params[0];
        }
        String str = ClientDiscoverAPI.getCategoryTagMenu(type);
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
