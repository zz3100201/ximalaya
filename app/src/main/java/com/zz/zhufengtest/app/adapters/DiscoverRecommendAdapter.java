package com.zz.zhufengtest.app.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.zz.zhufengtest.app.Constants;
import com.zz.zhufengtest.app.R;
import com.zz.zhufengtest.app.Tasks.ImageLoadTask;
import com.zz.zhufengtest.app.model.DiscoverRecomend;
import com.zz.zhufengtest.app.model.discoverRecomend.*;
import com.zz.zhufengtest.app.util.MyLog;

import java.io.File;
import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */

/**
 * 发现部分 需要支持多布局的处理
 */
public class DiscoverRecommendAdapter extends BaseAdapter {
    private Context context;
    /**
     * 从接口获取的discover recomend内容，完整的推荐
     */
    private DiscoverRecomend recomend;
    private View.OnClickListener onClickListener;
    public  DiscoverRecommendAdapter(Context context){
        this.context = context;
    }

    /**
     * 设置实际的数据
     * 这个方法需要在主线程调用更新
     * @param recomend
     */
    public void setRecomend(DiscoverRecomend recomend){
        this.recomend = recomend;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (recomend != null) {

            int hotCount = 0;
            HotRecommends hotRecommends = recomend.getHotRecommends();
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
                ret = recomend.getEditorRecommendAlbums();
                break;
            case 1:
                ret = recomend.getSpecialColumn();
                break;
            case 2:
                ret = recomend.getDiscoveryColumns();
                break;
            default:
                HotRecommends hotRecommends = recomend.getHotRecommends();
                if (hotRecommends != null) {
                    ret = hotRecommends.getList().get(position-3);
                }
                break;
        }
        return ret;
    }

