package com.zz.zhufengtest.app.model.discoverRecomend;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class DiscoveryColumns {
    private String title;
    private int locationInHotRecommend;
    private List<DiscoveryColumnsList> list;

    public List<DiscoveryColumnsList> getList() {
        return list;
    }

    public void setList(List<DiscoveryColumnsList> list) {
        this.list = list;
    }

    public int getLocationInHotRecommend() {
        return locationInHotRecommend;
    }

    public String getTitle() {
        return title;
    }

    public void setLocationInHotRecommend(int locationInHotRecommend) {
        this.locationInHotRecommend = locationInHotRecommend;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DiscoveryColumns{" +
                "list=" + list +
                ", title='" + title + '\'' +
                ", locationInHotRecommend=" + locationInHotRecommend +
                '}';
    }
}
