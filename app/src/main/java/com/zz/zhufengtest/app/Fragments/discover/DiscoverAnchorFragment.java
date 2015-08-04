package com.zz.zhufengtest.app.Fragments.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zz.zhufengtest.app.R;

/**
 * Created by zhangzhao11 on 2015/7/29.
 * User:zz
 * Date:2015/7/29
 */
public class DiscoverAnchorFragment extends Fragment {
    public DiscoverAnchorFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_anchor, container, false);
    }
}
