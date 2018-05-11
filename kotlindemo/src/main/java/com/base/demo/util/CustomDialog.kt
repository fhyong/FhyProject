package com.base.demo.util

/**
 * 描述: 类的描述

 * @author : [Fanhy](mailto:fanhuayong@yinli56.com)
 * *
 * @version : Ver 1.0
 * *
 * @date : 2018-05-02 14:42
 */

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.base.demo.R

/**
 * 加载提醒对话框
 */
class CustomDialog : ProgressDialog {
    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, theme: Int) : super(context, theme) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(context)
    }

    private fun init(context: Context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false)
        setCanceledOnTouchOutside(false)

        setContentView(R.layout.progress_layout)
        val params = window!!.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params
    }
}
