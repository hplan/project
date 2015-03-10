/****************************************************************************
 *
 * FILENAME:        com.alex.communicate.manager.adapter.MainGridViewAdapter.java
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
package com.alex.communicate.manager.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alex.communicate.manager.R;
import com.alex.communicate.manager.entity.HomeInfo;

import java.util.List;

/**
 * @author lanhp
 * @date 2015-03-10 14:11
 * @descrption
 */

public class HomeAdapter extends SimpleBaseAdapter {
    private LayoutInflater mInflater;
    private List<HomeInfo> list;

    public HomeAdapter(Context context) {
        mInflater =  LayoutInflater.from(context);
    }

    public void setItemValues(List<HomeInfo> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list == null ? null : list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_home, null);
            holder.mItemTv = findView(view, R.id.item_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        HomeInfo info = list.get(i);
        holder.mItemTv.setText(info.getDisplayName());
        if (!TextUtils.isEmpty(info.getResourceName())) {
            int resId = mInflater.getContext().getResources().getIdentifier(info.getResourceName(), "drawable", mInflater.getContext().getPackageName());
            Drawable drawable = mInflater.getContext().getResources().getDrawable(resId);
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.mItemTv.setCompoundDrawables(drawable, null, null, null);
        }
        return view;
    }

    class ViewHolder {
        private TextView mItemTv;
    }
}
