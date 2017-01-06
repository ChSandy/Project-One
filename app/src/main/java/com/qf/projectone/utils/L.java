package com.qf.projectone.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/11/21.
 */
public class L {

    static final boolean DEBUG = true;
    static final String TAG = "print";

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
}
