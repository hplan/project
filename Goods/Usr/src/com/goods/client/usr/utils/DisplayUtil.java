package com.goods.client.usr.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Alex on 2015/3/25.
 */
public class DisplayUtil {

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            return wm.getDefaultDisplay().getWidth();
        }
        return 0;
    }
}
