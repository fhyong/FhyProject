package com.base.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.base.util.common.SystemUtil;

/**
 * Created by ${qianzhihe} on 2017/12/25.
 * 自定义对话框
 */

public class CustomDialog extends Dialog {

    Context mContext;
    View customView;
    int resLayoutId;
    int widthMolecularVaule = 0;//分式的分子值
    int widthDenominatorVaule = 1;//分式的分母值

    int heightMolecularVaule = 0;
    int heightDenominatorVaule = 1;

    boolean isCanceled;//默认不能取消(空白处)
    boolean isCancelable;//默认不能取消(系统返回键)
    String titleStr;
    String messageStr;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
//        customView= LayoutInflater.from(mContext).inflate(R.layout.dialog_with_tooltip, null);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        this.setContentView(customView);
        initDialogWidthAndHeight();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(layoutParams);
    }

    /**
     * 宽度值：组合成分式
     *
     * @param mWidthMolecularVaule   分子值
     * @param mWidthDenominatorVaule 分母值(不能为0)
     */
    public void setDialogWidth(int mWidthMolecularVaule, int mWidthDenominatorVaule) {
        this.widthMolecularVaule = mWidthMolecularVaule;
        this.widthDenominatorVaule = mWidthDenominatorVaule;
    }

    /**
     * 高度值：组合成分式
     *
     * @param mHeightMolecularVaule   分子值
     * @param mHeightDenominatorVaule 分母值(不能为0)
     */
    public void setDialogHeight(int mHeightMolecularVaule, int mHeightDenominatorVaule) {
        this.heightMolecularVaule = mHeightMolecularVaule;
        this.heightDenominatorVaule = mHeightDenominatorVaule;
    }

    /**
     * 初始化对话框的显示宽高
     */
    public void initDialogWidthAndHeight() {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        if (widthMolecularVaule > 0 && widthDenominatorVaule > 0 && widthMolecularVaule <= widthDenominatorVaule) {
            layoutParams.width = SystemUtil.getPhoneWidthAndHeight((Activity) mContext).getDisWidth() * widthMolecularVaule / widthDenominatorVaule;
        }
        if (heightMolecularVaule > 0 && heightDenominatorVaule > 0 && heightMolecularVaule <= heightDenominatorVaule) {
            layoutParams.height = SystemUtil.getPhoneWidthAndHeight((Activity) mContext).getDisHeight() * heightMolecularVaule / heightDenominatorVaule;
        }
        getWindow().setAttributes(layoutParams);
    }

    /**
     * 设置自定义view的资源id，并初始化
     *
     * @param resId
     */
    public void setResLayoutId(int resId) {
        this.resLayoutId = resId;
        customView = LayoutInflater.from(mContext).inflate(resLayoutId, null);
    }

    /**
     * 设置点击空白处是否能取消对话框
     *
     * @param isCanceled false：默认不能取消
     */
    public void setIsCanceledOnOutside(boolean isCanceled) {
        this.isCanceled = isCanceled;
        setCanceledOnTouchOutside(isCanceled);
    }

    /**
     * 设置点击系统返回键是否能取消对话框
     *
     * @param isCancelable false：默认不能取消
     */
    public void setIsCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
        setCancelable(isCancelable);
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }

    public View getCustomView() {
        return customView;
    }

}
