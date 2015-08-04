package com.zz.zhufengtest.app.model;

/**
 * Created by zhangzhao11 on 2015/7/29.
 * User:zz
 * Date:2015/7/29
 */

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 发现的大分类
 */
public class DiscoverCategory implements Comparable<DiscoverCategory>  {
    /**
     *
     */
    private int id;
    private String name;
    private String title;
    private boolean checked;
    private int orderNum;
    private String coverPath;
    private boolean selectedSwitch;
    private boolean finished;
    private String contentType;

    /**
     *  "id": 2,
     "name": "music",
     "title": "音乐",
     "isChecked": false,
     "orderNum": 2,
     "coverPath": "http://fdfs.xmcdn.com/group12/M08/17/A0/wKgDXFVxM-LyPrvZAAAGGBNsGas270.png",
     "selectedSwitch": false,
     "isFinished": false,
     "contentType": "album"
     * @param json
     * @throws JSONException
     */

    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {
            id = json.getInt("id");
            name = json.optString("name");
            title = json.getString("title");
            checked = json.optBoolean("isChecked");
            orderNum = json.getInt("orderNum");
            coverPath = json.getString("coverPath");
            selectedSwitch = json.optBoolean("selectedSwitch");
            finished = json.optBoolean("isFinished");
            contentType = json.optString("contentType");
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public boolean isSelectedSwitch() {
        return selectedSwitch;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(DiscoverCategory another) {
        int ret = 0;
        if (another != null) {
            ret = orderNum - another.orderNum;
        }
        return ret;
    }

}
