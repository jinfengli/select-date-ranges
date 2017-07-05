package com.kingfeng.select_date_ranges.timessquare;

import android.support.v4.BuildConfig;
import android.util.Log;

/**
 * Log utility class to handle the log tag and DEBUG-only logging.
 */
final class Logr {
    private static final String TAG = "TimesSquare";
    public static void d(String message) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message);
        }
    }

    public static void d(String message, Object... args) {
        if (BuildConfig.DEBUG) {
            d(String.format(message, args));
        }
    }
}
