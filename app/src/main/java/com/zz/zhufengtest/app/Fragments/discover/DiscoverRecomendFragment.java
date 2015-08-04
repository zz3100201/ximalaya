package com.zz.zhufengtest.app.Fragments.discover;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zz.zhufengtest.app.Constants;
import com.zz.zhufengtest.app.R;
import com.zz.zhufengtest.app.SettingsActivity;
import com.zz.zhufengtest.app.Tasks.TaskCallback;
import com.zz.zhufengtest.app.Tasks.TaskResult;
import com.zz.zhufengtest.app.Tasks.impl.DiscoverRecomendTask;
import com.zz.zhufengtest.app.Test1Activity;
import com.zz.zhufengtest.app.adapters.DiscoverRecommendAdapter;
import com.zz.zhufengtest.app.adapters.DiscoverRecommendAdapter2;
import com.zz.zhufengtest.app.model.DiscoverRecomend;
import com.zz.zhufengtest.app.model.discoverRecomend.*;
import com.zz.zhufengtest.app.parsers.DataParser;
import com.zz.zhufengtest.app.util.MyLog;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/29.
 * User:zz
 * Date:2015/7/29
 */
public class DiscoverRecomendFragment extends Fragment implements AdapterView.OnItemClickListener, TaskCallback, View.OnClickListener {

    private ImageView imageView2;
    private DiscoverRecommendAdapter adapter;
    private DiscoverRecommendAdapter discoverRecommendAdapter;

    public DiscoverRecomendFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover_recomend, container, false);
        PullToRefreshListView pullToRefreshListView =
                (PullToRefreshListView) ret.findViewById(R.id.discover_recommend_list);
        if (pullToRefreshListView != null) {
            discoverRecommendAdapter = new DiscoverRecommendAdapter(getActivity());
            discoverRecommendAdapter.setOnClickListener(this);
            pullToRefreshListView.setAdapter(discoverRecommendAdapter);
            pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            ListView listView = pullToRefreshListView.getRefreshableView();
            listView.setDivider(new ColorDrawable(0xff999999));
            listView.setDividerHeight(5);
        }
//        ListView listView = (ListView) ret.findViewById(R.id.discover_recommend_list);
//        Log.d("实际数据","b");
//        if (listView != null) {
            //TODO 设置实际数据的Adapter
//            //添加头部
//            ImageView imageView = new ImageView(getActivity());
//            imageView.setImageResource(R.mipmap.ic_launcher);
//            //通过这个方法，添加跟随滚动的Header
//            listView.addHeaderView(imageView);
//            imageView2 = new ImageView(getActivity());
//            imageView2.setImageResource(R.mipmap.ic_action_search);
//            listView.addHeaderView(imageView2);
//            /////////////////////////////////
//            //添加底部视图
//            TextView btn = new TextView(getActivity());
//            btn.setText("点击加载更多");
//            listView.addFooterView(btn);
//            ArrayList<String> strings = new ArrayList<String>();
//            for (int i = 0; i <30 ; i++) {
//                strings.add(""+i);
//            }
//            ArrayAdapter<String> adapter =
//                    new ArrayAdapter<String>(
//                            getActivity(),
//                            android.R.layout.simple_list_item_1,
//                            strings
//                    );
//            listView.setAdapter(adapter);
            //////////////////////////////////////////
//            adapter = new DiscoverRecommendAdapter(getActivity());
//            MyLog.d("大蛇", "a");
////            adapter.setOnClickListener(this);
//            Log.d("大蛇", "b");
//            listView.setAdapter(adapter);
//            listView.setOnItemClickListener(this);
//        }

        return ret;
    }

    @Override
    public void onResume() {
        super.onResume();
        DiscoverRecomendTask task =
                new DiscoverRecomendTask(this);
        task.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentActivity activity = getActivity();
        if (parent instanceof ListView){
            ListView listView = (ListView) parent;
            int headerViewsCount = listView.getHeaderViewsCount();
            int footerViewsCount = listView.getFooterViewsCount();
            //调整因为HeaderView导致的偏移
            position -= headerViewsCount;
            //数据的个数
            if (footerViewsCount > 0){
                if (position >= 30){
                    //点的不是数据，因为
                }else {

                }
            }else {
                //点到数据上了
            }

        }
        Toast.makeText(activity,""+position,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int taskId = result.taskId;
            Object data = result.data;
            if (taskId == Constants.TASK_DISCOVER_RECOMEND){
                if (data != null) {
                    if (data instanceof JSONObject){
                        JSONObject jsonObject = (JSONObject) data;
                        DiscoverRecomend discoverRecomend = DataParser.parseDiscoverRecomend(jsonObject);
                        adapter.setRecomend(discoverRecomend);

                    }
                }
            }else if (taskId == Constants.TASK_DISCOVER_RECOMEND_HOT){
                if (data != null) {
                    if (data instanceof JSONObject){
                        JSONObject jsonObject = (JSONObject) data;

                    }
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Object tag = v.getTag();

        if (id == R.id.item_recomend_album_more) {//进入到 更多 界面
            String s = (String) tag;
            if (Constants.TAG_DISCOVER_EDITOR.equals(tag)) {
                Toast.makeText(getActivity(), "点了小编推荐", Toast.LENGTH_LONG).show();
            } else if (s.startsWith(Constants.TAG_DISCOVER_HOT)) {
                int index = s.indexOf(':');
                s = s.substring(index + 1);
                int cid = Integer.parseInt(s);
            }
        }else if (v instanceof ImageView){
            //TODO 如果是图片，相当于点击了专辑，跳新专辑列表
            if (tag != null){
                if (tag instanceof String[]){
                    String[] ss = (String[]) tag;
                    if (ss.length > 2){
                        String albumId = ss[1];
                        String trackId = ss[2];
                        //TODO 调用接口20
                        Intent intent = new Intent(getActivity(),Test1Activity.class);
                        intent.putExtra("albumId",albumId);
                        startActivity(intent);

                    }
                }
            }

        }
    }
}
