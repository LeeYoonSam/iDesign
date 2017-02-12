package com.bup.idesign.util;

import android.util.Log;

import com.bup.idesign.BuildConfig;

/**
 * Created by Albert-IM on 12/02/2017.
 */

public class LogUtil {

    public static void LogE(String tag, String msg) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void LogD(String tag, String msg) {
        if(BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void LogI(String tag, String msg) {
        if(BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }
}
