package com.zz.zhufengtest.app.model.discoverRecomend;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class FocusImagesList {
    /**
     * "id": 3597,
     "shortTitle": "中国每天2亿人乘电梯，安全吗？",
     "longTitle": "中国每天2亿人乘电梯，安全吗？",
     "pic": "http://fdfs.xmcdn.com/group15/M06/52/77/wKgDaFW16vfD9nFSAAHBRSk6xm4459_android_large.jpg",
     "type": 2,
     "uid": 30495264,
     "albumId": 2814299,
     "isShare": false,
     "is_External_url": false
     * @param jsonObject
     */
    private  int id;
    private String shortTitle;
    private String longTitle;
    private String pic;
    private int type;
    private int uid;
    private int albumId;
    private int specialId;
    private int subType;
    private int trackId;
    private String url;
    private boolean isShare;
    private boolean is_External_url;


    public void parseJSON(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        shortTitle = jsonObject.getString("shortTitle");
        longTitle = jsonObject.getString("longTitle");
        pic = jsonObject.getString("pic");
        type = jsonObject.getInt("type");
        uid = jsonObject.optInt("uid");
        albumId = jsonObject.optInt("albumId");
        specialId = jsonObject.optInt("specialId");
        subType = jsonObject.optInt("subType");
        trackId = jsonObject.optInt("trackId");
        url = jsonObject.optString("url");
        isShare = jsonObject.getBoolean("isShare");
        is_External_url = jsonObject.getBoolean("is_External_url");
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public boolean is_External_url() {
        return is_External_url;
    }

    public boolean isShare() {
        return isShare;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public String getPic() {
        return pic;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public int getType() {
        return type;
    }

    public int getUid() {
        return uid;
    }

    public int getSpecialId() {
        return specialId;
    }

    public int getSubType() {
        return subType;
    }

    public int getTrackId() {
        return trackId;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "FocusImagesList{" +
                "albumId=" + albumId +
                ", id=" + id +
                ", shortTitle='" + shortTitle + '\'' +
                ", longTitle='" + longTitle + '\'' +
                ", pic='" + pic + '\'' +
                ", type=" + type +
                ", uid=" + uid +
                ", specialId=" + specialId +
                ", subType=" + subType +
                ", trackId=" + trackId +
                ", url='" + url + '\'' +
                ", isShare=" + isShare +
                ", is_External_url=" + is_External_url +
                '}';
    }
}
