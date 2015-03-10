/****************************************************************************
 *
 * FILENAME:        com.alex.communicate.manager.fragment.HomeFragment.java
 * LAST REVISION:   
 * LAST MODIFIED:   2015-03-10
 * DESCRIPTION:     
 *
 * vi: set ts=4:
 *
 * Copyright (c) 2009-2011 by Grandstream Networks, Inc.
 * All rights reserved.
 *
 * This material is proprietary to Grandstream Networks, Inc. and,
 * in addition to the above mentioned Copyright, may be
 * subject to protection under other intellectual property
 * regimes, including patents, trade secrets, designs and/or
 * trademarks.
 *
 * Any use of this material for any purpose, except with an
 * express license from Grandstream Networks, Inc. is strictly
 * prohibited.
 *
 ***************************************************************************/
package com.alex.communicate.manager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.alex.communicate.manager.R;
import com.alex.communicate.manager.adapter.HomeAdapter;
import com.alex.communicate.manager.entity.HomeInfo;
import com.alex.communicate.manager.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author lanhp
 * @date 2015-03-10 16:55
 * @descrption
 */

public class HomeFragment extends BaseFragment {

    private GridView mGridView;
    private HomeAdapter mAdapter;
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.home, null);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
        flateMainUI();
    }

    private void initViews() {
        mGridView = findView(mRootView, R.id.grid_main);
        mAdapter = new HomeAdapter(getActivity());
        mGridView.setAdapter(mAdapter);
    }

    private void flateMainUI() {
        InputStream is = null;
        List<HomeInfo> list;
        try {
            is = getActivity().getAssets().open("home.xml");
            list = Utils.parserHomeItemValue(is);
            mAdapter.setItemValues(list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
    }
}
