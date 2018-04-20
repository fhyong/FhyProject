package com.base.util.common;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Observer;

/**
 * 描述: 对RxPermission进一步简化封装，提供制定权限的处理流程
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-28 13:44
 */

public class RxPermissionUtil {

    /**
     * 处理权限的检查申请等流程
     * @param activity
     * @param permissionName    权限名称
     */
    public static void handlePermission(final Activity activity, String permissionName) {
        if(checkPermission(activity, permissionName)) {// 具备权限
            return;
        }
        requestPermission(activity, permissionName);
    }

    /**
     * 获取权限
     * @param activity
     * @param permissionName        权限名称
     */
    private static void requestPermission(final Activity activity, final String permissionName) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions
                .request(permissionName)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean granted) {
                    }
                });
    }

    /**
     * 检查是否具有指定权限
     * @param activity
     * @param permissionName        权限名
     * @return
     */
    private static boolean checkPermission(Activity activity, String permissionName) {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M) {
            return true;
        }
        if(ContextCompat.checkSelfPermission(activity, permissionName)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private static void startAppSettings(final Activity activity) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }
}
