package com.zz.zhufengtest.app.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zz.zhufengtest.app.Constants;
import com.zz.zhufengtest.app.Fragments.discover.*;
import com.zz.zhufengtest.app.R;
import com.zz.zhufengtest.app.Tasks.TaskCallback;
import com.zz.zhufengtest.app.Tasks.TaskResult;
import com.zz.zhufengtest.app.Tasks.impl.DiscoverTagTask;
import com.zz.zhufengtest.app.adapters.CommonFragmentPagerAdapter;
import com.zz.zhufengtest.app.model.DiscoverTab;
import com.zz.zhufengtest.app.parsers.DataParser;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements View.OnClickListener, TabLayout.OnTabSelectedListener, TaskCallback {
private ViewPager pager;
    private TabLayout tabBar;
    private List<DiscoverTab> tabTitles;
    private List<Fragment> subFragments;
    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabTitles = new LinkedList<DiscoverTab>();
        subFragments = new LinkedList<Fragment>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover,container,false);
        View btnSearch = ret.findViewById(R.id.discover_search_title);
        if (btnSearch != null) {
            btnSearch.setOnClickListener(this);
        }
        tabBar = (TabLayout) ret.findViewById(R.id.discover_tab_bar);
        //TODO 因为Tab的设置是从网络来的，因此需要动态添加

        //////////////////////////////////////////////////////
        pager= (ViewPager)ret.findViewById(R.id.discover_pager);
        //设置adapter
        //TODO 由于Tab是动态设置的，所以ViewPager Adapter也需要动态设置

        //ViewPager在滑动页面的时候，添加监听
        //监听由TabLayoutOnPagerChangeListener来完成
        //从而实现ViewPager滚动，上面的TabLayout跟随滚动Tab
        pager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabBar)
        );
        //加载Tabs
        DiscoverTagTask task = new DiscoverTagTask(this);
        task.execute();
        return ret;

    }

    ////////////////////////////////////
    //TabLayout的Tab的
    @Override
    public void onClick(View v) {

    }
    ////////////////////////////////////////
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        pager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //TODO 进行刷新
    }
    //////////////////////////////////////////////////////
    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int taskId = result.taskId;
            Object data = result.data;
            if (taskId == Constants.TASK_CATEGORY_TAG_TABS){
                if (data != null) {
                    //解析json
                    if (data instanceof JSONObject){
                        JSONObject jsonObject = (JSONObject) data;
                        tabTitles = DataParser.parseDiscoverTabs(jsonObject);
                        updateTabs();
                    }
                }
            }
        }
    }
    private void updateTabs(){
        if (tabTitles != null) {
            for (DiscoverTab tabTitle : tabTitles) {
                TabLayout.Tab tab = tabBar.newTab();
                tab.setText(tabTitle.getTitle());
                tabBar.addTab(tab);
                //根据内容类型，来设置指定的Fragment
                String type = tabTitle.getContentType();
                if ("recommend".equals(type)) {
                    subFragments.add(new DiscoverRecomendFragment());
                } else if ("category".equals(type)) {
                    subFragments.add(new DiscoverCategoryFragment());
                } else if ("live".equals(type)) {
                    subFragments.add(new DiscoverLiverFragment());
                } else if ("ranking".equals(type)) {
                    subFragments.add(new DiscoverRankingFragment());
                } else if ("anchor".equals(type)) {
                    subFragments.add(new DiscoverAnchorFragment());
                }
            }
            CommonFragmentPagerAdapter adapter =
                    new CommonFragmentPagerAdapter(
                            getChildFragmentManager(),
                            subFragments
                    );
            pager.setAdapter(adapter);
        }
    }
}
