package com.goods.client.lib.utils;

import android.util.Log;

/**
 * Created by Alex on 2015/3/25.
 */
public class ILog {
    private static final String TAG = "alex";
    private static final boolean DEBUG = true;

    public static void d(String msg) {
        if (DEBUG) Log.d(TAG, msg);
    }

    public static void i(String msg) {
        if (DEBUG) Log.i(TAG, msg);
    }

    public static void e(String msg) {
        if (DEBUG) Log.e(TAG, msg);
    }

    public static void w(String msg) {
        if (DEBUG) Log.w(TAG, msg);
    }
}
