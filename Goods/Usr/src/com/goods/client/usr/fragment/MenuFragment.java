package com.goods.client.usr.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.goods.client.lib.utils.ILog;
import com.goods.client.usr.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 2015/3/25.
 */
public class MenuFragment extends Fragment {

    private View mRootView;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.layout_menu, container, false);
        ILog.i("onCreateView");

        return mRootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ILog.i("onCreate");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ILog.i("onActivityCreate");

        showMenuContent();
        final ActionBar actionBar = getActivity().getActionBar();
        actionBar.show();
    }

    private void showMenuContent() {
        mListView = (ListView) mRootView.findViewById(R.id.lv_menu);
        SampleAdapter adapter = new SampleAdapter(getActivity());
        mListView.setAdapter(adapter);

        List<SampleItem> list = new ArrayList<SampleItem>();
        list.add(new SampleItem("叫车", android.R.drawable.ic_menu_save));
        list.add(new SampleItem("记录", android.R.drawable.ic_menu_save));
        list.add(new SampleItem("我的发布", android.R.drawable.ic_menu_save));
        list.add(new SampleItem("收费表", android.R.drawable.ic_menu_save));
        list.add(new SampleItem("设置", android.R.drawable.ic_menu_save));
        adapter.setItemValues(list);
    }


    private class SampleItem {
        public String tag;
        public int iconRes;
        public SampleItem(String tag, int iconRes) {
            this.tag = tag;
            this.iconRes = iconRes;
        }
    }

    public class SampleAdapter extends BaseAdapter {

        private List<SampleItem> list;
        private LayoutInflater mInflater;
        public SampleAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public void setItemValues(List<SampleItem> list) {
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

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_menu, null);
                holder.mIconIv = (ImageView) convertView.findViewById(R.id.img_menu_item);
                holder.mNameTv = (TextView) convertView.findViewById(R.id.tv_menu_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            SampleItem item = (SampleItem) getItem(position);
            if (item != null) {
                holder.mIconIv.setImageResource(item.iconRes);
                holder.mNameTv.setText(item.tag);
            }
            return convertView;
        }

        class ViewHolder {
            private ImageView mIconIv;
            private TextView mNameTv;
        }
    }
}
