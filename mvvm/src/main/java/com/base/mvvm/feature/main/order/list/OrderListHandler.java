package com.base.mvvm.feature.main.order.list;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.base.mvvm.databinding.OrderListItemLayoutBinding;
import com.base.mvvm.feature.main.order.detail.OrderDetailActivity;
import com.base.mvvm.feature.main.order.util.OrderUtil;
import com.base.util.app.IntentUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述: 订单列表相关处理工具
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-19 10:24
 */

public class OrderListHandler {
    private OrderListItemLayoutBinding itemBinding;

    public OrderListHandler(OrderListItemLayoutBinding itemBinding) {
        this.itemBinding = itemBinding;
    }

    /**
     * 时间格式化：把年月日，时间段进行格式化和拼接
     *
     * @return
     */
    public String getDeliveryTimeStr() {
        return OrderUtil.getDeliveryTimeStr(itemBinding.getOrderBean().sendDate,
                itemBinding.getOrderBean().sendTimeName + "");
    }

    /**
     * 跳转到订单详情
     *
     * @param view
     */
    public void toOrderDetail(View view) {
        Toast.makeText(view.getContext(), "去订单详情", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(view.getContext(), OrderDetailActivity.class);
        intent.putExtra("orderNo", itemBinding.getOrderBean().orderNo);
        IntentUtil.openActivity(view.getContext(), intent);
    }

    /**
     * 拨打电话
     *
     * @param view
     */
    public void callPhone(View view) {
        OrderUtil.callPhone(view, itemBinding.getOrderBean().sendPhone + "");
    }

    /**
     * 确认收货
     *
     * @param view
     */
    public void comfirmReceive(View view) {
        Toast.makeText(view.getContext(), "确认收货", Toast.LENGTH_SHORT).show();
    }
}
