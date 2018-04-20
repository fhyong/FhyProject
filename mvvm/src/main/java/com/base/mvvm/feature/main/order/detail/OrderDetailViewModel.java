package com.base.mvvm.feature.main.order.detail;

import android.util.Log;

import com.base.mvvm.adapter.CommonAdapter;
import com.base.mvvm.bean.BaseBean;
import com.base.mvvm.databinding.ActivityOrderDetailBinding;
import com.base.mvvm.feature.main.order.OrderDetailBean;

/**
 * 描述: 订单详情ViewModel层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-20 8:57
 */

public class OrderDetailViewModel {
    private OrderDetailModel model;
    private ActivityOrderDetailBinding binding;
    private CommonAdapter adapter;

    public OrderDetailViewModel(ActivityOrderDetailBinding binding, CommonAdapter adapter) {
        this.binding = binding;
        this.adapter = adapter;
        model = new OrderDetailModel(this);
    }

    public void getOrderDetail(String orderNo) {
        model.getOrderDetail(orderNo);
    }

    public void updateOrderDetailUI(BaseBean<OrderDetailBean> baseBean) {
        if(baseBean == null || baseBean.result == null) {
            return;
        }
        Log.d("fanhy", "详情：" + baseBean.result);
        binding.setOrderDetailBean(baseBean.result);
        adapter.getData().clear();
        adapter.getData().addAll(baseBean.result.orderCommList);
        adapter.notifyDataSetChanged();
    }
}
