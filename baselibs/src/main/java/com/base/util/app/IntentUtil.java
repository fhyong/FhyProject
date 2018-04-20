package com.base.util.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 描述: Intent跳转工具类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-16 15:29
 */
public class IntentUtil {

    /**
     * 通过类名启动Activity
     *
     * @param context 上下文
     * @param pClass  目标Activity的Class实例
     */
    public static void openActivity(Context context, Class<?> pClass) {
        openActivity(context, pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param context 上下文
     * @param pClass  目标Activity的Class实例
     * @param pBundle 传递的bundle对象
     */
    public static void openActivity(Context context, Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(context, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
    }

    /**
     * 通过Action启动Activity
     *
     * @param context 上下文
     * @param pAction Action名称
     */
    public void openActivity(Context context, String pAction) {
        openActivity(context, pAction, null);
    }

    /**
     * 通过Action启动Activity，并且含有Bundle数据
     *
     * @param context 上下文
     * @param pAction Action名称
     * @param pBundle 传递的bundle对象
     */
    public static void openActivity(Context context, String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
    }

    /**
     * 通过Intent跳转
     *
     * @param context 上下文
     * @param Intent  Intent对象
     */
    public static void openActivity(Context context, Intent Intent) {
        if (Intent == null) {
            return;
        }
        context.startActivity(Intent);
    }
}
