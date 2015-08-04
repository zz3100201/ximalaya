package com.zz.zhufengtest.app.data;

import com.zz.zhufengtest.app.model.DiscoverCategory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/31.
 * User:zz
 * Date:2015/7/31
 */
public class DataStore2 {
    private static DataStore2 outInstance;
    private List<DiscoverCategory> discoverCategories;
    private DataStore2(){
        discoverCategories = new LinkedList<DiscoverCategory>();
    }
    public static DataStore2 getDataStore2(){
        if (outInstance == null) {
            outInstance = new DataStore2();
        }
        return outInstance;
    }
    public static void release(){
        outInstance = null;
    }
    public  void setDiscoverCategories(List<DiscoverCategory> categories){
        if (discoverCategories != null&& !discoverCategories.isEmpty()) {
            discoverCategories.clear();
            discoverCategories.addAll(categories);
            Collections.sort(discoverCategories);
        }
    }
}
