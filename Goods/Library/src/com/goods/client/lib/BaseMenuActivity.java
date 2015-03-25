package com.goods.client.lib;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by Alex on 2015/3/25.
 */
public class BaseMenuActivity extends SlidingFragmentActivity {

    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseMenuActivity.this;
    }

    protected  <T extends View> T findView(int id) {
        T v = (T) findViewById(id);
        if (v == null) {
            throw new IllegalArgumentException("view 0x" + Integer.toHexString(id) + " doesn't exist");
        }
        return v;
    }

    protected  <T extends View> T findView(View view, int id) {
        T v = (T) view.findViewById(id);
        if (v == null) {
            throw new IllegalArgumentException("view 0x" + Integer.toHexString(id) + " doesn't exist");
        }
        return v;
    }
}
