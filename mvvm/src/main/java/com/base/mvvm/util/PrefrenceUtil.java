package com.base.mvvm.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.base.mvvm.app.MVVMApp;
import com.base.mvvm.bean.UserBean;

/**
 * 描述: SharedPreferences工具类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-18 14:37
 */

public class PrefrenceUtil {
    private static SharedPreferences sp;
    private static final String SP_FILE_NAME = "spFileName";

    static {
        sp = MVVMApp.getAppContext().getSharedPreferences(SP_FILE_NAME, Context.MODE_MULTI_PROCESS);
    }

    /**
     * 保存本地用户信息
     *
     * @param userBean 用户信息
     */
    public static void setUserBean(UserBean userBean) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userName", userBean.userName);
        editor.putString("userPwd", userBean.userPwd);
        editor.putBoolean("isRecordPwd", userBean.isRecordPwd);
        editor.commit();
    }

    /**
     * 清除本地用户信息
     */
    public static void clearUserBean() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("userName");
        editor.remove("userPwd");
        editor.remove("isRecordPwd");
        editor.commit();
    }

    /**
     * 获取本地用户信息
     *
     * @return
     */
    public static UserBean getUserBean() {
        boolean isRecordPwd = sp.getBoolean("isRecordPwd", false);
        if (!isRecordPwd) {
            return null;
        }
        UserBean userBean = new UserBean();
        userBean.userName = sp.getString("userName", "");
        userBean.userPwd = sp.getString("userPwd", "");
        userBean.isRecordPwd = isRecordPwd;
        return userBean;
    }
}
