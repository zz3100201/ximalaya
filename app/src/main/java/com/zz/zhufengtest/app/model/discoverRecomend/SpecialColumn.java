package com.zz.zhufengtest.app.model.discoverRecomend;

import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class SpecialColumn {
    private String title;
    private boolean hasMore;
    private List<SpecialColumnList> list;

    public boolean isHasMore() {
        return hasMore;
    }

    public List<SpecialColumnList> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public void setList(List<SpecialColumnList> list) {
        this.list = list;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SpecialColumn{" +
                "hasMore=" + hasMore +
                ", title='" + title + '\'' +
                ", list=" + list +
                '}';
    }
}
