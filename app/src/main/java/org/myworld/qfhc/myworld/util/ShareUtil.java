package org.myworld.qfhc.myworld.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/8 16:37
 * @备注：
 */
public class ShareUtil {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    /**
     * 初始化共享参数
     * @param context
     */
    public static void initShared(Context context){
        sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void putString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key){
        return sharedPreferences.getString(key, null);
    }

    public static void putInt(String key, int value){
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(String key, int defalutvalue){
        return sharedPreferences.getInt(key, defalutvalue);
    }
}
