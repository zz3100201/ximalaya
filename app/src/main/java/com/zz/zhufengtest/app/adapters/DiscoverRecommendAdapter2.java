package com.zz.zhufengtest.app.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.zz.zhufengtest.app.Constants;
import com.zz.zhufengtest.app.R;
import com.zz.zhufengtest.app.Tasks.ImageLoadTask;
import com.zz.zhufengtest.app.model.DiscoverRecomend;
import com.zz.zhufengtest.app.model.discoverRecomend.*;

import java.util.List;

/**
 * Created by zhangzhao11 on 2015/8/2.
 * User:zz
 * Date:2015/8/2
 */
public class DiscoverRecommendAdapter2 extends BaseAdapter {
    private Context context;
    private DiscoverRecomend discoverRecomend;

    public DiscoverRecommendAdapter2(Context context) {
        this.context = context;
    }

    public void setRecomend(DiscoverRecomend discoverRecomend){
        this.discoverRecomend = discoverRecomend;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
//        int ret = 0;
//        if (discoverRecomend != null) {
//            HotRecommends hotRecommends = discoverRecomend.getHotRecommends();
//            if (hotRecommends != null) {
//                ret = hotRecommends.getList().size();
//            }
//        }
//        return ret + 3;
        int ret = 0;
        if (discoverRecomend != null) {

            int hotCount = 0;
            HotRecommends hotRecommends = discoverRecomend.getHotRecommends();
            if (hotRecommends != null) {
                //热门推荐的子分类
                List<HotRecommendsList> list = hotRecommends.getList();
                if (list != null) {
                    hotCount = list.size();
                }
            }
            //3是"小编推荐"、“精品听单”、“发现新奇”，
            //hotCount是热门推荐的个数
            ret = 3 +hotCount;
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        Object ret = null;
        switch (position){
            case 0:
                ret = discoverRecomend.getEditorRecommendAlbums();
                break;
            case 1:
                ret = discoverRecomend.getSpecialColumn();
                break;
            case 2:
                ret = discoverRecomend.getDiscoveryColumns();
                break;
            default:
                HotRecommends hotRecommends= discoverRecomend.getHotRecommends();
                if (hotRecommends != null) {
                    ret = hotRecommends.getList().get(position-3);
                }
                break;
        }
        return ret;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        Object item = getItem(position);
        if (item != null) {
            if (item instanceof EditorRecommendAlbums){
                ret = 0;
            }else if (item instanceof SpecialColumn){
                ret = 1;
            }else if (item instanceof DiscoveryColumns){
                ret = 2;
            }else if (item instanceof HotRecommendsList)
                ret = 3;
        }
        return ret;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        int itemViewType = getItemViewType(position);
        Object item = getItem(position);
        switch (itemViewType){
            case 0:
                ret = bindEditorRecommendAlbums(item,convertView,parent);
                break;
            case 1:
                ret = bindSpecialColumn(item,convertView,parent);
                break;
            case 2:
                ret = bindDiscoveryColumns(item,convertView,parent);
                break;
            case 3:
                ret = bindHotRecommendList(item,convertView,parent);
                break;
        }
        return ret;
    }

    private View bindHotRecommendList(Object item, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.item_recomend_album,parent,false);
        }
        HotRecommendViewHolder holder = (HotRecommendViewHolder) ret.getTag();
        if (holder == null) {
            holder = new HotRecommendViewHolder();
            holder.txtTitle =(TextView) ret.findViewById(R.id.item_recomend_album_title);
            holder.txtMore = (TextView) ret.findViewById(R.id.item_recomend_album_more);
            holder.blocks = new ViewGroup[3];
            holder.blocks[0] = (ViewGroup) ret.findViewById(R.id.item_recomend_album_block0);
            holder.blocks[1] = (ViewGroup) ret.findViewById(R.id.item_recomend_album_block1);
            holder.blocks[2] = (ViewGroup) ret.findViewById(R.id.item_recomend_album_block2);
            ret.setTag(holder);
        }
        ////////////////////////////////
        HotRecommendsList hotRecommendsList = (HotRecommendsList) item;
        String title = hotRecommendsList.getTitle();
        holder.txtTitle.setText(title);
        holder.txtMore.setTag(Constants.TAG_DISCOVER_HOT + hotRecommendsList.getCategoryId());
        boolean hasMore = hotRecommendsList.isHasMore();
        if (hasMore){
            holder.txtMore.setVisibility(View.VISIBLE);
        }else {
            holder.txtMore.setVisibility(View.INVISIBLE);
        }
        ////////////////////////////////////////////////
        List<HotRecommendsList_list> list = hotRecommendsList.getList();
        if (list != null) {
            int size = list.size();
            if (size > 3){
                size = 3;
            }
            for (int i = 0; i < size; i++) {
                ImageButton imageButton = (ImageButton) holder.blocks[i].getChildAt(0);
                HotRecommendsList_list hotRecommendsList_list = list.get(i);
                String coverLarge = hotRecommendsList_list.getCoverLarge();
                boolean needLoad = true;
                Object tag = imageButton.getTag();
                if (tag != null) {
                    String s = (String) tag;
                    if (s.equals(coverLarge)){
                        needLoad = false;
                    }
                }
                if (needLoad){
                    imageButton.setImageResource(R.mipmap.ic_launcher);
                }
                TextView blockTitle = (TextView) holder.blocks[i].getChildAt(1);
                blockTitle.setText(hotRecommendsList_list.getTrackTitle());
                imageButton.setTag(coverLarge);
                if (coverLarge != null && needLoad) {
                    ImageLoadTask task = new ImageLoadTask(imageButton);
                    if (Build.VERSION.SDK_INT >= 11){
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, coverLarge);
                    }else {
                        task.execute(coverLarge);
                    }
                }

            }
        }


        return ret;
    }

    private View bindDiscoveryColumns(Object item, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText("1");
        return textView;
    }

    private View bindSpecialColumn(Object item, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText("2");
        return textView;
    }

    private View bindEditorRecommendAlbums(Object item, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText("3");
        return textView;
    }

    private static class HotRecommendViewHolder{
        public TextView txtTitle;
        public TextView txtMore;
        public ViewGroup[] blocks;
    }
}
