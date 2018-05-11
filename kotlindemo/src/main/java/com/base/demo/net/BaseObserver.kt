package com.base.demo.net

import android.app.Activity
import com.base.demo.util.DialogUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 描述: 对观察者进行封装，实现其中部分方法

 * @author : [Fanhy](mailto:fanhuayong@yinli56.com)
 * *
 * @version : Ver 1.0
 * *
 * @date : 2018-03-21 16:16
 */

abstract class BaseObserver<T>(private val activity: Activity, msg: String) : Observer<Any> {
    private var msg = ""// 进度对话框信息

    init {
        this.msg = msg
    }

    override fun onSubscribe(d: Disposable) {
        DialogUtil.instance.showDialog(activity, msg)
    }

    override fun onNext(value: Any) {
        callBack(value as T)
    }

    override fun onError(e: Throwable) {
        DialogUtil.instance.dismissDialog()
    }

    override fun onComplete() {
        DialogUtil.instance.dismissDialog()
    }

    abstract fun callBack(t: T)
}
