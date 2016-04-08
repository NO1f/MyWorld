package org.myworld.qfhc.myworld.util;

import android.app.Application;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/28 17:27
 * @备注：
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtil.initVolley(this);
        ShareUtil.initShared(this);
    }
}
