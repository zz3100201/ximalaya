package com.zz.zhufengtest.app.model.discoverRecomend;

import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class FocusImages {
    private String title;
    private List<FocusImagesList> list;

    public List<FocusImagesList> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    public void setList(List<FocusImagesList> list) {
        this.list = list;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "FocusImages{" +
                "list=" + list +
                ", title='" + title + '\'' +
                '}';
    }
}
