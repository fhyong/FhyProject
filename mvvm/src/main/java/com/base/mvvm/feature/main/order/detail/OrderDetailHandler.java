package com.base.mvvm.feature.main.order.detail;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.base.mvvm.databinding.ActivityOrderDetailBinding;
import com.base.mvvm.databinding.CommodityItemLayoutBinding;
import com.base.mvvm.databinding.OrderListItemLayoutBinding;
import com.base.mvvm.feature.main.order.OrderDetailBean;
import com.base.util.app.IntentUtil;
import com.base.util.common.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述: 订单列表相关处理工具
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-19 10:24
 */

public class OrderDetailHandler {
    private ActivityOrderDetailBinding binding;
    private CommodityItemLayoutBinding commodityBinding;

    public OrderDetailHandler(ActivityOrderDetailBinding binding) {
        this.binding = binding;
    }

    public void setCommodityBinding(CommodityItemLayoutBinding commodityBinding) {
        this.commodityBinding = commodityBinding;
    }

    /**
     * 时间格式化：把年月日，时间段进行格式化和拼接
     *
     * @return
     */
    public String getOrderDeliveryTimeStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        OrderDetailBean orderDetailBean = binding.getOrderDetailBean();
        if(orderDetailBean == null) {
            return "";
        }

        String sendDate = format.format(new Date(orderDetailBean.sendDate));
        String sendTimeName = orderDetailBean.sendTimeName;
        return sendDate + " " + sendTimeName;
    }

    /**
     * 获取商品规格
     *
     * @return
     */
    public String getCommodityStandard() {
        String standard = commodityBinding.getCommodityBean().standard;
        if(StringUtils.isEmpty(standard)) {
            commodityBinding.tvCommodityStandard.setVisibility(View.INVISIBLE);
            return "";
        } else {
            commodityBinding.tvCommodityStandard.setVisibility(View.VISIBLE);
        }
        return standard;
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
        intent.putExtra("orderNo", binding.getOrderDetailBean().orderNo);
        IntentUtil.openActivity(view.getContext(), intent);
    }

    /**
     * 拨打电话
     *
     * @param view
     */
    public void callPhone(View view) {
        Toast.makeText(view.getContext(), "打电话", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + binding.getOrderDetailBean().sendPhone);
        intent.setData(data);
        IntentUtil.openActivity(view.getContext(), intent);
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
