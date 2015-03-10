/****************************************************************************
 *
 * FILENAME:        com.alex.communicate.manager.adapter.SimpleBaseAdapter.java
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

import android.view.View;
import android.widget.BaseAdapter;

/**
 * @author lanhp
 * @date 2015-03-10 14:26
 * @descrption
 */

public abstract class SimpleBaseAdapter extends BaseAdapter {

    protected  <T extends View> T findView(View view, int id) {
        T v = (T) view.findViewById(id);
        if (v == null) {
            throw new IllegalArgumentException("view 0x" + Integer.toHexString(id) + " doesn't exist");
        }
        return v;
    }
}
