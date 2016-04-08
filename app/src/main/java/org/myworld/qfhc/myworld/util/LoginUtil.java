package org.myworld.qfhc.myworld.util;

import android.content.Context;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/8 21:16
 * @备注：
 */
public class LoginUtil {

    public static void sinaLongin(Context context, PlatformActionListener listener){
        ShareSDK.initSDK(context);
        Platform weibo = ShareSDK.getPlatform(context, SinaWeibo.NAME);
        weibo.setPlatformActionListener(listener);
        weibo.showUser(null);//执行登录，登录后在回调里面获取用户资料
        //weibo.showUser(“3189087725”);//获取账号为“3189087725”的资料



    }
    public static void QzomLogin(Context context,PlatformActionListener listener) {
        ShareSDK.initSDK(context);
        Platform qzone = ShareSDK.getPlatform(context, QZone.NAME);
        qzone.setPlatformActionListener(listener);
        qzone.showUser(null);//执行登录，登录后在回调里面获取用户资料
        //weibo.showUser(“3189087725”);//获取账号为“3189087725”的资料
    }
}
