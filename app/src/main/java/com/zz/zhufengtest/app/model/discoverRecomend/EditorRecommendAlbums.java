package com.zz.zhufengtest.app.model.discoverRecomend;

import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class EditorRecommendAlbums {
    /**
     * "title": "小编推荐",
     "hasMore": true,
     "list":
     */
    private String title;
    private boolean hasMore;
    private List<EditorRecommendAlbumsList> list;

    public boolean isHasMore() {
        return hasMore;
    }

    public List<EditorRecommendAlbumsList> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public void setList(List<EditorRecommendAlbumsList> list) {
        this.list = list;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "EditorRecommendAlbums{" +
                "hasMore=" + hasMore +
                ", title='" + title + '\'' +
                ", list=" + list +
                '}';
    }
}
