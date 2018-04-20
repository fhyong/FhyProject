package com.base.util.hint;

import android.util.Log;

/**
 * 描述: 日志工具类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-19 15:44
 */

public class LogUtil {
    public static final String TAG = "TS-APP";// 默认TAG
    private static final boolean IS_LOG = true;// 默认打印日志，上线版本可以把日志关闭

    /**
     * 打印debug日志
     *
     * @param msg 日志内容
     */
    public static void logD(String msg) {
        if (IS_LOG) {
            Log.d(TAG, msg);
        }
    }

    /**
     * 打印debug日志
     *
     * @param tag 日志tag标签
     * @param msg 日志内容
     */
    public static void logD(String tag, String msg) {
        if (IS_LOG) {
            Log.d(tag, msg);
        }
    }

    /**
     * 打印error日志
     *
     * @param msg 日志内容
     */
    public static void logE(String msg) {
        if (IS_LOG) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 打印error日志
     *
     * @param tag 日志tag标签
     * @param msg 日志内容
     */
    public static void logE(String tag, String msg) {
        if (IS_LOG) {
            Log.e(tag, msg);
        }
    }
}
