package com.base.mvvm.feature.main.order.util;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.base.util.app.IntentUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述: 订单模块工具类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-20 13:32
 */

public class OrderUtil {
    /**
     * 拨打电话
     *
     * @param view
     */
    public static void callPhone(View view, String sendPhone) {
        Toast.makeText(view.getContext(), "打电话", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + sendPhone);
        intent.setData(data);
        IntentUtil.openActivity(view.getContext(), intent);
    }

    /**
     * 时间格式化：把年月日，时间段进行格式化和拼接
     *
     * @return
     */
    public static String getDeliveryTimeStr(Long sendDate, String sendTimeName) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Date(sendDate)) + " " + sendTimeName;
    }
}