    @Override
    public int getViewTypeCount() {
        //小编推荐，精品听单，发现新奇
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        Object item = getItem(position);
        if (item instanceof EditorRecommendAlbums){
            ret = 0;
        }else if (item instanceof SpecialColumn){
            ret = 1;
        }else if (item instanceof DiscoveryColumns){
            ret = 2;
        }else if (item instanceof HotRecommendsList){
            ret = 3;
        }
        return ret;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        Object item = getItem(position);
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case 0:
                ret = bindEditorRecomendView(item,convertView,parent);
                break;
            case 1:
                ret = bindSpecialColumnView(item,convertView,parent);
                break;
            case 2:
                ret = bindDiscoverColumnsView(item,convertView,parent);
                break;
            case 3:
                ret = bindHotColumnView(item,convertView,parent);
                break;
        }
        return ret;
    }

    private View bindDiscoverColumnsView(Object item, View convertView, ViewGroup parent) {
        TextView ret = new TextView(context);
        ret.setText("发现新奇");
        return ret;
    }

    /**
     * 热门推荐
     * @param item
     * @param convertView
     * @param parent
     * @return
     */
    private View bindHotColumnView(Object item, View convertView, ViewGroup parent) {
       View ret = null;
        if (convertView != null) {
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.item_recomend_album,parent,false);
        }
        HotRecomendViewHolder holder = (HotRecomendViewHolder) ret.getTag();
        if(holder == null){
            holder = new HotRecomendViewHolder();
            holder.txtTitle = (TextView) ret.findViewById(R.id.item_recomend_album_title);
            holder.txtMore = (TextView) ret.findViewById(R.id.item_recomend_album_more);
            holder.txtMore.setOnClickListener(onClickListener);
            holder.blocks = new ViewGroup[3];
            holder.blocks[0] = (LinearLayout) ret.findViewById(R.id.item_recomend_album_block0);
            holder.blocks[1] = (LinearLayout) ret.findViewById(R.id.item_recomend_album_block1);
            holder.blocks[2] = (LinearLayout) ret.findViewById(R.id.item_recomend_album_block2);

            ret.setTag(holder);
        }
        //////////////////////////////////
        HotRecommendsList hot = (HotRecommendsList) item;

        String title = hot.getTitle();
        holder.txtTitle.setText(title);

        boolean hasMore = hot.isHasMore();
        //对于热门推荐，更多 点击的时候，对象中包含了CategoryId
        //通过这个作为点击事件的入口
        holder.txtMore.setTag(Constants.TAG_DISCOVER_HOT+ hot.getCategoryId());
        if (hasMore) {
            holder.txtMore.setVisibility(View.VISIBLE);
        } else {
            holder.txtMore.setVisibility(View.INVISIBLE);
        }

        ///////////////////////////
        // 水平的图片

        List<HotRecommendsList_list> list = hot.getList();

        if (list != null) {

            int size = list.size();

            if (size > 3) {
                size = 3;
            }

            for (int i = 0; i < size; i++) {
                ViewGroup block = holder.blocks[i];

                ImageButton img = (ImageButton) block.getChildAt(0);

                HotRecommendsList_list recommend = list.get(i);

                // 网址
                String coverLarge = recommend.getCoverLarge();

                boolean needLoad = true;
                Object tag = img.getTag();
                if (tag != null) {
                    if(tag instanceof String){
                        String s = (String) tag;
                        if(s.equals(coverLarge)){
                            needLoad = false;
                        }
                    }else if (tag instanceof String[]){
                        String[] ss = (String[]) tag;
                        if (ss.length > 0){
                            String s = ss[0];
                            if (s.equals(coverLarge)){
                                needLoad = false;
                            }
                        }
                    }
                }
                if(needLoad) {
                    // 设置“图片加载中”显示
                    img.setImageResource(R.mipmap.ic_launcher);
                }

                img.setOnClickListener(onClickListener);
                TextView blockTitle = (TextView) block.getChildAt(1);

                // TODO 加载图片
                blockTitle.setText(recommend.getTrackTitle());

                // 用于在异步任务中，进行图片下载地址的识别，避免错位
//                img.setTag(coverLarge);
                //设置字符串数组Tag
                //索引0用于ImageButton的图片错位问题，其余两个用于ImageButton点击事件的处理
                img.setTag(new String[]{coverLarge,
                        Integer.toString(recommend.getAlbumId()),
                        Integer.toString(recommend.getTrackId())
                });

                if (coverLarge != null && needLoad) {

                    ImageLoadTask task = new ImageLoadTask(img);

                    // 手机版本的适配
                    if (Build.VERSION.SDK_INT >= 11) {
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, coverLarge);
                    } else {
                        task.execute(coverLarge);
                    }
                }
            }
        }


        return ret;
    }


    private View bindSpecialColumnView(Object item, View convertView, ViewGroup parent) {
        TextView ret = new TextView(context);
        ret.setText("精品听单");
        return ret;
    }

    /**
     * 小编
     * @param item
     * @param convertView
     * @param parent
     * @return
     */
    private View bindEditorRecomendView(Object item, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.item_recomend_album, parent, false);
        }
            ALbumRecomendViewHolder holder =
                    (ALbumRecomendViewHolder) ret.getTag();
            if (holder == null){
                holder = new ALbumRecomendViewHolder();
                //TODO 加载View
                holder.txtTitle = (TextView) ret.findViewById(R.id.item_recomend_album_title);
                holder.txtMore = (TextView) ret.findViewById(R.id.item_recomend_album_more);
                // 设置更多的点击处理事件
                holder.txtMore.setOnClickListener(onClickListener);
                ///////////////////////////////////////////////////////////
                holder.block0 = (LinearLayout) ret.findViewById(R.id.item_recomend_album_block0);
                holder.block1 = (LinearLayout) ret.findViewById(R.id.item_recomend_album_block1);
                holder.block2 = (LinearLayout) ret.findViewById(R.id.item_recomend_album_block2);

                holder.block0ImageButton = (ImageButton) holder.block0.getChildAt(0);
                holder.block0ImageButton.setOnClickListener(onClickListener);
                holder.block0TextView = (TextView) holder.block0.getChildAt(1);

                holder.block1ImageButton = (ImageButton) holder.block1.getChildAt(0);
                holder.block1ImageButton.setOnClickListener(onClickListener);
                holder.block1TextView = (TextView) holder.block1.getChildAt(1);

                holder.block2ImageButton = (ImageButton) holder.block2.getChildAt(0);
                holder.block2ImageButton.setOnClickListener(onClickListener);
                holder.block2TextView = (TextView) holder.block2.getChildAt(1);
                ret.setTag(holder);
            }
            EditorRecommendAlbums albums = (EditorRecommendAlbums) item;
            String title = albums.getTitle();
            holder.txtTitle.setText(title);
            holder.txtMore.setTag(Constants.TAG_DISCOVER_EDITOR);
            boolean hasMore = albums.isHasMore();
            if (hasMore){
                holder.txtMore.setVisibility(View.VISIBLE);
            }else {
                holder.txtMore.setVisibility(View.INVISIBLE);
            }
        ////////////////////////////
        List<EditorRecommendAlbumsList> list = albums.getList();
        if (list != null) {
            int size = list.size();
            if(size > 3){
                size =3 ;
            }
            for (int i = 0; i < size; i++) {
                EditorRecommendAlbumsList editorRecommendAlbumsList = list.get(i);
                //图片的网址
                String coverlarge = editorRecommendAlbumsList.getCoverLarge();
                String tit = editorRecommendAlbumsList.getTrackTitle();
                ImageView imageView = null;
                switch (i) {
                    case 0:
                        //TODO 需要显示图片
                        holder.block0TextView.setText(tit);
                        imageView = holder.block0ImageButton;
                        break;
                    case 1:
                        //TODO 需要显示图片
                        holder.block1TextView.setText(tit);
                        imageView = holder.block1ImageButton;
                        break;
                    case 2:
                        //TODO 需要显示图片
                        holder.block2TextView.setText(tit);
                        imageView = holder.block2ImageButton;
                        break;
                }
                if (imageView != null && coverlarge != null){
                    //设置ImageView的Tag，在异步任务中，需要检查这个Tag
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    imageView.setTag(coverlarge);
                    ImageLoadTask task = new ImageLoadTask(imageView);
                    //手机版本的适配
                    if (Build.VERSION.SDK_INT >= 11){
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,coverlarge);
                    }else {
                        task.execute(coverlarge);
                    }

                }

            }
        }

        return ret;
    }

    /**
     * 小编推荐、热门推荐使用的ViewHolder
     */
    private static class HotRecomendViewHolder{
        public TextView txtTitle;
        public TextView txtMore;
        //三块文字图片的布局
        public ViewGroup[] blocks;


    }
    private static class  ALbumRecomendViewHolder{
        public TextView txtTitle;
        public TextView txtMore;

        public LinearLayout block0;
        public LinearLayout block1;
        public LinearLayout block2;
        //每一个block的子内容
        public ImageButton block0ImageButton;
        public TextView block0TextView;
        public ImageButton block1ImageButton;
        public TextView block1TextView;
        public ImageButton block2ImageButton;
        public TextView block2TextView;
    }

    /**
     * 精品听单
     */
    private static class SpecialViewHolder{

    }

    /**
     * 发现新奇
     */
    private static class DiscoverColumnsViewHolder{

    }
}
