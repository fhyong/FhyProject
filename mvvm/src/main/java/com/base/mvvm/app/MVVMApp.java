package com.base.mvvm.app;

import android.app.Application;
import android.content.Context;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-18 14:40
 */

public class MVVMApp extends Application {
    private static Context appContext;
    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }
}
