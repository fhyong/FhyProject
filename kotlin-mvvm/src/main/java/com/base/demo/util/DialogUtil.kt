package com.base.demo.util

import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.base.demo.R
import com.base.demo.app.KotlinApp
import com.base.demo.databinding.ProgressLayoutBinding

import com.wang.avi.AVLoadingIndicatorView

/**
 * 描述: 对话框工具类

 * @author : [Fanhy](mailto:fanhuayong@yinli56.com)
 * *
 * @version : Ver 1.0
 * *
 * @date : 2018-05-02 10:08
 */

class DialogUtil {
    private var pd: Dialog? = null
    private var contentView: View? = null
    private var aviLoading: AVLoadingIndicatorView? = null
    private var tvLoadingMsg: TextView? = null

    fun showDialog(activity: Context, msg: String?) {
        if (aviLoading != null && pd != null) {
            if (aviLoading!!.isShown) {
                aviLoading!!.hide()
            }
            if (pd != null) {
                pd?.dismiss()
            }
        }
        val plBinding = DataBindingUtil.inflate<ProgressLayoutBinding>(inflater!!, R.layout.progress_layout, null, false)
        contentView = plBinding.llProgress
        aviLoading = plBinding.aviProgress
        tvLoadingMsg = plBinding.tvProgressMsg
        if (msg != null && msg != "") {
            tvLoadingMsg?.text = msg
        }
        aviLoading?.setIndicator("BallSpinFadeLoaderIndicator")
        pd = CustomDialog(activity, R.style.CustomDialog)
        aviLoading?.show()
        pd?.show()
        pd?.setContentView(contentView!!)
    }

    fun dismissDialog() {
        if (aviLoading == null || pd == null) {
            return
        }

        aviLoading?.hide()
        pd?.dismiss()
    }

    companion object {
        private var mDialogUtil: DialogUtil? = null
        private var inflater: LayoutInflater? = null

        val instance: DialogUtil
            get() {
                if (mDialogUtil == null) {
                    if (mDialogUtil == null) {
                        mDialogUtil = DialogUtil()
                        inflater = KotlinApp.appContext!!
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    }
                }
                return mDialogUtil as DialogUtil
            }
    }
}
