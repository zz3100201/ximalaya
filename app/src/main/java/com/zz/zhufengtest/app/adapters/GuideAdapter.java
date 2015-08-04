package com.zz.zhufengtest.app.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.zz.zhufengtest.app.R;

import java.util.List;

/**
 * Created by zhangzhao11 on 2015/7/29.
 * User:zz
 * Date:2015/7/29
 */

/**
 * 教程页中的ViewPager是匹配器
 */
public class GuideAdapter extends PagerAdapter {
    private List<Integer> images;
    private Context context;
    private View.OnClickListener goOnClickListener;

    public GuideAdapter(Context context, List<Integer> images) {
        this.context = context;
        this.images = images;
    }
    public void setGoOnClickListener(View.OnClickListener onClickListener){
        this.goOnClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (images != null) {
            ret = images.size();
        }
        return ret;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //对于FragmentPagerAdapter，object是Fragment
        //view与object的判断就不能直接 view == object 了
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View ret = null;
        int resId = images.get(position);
        if (position == images.size()-1){
            //TODO 最后一个，设置布局，添加按钮
            FrameLayout frameLayout = new FrameLayout(context);
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(resId);
            ViewGroup.LayoutParams lp1 =
                    new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                    );
            imageView.setLayoutParams(lp1);
            frameLayout.addView(imageView);
            ///////////////////////////////////
            Button btnGo = new Button(context);
            btnGo.setText(R.string.guide_start_main);
            //第三个参数指定控件在FrameLayout的哪个位置
            FrameLayout.LayoutParams lp2 =
                    new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM
                    );
            //TODO 需要进行机型适配
            lp2.bottomMargin = 80;
            btnGo.setLayoutParams(lp2);
            frameLayout.addView(btnGo);
            btnGo.setOnClickListener(goOnClickListener);
            //代码创建的Button是没有id的，通过Tag可以进行按钮的区分
            btnGo.setTag("Go");
            ret = frameLayout;
        }else {
            //TODO 直接是图片
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(resId);
            ret = imageView;
        }
        container.addView(ret);
        return ret;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      if (object instanceof View){
          container.removeView((View)object);
      }
    }
}
