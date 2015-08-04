package com.zz.zhufengtest.app.data;

/**
 * Created by zhangzhao11 on 2015/7/29.
 * User:zz
 * Date:2015/7/29
 */

import com.zz.zhufengtest.app.model.DiscoverCategory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 公共的数据存储区
 */
public class DataStore {
    private static DataStore ourInstance;

    public static DataStore getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataStore();
        }
        return ourInstance;
    }
    //单例的释放
    public static void release(){
        ourInstance = null;
    }
    private List<DiscoverCategory> discoverCategories;
    private DataStore() {
        discoverCategories = new LinkedList<DiscoverCategory>();
    }
    public void setDiscoverCategories(List<DiscoverCategory> categories){
        if (categories != null&& !categories.isEmpty()) {
            discoverCategories.clear();
            discoverCategories.addAll(categories);
            Collections.sort(discoverCategories);
        }
    }
    /**
     * 获取已经加载过的分类列表
     * @return
     */
    public List<DiscoverCategory> getDiscoverCategories() {
        return discoverCategories;
    }
}
