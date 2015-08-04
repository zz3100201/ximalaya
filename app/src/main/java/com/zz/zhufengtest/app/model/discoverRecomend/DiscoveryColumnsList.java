package com.zz.zhufengtest.app.model.discoverRecomend;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class DiscoveryColumnsList {
    private int id;
    private int orderNum;
    private String title;
    private String subtitle;
    private String coverPath;
    private String contentType;
    private String url;
    private String sharePic;
    private boolean enableShare;
    private int contentUpdatedAt;
    public void parseJSON(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        orderNum = jsonObject.getInt("orderNum");
        title = jsonObject.getString("title");
        subtitle = jsonObject.getString("subtitle");
        coverPath = jsonObject.getString("coverPath");
        contentType = jsonObject.getString("contentType");
        url = jsonObject.getString("url");
        sharePic = jsonObject.getString("sharePic");
        enableShare = jsonObject.getBoolean("enableShare");
        contentUpdatedAt = jsonObject.getInt("contentUpdatedAt");
    }

    public String getContentType() {
        return contentType;
    }

    public int getContentUpdatedAt() {
        return contentUpdatedAt;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public boolean isEnableShare() {
        return enableShare;
    }

    public int getId() {
        return id;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getSharePic() {
        return sharePic;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "DiscoveryColumnsList{" +
                "contentType='" + contentType + '\'' +
                ", id=" + id +
                ", orderNum=" + orderNum +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", url='" + url + '\'' +
                ", sharePic='" + sharePic + '\'' +
                ", enableShare=" + enableShare +
                ", contentUpdatedAt=" + contentUpdatedAt +
                '}';
    }
}
