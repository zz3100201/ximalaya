package com.zz.zhufengtest.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * 基础的Activity
 */
public class BaseActivity extends FragmentActivity {

    private TextView txtTitle;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //TODO 进行公共的一些控件的内容初始化
        //只要调用了super.setContentView就可以findViewById了
        txtTitle = (TextView) findViewById(R.id.common_header_title);

    }

    /**
     * Activity设置标题的方法
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if(txtTitle != null){
            txtTitle.setText(title);

        }
    }


    /**
     * 获取startActivity之后，新的Activity进入的动画<br/>
     * 默认是从右往左，如果定制不同的动画，重写这个方法即可
     * @return
     */
    protected int getEnterAnimationId(){
        return R.anim.anim_slide_to_left;
    }

    protected int getExitAnimationId(){
        return R.anim.anim_drop_down;
    }

    /**
     * 启动Activity，并且给启动的Activity指定一个动画
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(getEnterAnimationId(),0);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,getExitAnimationId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

}
