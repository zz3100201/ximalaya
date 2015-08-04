package com.zz.zhufengtest.app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao11 on 2015/7/29.
 * User:zz
 * Date:2015/7/29
 */
public class DiscoverTab {
    //显示在DiscoverFragment上面的Tab标题
    private String title;
    /**
     * 内容的描述，代码判断的时候用的
     */
    private String contentType;
    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null){
            title = json.getString("title");
            contentType = json.getString("contentType");
        }
    }

    public String getContentType() {
        return contentType;
    }

    public String getTitle() {
        return title;
    }
}
