package com.base.demo.app

import android.app.Application
import android.content.Context

/**
 * 描述: Application类

 * @author : [Fanhy](mailto:fanhuayong@yinli56.com)
 * *
 * @version : Ver 1.0
 * *
 * @date : 2018-04-18 14:40
 */

class KotlinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}
