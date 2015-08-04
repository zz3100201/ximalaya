package com.zz.zhufengtest.app.parsers;

import android.util.Log;
import com.zz.zhufengtest.app.model.CategoryTagMenu;
import com.zz.zhufengtest.app.model.DiscoverCategory;
import com.zz.zhufengtest.app.model.DiscoverRecomend;
import com.zz.zhufengtest.app.model.DiscoverTab;
import com.zz.zhufengtest.app.model.discoverRecomend.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/28.
 * User:zz
 * Date:2015/7/28
 */
public final class DataParser {


    private DataParser(){

    }

    /**
     * 解析发现的分类
     * @param json JSONObject
     * @return
     */
    public static List<DiscoverCategory> parseDiscoverCategories(JSONObject json){
        List<DiscoverCategory> ret = null;
        if (json != null) {
            try {
                int code = json.getInt("ret");
                if (code == 0){
                    JSONArray jsonArray = json.getJSONArray("list");
                    int len = jsonArray.length();
                    if (len > 0){
                        ret = new LinkedList<DiscoverCategory>();
                        Log.d("大神",""+len);
                        for (int i = 0; i < len; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            DiscoverCategory category = new DiscoverCategory();
                            category.parseJSON(jsonObject);
                            ret.add(category);
                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


    /**
     * 解析CategoryTagMenu返回的JSON结果
     * @param json JSONObject
     * @return List&lt;CategoryTagMenu&gt;
     */
    public static List<CategoryTagMenu> parseCategoryTagMenuResult(JSONObject json){
        List<CategoryTagMenu> ret = null;
        if (json != null){
            try {
                int code = json.getInt("ret");
                if (code ==0){
                    JSONObject data = json.getJSONObject("data");
                    int category_count = data.getInt("category_count");
                    if (category_count >0){
                        JSONArray array = data.getJSONArray("category_list");
                        category_count = array.length();
                        if (category_count > 0){
                            ret = new LinkedList<CategoryTagMenu>();
                            for (int i = 0; i < category_count; i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                CategoryTagMenu menu = new CategoryTagMenu();
                                //实体类自己解析自己的数据
                                menu.parseJSON(jsonObject);
                                ret.add(menu);
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 解析发现的标题
     * @param jsonObject
     * @return
     */
    public static List<DiscoverTab> parseDiscoverTabs(JSONObject jsonObject) {
        List<DiscoverTab> ret = null;
        try {
            int code = jsonObject.getInt("ret");
            if (code == 0){
                JSONObject tabs = jsonObject.getJSONObject("tabs");
                JSONArray jsonArray = tabs.getJSONArray("list");
                if (jsonArray != null) {
                    int len = jsonArray.length();
                    if (len>0){
                        ret = new LinkedList<DiscoverTab>();
                        ret.clear();
                        for (int i = 0; i < len; i++) {
                            DiscoverTab tab = new DiscoverTab();
                            tab.parseJSON(jsonArray.getJSONObject(i));
                            ret.add(tab);
                        }
                        //TODO 更新ViewPager与TabLayout
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static DiscoverRecomend parseDiscoverRecomend(JSONObject jsonObject) {
        DiscoverRecomend discoverRecomend = new DiscoverRecomend();;
        try {
            if (jsonObject != null) {
                int code = jsonObject.getInt("ret");
                if (code == 0){
                    JSONObject discoveryColumnsJSON = jsonObject.getJSONObject("discoveryColumns");
                    JSONObject editorRecommendAlbumsJSON = jsonObject.getJSONObject("editorRecommendAlbums");
                    JSONObject hotRecommendsJSON = jsonObject.getJSONObject("hotRecommends");
                    JSONObject focusImagesJSON = jsonObject.getJSONObject("focusImages");
                    JSONObject specialColumnJSON = jsonObject.getJSONObject("specialColumn");
                    DiscoveryColumns discoveryColumns = parseDiscoverRecomendDiscoveryColumns(discoveryColumnsJSON);
                    EditorRecommendAlbums editorRecommendAlbums = parseDiscoverRecomendEditorRecommendAlbums(editorRecommendAlbumsJSON);
                    HotRecommends hotRecommends = parseDiscoverRecomendHotRecommends(hotRecommendsJSON);
                    FocusImages focusImages = parseDiscoverRecomendFocusImages(focusImagesJSON);
                    SpecialColumn specialColumn = parseDiscoverRecomendSpecialColumn(specialColumnJSON);
                    discoverRecomend.setDiscoveryColumns(discoveryColumns);
                    discoverRecomend.setEditorRecommendAlbums(editorRecommendAlbums);
                    discoverRecomend.setHotRecommends(hotRecommends);
                    discoverRecomend.setFocusImages(focusImages);
                    discoverRecomend.setSpecialColumn(specialColumn);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return discoverRecomend;
    }


    private static SpecialColumn parseDiscoverRecomendSpecialColumn(JSONObject specialColumnJSON) {
        SpecialColumn specialColumn = new SpecialColumn();
        try {
            int code = specialColumnJSON.getInt("ret");
            if (code == 0)
            {
                String title = specialColumnJSON.getString("title");
                boolean hasMore = specialColumnJSON.getBoolean("hasMore");
                JSONArray special_List = specialColumnJSON.getJSONArray("list");
                int len = special_List.length();
                List<SpecialColumnList> list = new LinkedList<SpecialColumnList>();
                if (len >0){
                    for (int i = 0; i < len; i++) {
                        SpecialColumnList specialColumnList = new SpecialColumnList();
                        specialColumnList.parseJSON(special_List.getJSONObject(i));
                        list.add(specialColumnList);
                    }
                }
                specialColumn.setHasMore(hasMore);
                specialColumn.setTitle(title);
                specialColumn.setList(list);}

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return specialColumn;
    }

    private static FocusImages parseDiscoverRecomendFocusImages(JSONObject focusImagesJSON) {
        FocusImages focusImages = new FocusImages();
        try {
            int code = focusImagesJSON.getInt("ret");
            if (code == 0)
            {
                String title = focusImagesJSON.getString("title");
                JSONArray images_List = focusImagesJSON.getJSONArray("list");
                int len = images_List.length();
                List<FocusImagesList> list = new LinkedList<FocusImagesList>();
                if (len >0){
                    for (int i = 0; i < len; i++) {
                        FocusImagesList focusImagesList = new FocusImagesList();

                        focusImagesList.parseJSON(images_List.getJSONObject(i));
                        list.add(focusImagesList);
                    }
                }
                focusImages.setTitle(title);
                focusImages.setList(list);}

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return focusImages;
    }

    private static HotRecommends parseDiscoverRecomendHotRecommends(JSONObject hotRecommendsJSON) {
        HotRecommends hotRecommends = new HotRecommends();
        try {
            int code = hotRecommendsJSON.getInt("ret");
            if (code == 0)
            {
                String title = hotRecommendsJSON.getString("title");
                JSONArray hot_List = hotRecommendsJSON.getJSONArray("list");
                int len = hot_List.length();
                List<HotRecommendsList> list = new LinkedList<HotRecommendsList>();
                if (len >0){
                    for (int i = 0; i < len; i++) {
                        HotRecommendsList hotRecommendsList = new HotRecommendsList();
                        hotRecommendsList.parseJSON(hot_List.getJSONObject(i));
                        list.add(hotRecommendsList);
                    }
                }
                hotRecommends.setTitle(title);
                hotRecommends.setList(list);}

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hotRecommends;
    }

    private static EditorRecommendAlbums parseDiscoverRecomendEditorRecommendAlbums(JSONObject editorRecommendAlbumsJSON) {
        EditorRecommendAlbums editorRecommendAlbums = new EditorRecommendAlbums();
        try {
            int code = editorRecommendAlbumsJSON.getInt("ret");
            if (code == 0)
            {
                String title = editorRecommendAlbumsJSON.getString("title");
                boolean hasMore = editorRecommendAlbumsJSON.getBoolean("hasMore");
                JSONArray albums_List = editorRecommendAlbumsJSON.getJSONArray("list");
                int len = albums_List.length();
                List<EditorRecommendAlbumsList> list = new LinkedList<EditorRecommendAlbumsList>();
                if (len >0){
                    for (int i = 0; i < len; i++) {
                        EditorRecommendAlbumsList editorRecommendAlbumsList = new EditorRecommendAlbumsList();
                        editorRecommendAlbumsList.parseJSON(albums_List.getJSONObject(i));
                        list.add(editorRecommendAlbumsList);
                    }
                }
                editorRecommendAlbums.setHasMore(hasMore);
                editorRecommendAlbums.setTitle(title);
                editorRecommendAlbums.setList(list);}

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return editorRecommendAlbums;
    }

    private static DiscoveryColumns parseDiscoverRecomendDiscoveryColumns(JSONObject discoveryColumnsJSON) {
        DiscoveryColumns discoveryColumns = new DiscoveryColumns();
            try {
                int code = discoveryColumnsJSON.getInt("ret");
                if (code == 0)
                {
                    String title = discoveryColumnsJSON.getString("title");
                    int locationInHotRecommend = discoveryColumnsJSON.getInt("locationInHotRecommend");
                    JSONArray Columns_List = discoveryColumnsJSON.getJSONArray("list");
                    int len = Columns_List.length();
                    List<DiscoveryColumnsList> list = new LinkedList<DiscoveryColumnsList>();
                    if (len >0){
                        for (int i = 0; i < len; i++) {
                            DiscoveryColumnsList discoveryColumnsList = new DiscoveryColumnsList();
                            discoveryColumnsList.parseJSON(Columns_List.getJSONObject(i));
                            list.add(discoveryColumnsList);
                        }
                    }
                    discoveryColumns.setLocationInHotRecommend(locationInHotRecommend);
                    discoveryColumns.setTitle(title);
                    discoveryColumns.setList(list);}

            } catch (JSONException e) {
                e.printStackTrace();
            }

        return discoveryColumns;
    }


}
