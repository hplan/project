package com.goods.client.usr.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.goods.client.lib.BaseActivity;
import com.goods.client.lib.utils.ILog;
import com.goods.client.usr.R;
import com.goods.client.usr.utils.Constants;

/**
 * Created by Alex on 2015/3/23.
 */
public class StartupActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_startup);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mHandler.sendMessageDelayed(new Message(), 1000);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Intent intent;
            if (isFirstIn()) {
                intent = new Intent(mContext, WizardActivity.class);
            } else {
                intent = new Intent(mContext, MainActivity.class);
            }
            startActivity(intent);
            finish();
        }
    };

    private boolean isFirstIn() {
        SharedPreferences sp = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        ILog.i("sp == null ? " + (sp == null));
        if (sp == null) {
            return true;
        }
        return sp.getBoolean(Constants.SP_FIELD_FIRST_IN, true);
    }
}
