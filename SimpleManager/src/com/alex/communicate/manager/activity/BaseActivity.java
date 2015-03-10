package com.alex.communicate.manager.activity;

import android.view.View;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity {


    protected  <T extends View> T findView(int id) {
        T v = (T) findViewById(id);
        if (v == null) {
            throw new IllegalArgumentException("view 0x" + Integer.toHexString(id) + " doesn't exist");
        }
        return v;
    }

}
