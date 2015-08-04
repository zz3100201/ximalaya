package com.zz.zhufengtest.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TabHost;
import com.zz.zhufengtest.app.Fragments.CustomFragment;
import com.zz.zhufengtest.app.Fragments.DiscoverFragment;
import com.zz.zhufengtest.app.Fragments.DownloadTingFragment;
import com.zz.zhufengtest.app.Fragments.ProfileFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private DiscoverFragment discoverFragment;
    private CustomFragment customFragment;
    private DownloadTingFragment downloadTingFragment;
    private ProfileFragment profileFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////////////////////////////////////////////////
        setTitle("主界面");
        ///////////////////////////////////////////////
        RadioGroup tabBar = (RadioGroup) findViewById(R.id.main_radio_group);
        tabBar.setOnCheckedChangeListener(this);
        //默认选中第一个
        tabBar.check(R.id.main_tab_item_discover);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager =
                getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = null;
        switch (checkedId){
            case R.id.main_tab_item_discover:
                if(discoverFragment == null){
                    discoverFragment = new DiscoverFragment();
                }
                fragment = discoverFragment;
                break;
            case R.id.main_tab_item_custom:
                if (customFragment == null) {
                    customFragment = new CustomFragment();
                }
                fragment = customFragment;
                break;
            case R.id.main_tab_item_download:
                if (downloadTingFragment == null) {
                    downloadTingFragment = new DownloadTingFragment();
                }
                fragment = downloadTingFragment;
                break;
            case R.id.main_tab_item_profile:
                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                }
                fragment = profileFragment;
                break;
        }
        transaction.replace(R.id.main_fragment_container,fragment);
        transaction.commit();
    }

    @Override
    protected int getExitAnimationId() {
        return 0;
    }
}
