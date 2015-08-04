package com.zz.zhufengtest.app.model;

import com.zz.zhufengtest.app.model.discoverRecomend.*;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class DiscoverRecomend {
    private DiscoveryColumns discoveryColumns;
    private EditorRecommendAlbums editorRecommendAlbums;
    private FocusImages focusImages;
    private HotRecommends hotRecommends;
    private SpecialColumn specialColumn;

    public DiscoveryColumns getDiscoveryColumns() {
        return discoveryColumns;
    }

    public EditorRecommendAlbums getEditorRecommendAlbums() {
        return editorRecommendAlbums;
    }

    public FocusImages getFocusImages() {
        return focusImages;
    }

    public HotRecommends getHotRecommends() {
        return hotRecommends;
    }

    public SpecialColumn getSpecialColumn() {
        return specialColumn;
    }

    public void setDiscoveryColumns(DiscoveryColumns discoveryColumns) {
        this.discoveryColumns = discoveryColumns;
    }

    public void setEditorRecommendAlbums(EditorRecommendAlbums editorRecommendAlbums) {
        this.editorRecommendAlbums = editorRecommendAlbums;
    }

    public void setFocusImages(FocusImages focusImages) {
        this.focusImages = focusImages;
    }

    public void setHotRecommends(HotRecommends hotRecommends) {
        this.hotRecommends = hotRecommends;
    }

    public void setSpecialColumn(SpecialColumn specialColumn) {
        this.specialColumn = specialColumn;
    }

    @Override
    public String toString() {
        return "DiscoverRecomend{" +
                "discoveryColumns=" + discoveryColumns +
                ", editorRecommendAlbums=" + editorRecommendAlbums +
                ", focusImages=" + focusImages +
                ", hotRecommends=" + hotRecommends +
                ", specialColumn=" + specialColumn +
                '}';
    }
}
