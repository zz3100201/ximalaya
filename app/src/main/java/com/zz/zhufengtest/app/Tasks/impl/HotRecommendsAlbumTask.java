package com.zz.zhufengtest.app.Tasks.impl;

import com.zz.zhufengtest.app.Constants;
import com.zz.zhufengtest.app.Tasks.BaseTask;
import com.zz.zhufengtest.app.Tasks.TaskCallback;
import com.zz.zhufengtest.app.Tasks.TaskResult;
import com.zz.zhufengtest.app.client.ClientDiscoverAPI;

/**
 * Created by zhangzhao11 on 2015/8/3.
 * User:zz
 * Date:2015/8/3
 */
public class HotRecommendsAlbumTask extends BaseTask {
    public HotRecommendsAlbumTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.taskId = Constants.TASK_DISCOVER_RECOMEND_HOT;
        if (params != null) {
            String AlbumId = params[0];
            if (AlbumId != null) {
                String data = ClientDiscoverAPI.getHotRecommendsAlbum(AlbumId);
            }
        }
        return ret;
    }
}
