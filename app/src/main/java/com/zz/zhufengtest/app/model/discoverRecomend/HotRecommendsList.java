package com.zz.zhufengtest.app.model.discoverRecomend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class HotRecommendsList {

    /**
     * "title": "听新闻",
     "contentType": "album",
     "isFinished": false,
     "categoryId": 1,
     "count": 822,
     "hasMore": true,
     "list":
     * @param jsonObject
     */
    private String title;
    private String contentType;
    private boolean isFinished;
    private int categoryId;
    private int count;
    private boolean hasMore;
    private List<HotRecommendsList_list> list;
    public void parseJSON(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("title");
        contentType = jsonObject.getString("contentType");
        isFinished = jsonObject.getBoolean("isFinished");
        categoryId = jsonObject.getInt("categoryId");
        count = jsonObject.getInt("count");
        hasMore = jsonObject.getBoolean("hasMore");
        JSONArray array = jsonObject.getJSONArray("list");
        list = new LinkedList<HotRecommendsList_list>();
        int len = array.length();
        if (len > 0){
            for (int i = 0; i < len; i++) {
                HotRecommendsList_list hotRecommendsList_list = new HotRecommendsList_list();
                hotRecommendsList_list.parse(array.getJSONObject(i));
                list.add(hotRecommendsList_list);
            }
        }
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getContentType() {
        return contentType;
    }

    public int getCount() {
        return count;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public List<HotRecommendsList_list> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "HotRecommendsList{" +
                "categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", contentType='" + contentType + '\'' +
                ", isFinished=" + isFinished +
                ", count=" + count +
                ", hasMore=" + hasMore +
                ", list=" + list +
                '}';
    }
}
