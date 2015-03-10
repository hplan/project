/****************************************************************************
 *
 * FILENAME:        com.alex.communicate.manager.utils.Utils.java
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
package com.alex.communicate.manager.utils;

import com.alex.communicate.manager.entity.HomeInfo;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lanhp
 * @date 2015-03-10 17:56
 * @descrption
 */

public class Utils {

    /**
     * parser asset/home.xml to list.
     * split display home values.
     * @param in InputStream
     * @return
     */
    public static List<HomeInfo> parserHomeItemValue(InputStream in) {
        XmlPullParserFactory factory;
        List<HomeInfo> list = new ArrayList<HomeInfo>();
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(in, "UTF-8");
            int eventType = xpp.getEventType();
            String name;
            HomeInfo info = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG: {
                        name = xpp.getName();
                        if ("item".equals(name)) {
                            info = new HomeInfo();
                            String attrValue;
                            for (int i = 0; i < xpp.getAttributeCount(); i++) {
                                attrValue = xpp.getAttributeValue(i);
                                if (i == 0) {
                                    info.setDisplayName(attrValue);
                                } else if (i == 1) {
                                    info.setResourceName(attrValue);
                                } else if (i == 2) {
                                    info.setOrder(optInt(attrValue));
                                }
                            }
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        name = xpp.getName();
                        if ("item".equals(name) && info != null) {
                            list.add(info);
                        }
                        break;
                    }
                }
                eventType = xpp.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    private static int optInt(String value) {
        int order = 100;
        try {
            order = Integer.parseInt(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return order;
        }
    }
}
