package com.qf.projectone.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 判断是否第一次启动
 * Created by Administrator on 2016/11/21.
 */
public class SharedUtils {

    /**
     * 共享参数名
     */
    static  final String SHARED_NAME="kanfang";

    /**
     * 第一次保存的key值
     */
    static  final String FIRST_RUN = "isFirstRun";

    public static boolean isFirstRun(Context context){
        SharedPreferences preferences=context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        return preferences.getBoolean(FIRST_RUN,true);
    }

    /**
     * 进入主界面
     */
    public static void saveFirstRun(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(FIRST_RUN, false).commit();
    }


}
