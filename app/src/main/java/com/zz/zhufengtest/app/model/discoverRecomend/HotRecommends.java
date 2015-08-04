package com.zz.zhufengtest.app.model.discoverRecomend;

import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class HotRecommends {
    private String title;
    private List<HotRecommendsList> list;

    public List<HotRecommendsList> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    public void setList(List<HotRecommendsList> list) {
        this.list = list;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "HotRecommends{" +
                "list=" + list +
                ", title='" + title + '\'' +
                '}';
    }
}
