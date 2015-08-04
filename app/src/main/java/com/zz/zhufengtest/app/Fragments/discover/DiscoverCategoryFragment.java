package com.zz.zhufengtest.app.Fragments.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.zz.zhufengtest.app.Constants;
import com.zz.zhufengtest.app.R;
import com.zz.zhufengtest.app.Tasks.TaskCallback;
import com.zz.zhufengtest.app.Tasks.TaskResult;
import com.zz.zhufengtest.app.Tasks.impl.DiscoverCategoryTask;
import com.zz.zhufengtest.app.Test1Activity;
import com.zz.zhufengtest.app.data.DataStore;
import com.zz.zhufengtest.app.model.DiscoverCategory;
import com.zz.zhufengtest.app.parsers.DataParser;
import com.zz.zhufengtest.app.util.MyLog;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/29.
 * User:zz
 * Date:2015/7/29
 */
public class DiscoverCategoryFragment extends Fragment implements TaskCallback {
    private static final String TAG = "DCF";
    public DiscoverCategoryFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<DiscoverCategory> categories = DataStore.getInstance().getDiscoverCategories();
        if (categories != null && !categories.isEmpty()) {
            //有分类
            MyLog.d(TAG,"has");
        }else{
            //无分类
            MyLog.d(TAG,"no");
            DiscoverCategoryTask task = new DiscoverCategoryTask(this);

            task.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover_category, container, false);

        return ret;
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int taskId = result.taskId;
            Object data = result.data;
            if (taskId == Constants.TASK_DISCOVER_CATEGORIES){
                if (data!=null){
                    if (data instanceof  JSONObject){
                        List<DiscoverCategory> categories = DataParser.parseDiscoverCategories((JSONObject)data);
                        if (categories != null) {
                            DataStore.getInstance().setDiscoverCategories(categories);
                        }
                    }
                }
            }
        }
    }


}
