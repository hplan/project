package com.goods.client.usr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.goods.client.lib.adapter.BasePagerAdapter;

import java.util.List;

/**
 * Created by Alex on 2015/3/23.
 */
public class WizardAdapter extends BasePagerAdapter {
    private List<View> list;
    private LayoutInflater mInflater;

    public WizardAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setItemValues(List<View> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = list.get(position);
        if (v == null || container == null) {
            return new View(mInflater.getContext());
        }
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container != null) {
            container.removeView(list.get(position));
        }
    }

    @Override
    public void notifyDataSetChanged() {
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        super.isViewFromObject(view, o);
        return view == o;
    }
}
