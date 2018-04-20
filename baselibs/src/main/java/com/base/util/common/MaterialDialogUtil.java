package com.base.util.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

import java.lang.ref.WeakReference;

/**
 * Created by ${qianzhihe} on 2018/1/3.
 * 进度等待框
 */

public class MaterialDialogUtil {

    private volatile static MaterialDialogUtil mDialogUtil;
    private MaterialDialog materialDialog;
    private Context mContext;

    /**
     * 加载等待框
     */
    private static MaterialDialog mMaterialDialog; // 一个Activity下只产生一个MaterialDialog实例
    private WeakReference<Context> mWeakReferenceContext = new WeakReference<>(null);

    public static MaterialDialogUtil getInstance() {
        if (mDialogUtil == null) {
            synchronized (MaterialDialogUtil.class) {
                if (mDialogUtil == null) {
                    mDialogUtil = new MaterialDialogUtil();
                }
            }
        }
        return mDialogUtil;
    }

    public synchronized void showProgressDialog(Context context, String message) {
        mWeakReferenceContext = new WeakReference<>(context);
        if (StringUtils.isEmpty(message)) {
            return;
        }

        if (mMaterialDialog != null && mMaterialDialog.isShowing()) {
            return;
        }

        if (context == null || !(context instanceof Activity)) {
            return;
        }

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
//                .title(R.string.progress_dialog)
                .content(message)
                .canceledOnTouchOutside(false)
                .progress(true, 0)
                .progressIndeterminateStyle(false);
        mMaterialDialog = builder.build();

        if (mMaterialDialog != null && !mMaterialDialog.isShowing() && !((Activity) context).isFinishing()) {
            mMaterialDialog.show();
        }
    }

    public synchronized void dismissDialog() {
        if (mMaterialDialog != null && mMaterialDialog.isShowing()) {
            Context context = ((ContextWrapper) mMaterialDialog.getContext()).getBaseContext();
            if (context instanceof Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed())
                        mMaterialDialog.dismiss();
                }
            } else {//if the Context used wasnt an Activity, then dismiss it too
                mMaterialDialog.dismiss();
            }
        }
        mMaterialDialog = null;
    }


    /**
     * 显示加载进度框
     *
     * @param context
     * @param message    显示的加载消息
     * @param horizontal 是否水平显示加载框：true--水平显示；false--圆形显示
     */
    public void showIndeterminateProgressDialog(Context context, String message, boolean horizontal) {
        this.mContext = context;
        if (materialDialog != null) {
            dismissProgressDialog();
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
//                .title(R.string.progress_dialog)
                .content(message)
                .canceledOnTouchOutside(false)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal);
        materialDialog = builder.build();
        if (((Activity) mContext).hasWindowFocus()) {
            materialDialog.show();
        }
    }

    /**
     * 关闭进度框
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void dismissProgressDialog() {
        isValidContextStatusDissmiss();
    }

    /**
     * 判断创建该Dialog的上下文是否有效，在有效的情况才关闭Dialog。
     */
    public void isValidContextStatusDissmiss() {
        if (materialDialog != null) {
            if (materialDialog.isShowing()) { //check if dialog is showing.

                //get the Context object that was used to great the dialog
                Context context = ((ContextWrapper) materialDialog.getContext()).getBaseContext();

                //if the Context used here was an activity AND it hasn't been finished or destroyed
                //then dismiss it
                if (context instanceof Activity) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed())
                            materialDialog.dismiss();
                    }
                } else //if the Context used wasnt an Activity, then dismiss it too
                    materialDialog.dismiss();
            }
            materialDialog = null;
        }
    }

    /**
     * 先检查context对应的Activity的状态，如果不可用就停止dialog操作
     *
     * @param c
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean isValidContextStatus(Context c) {
        Activity a = (Activity) c;
        if (a.isDestroyed() || a.isFinishing()) {
            Log.i("YXH", "Activity is invalid." + " isDestoryed-->" + a.isDestroyed() + " isFinishing-->" + a.isFinishing());
            return false;
        } else {
            return true;
        }
    }

}
