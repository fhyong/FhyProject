package com.base.mvvm.feature.main.order.list;

import com.base.mvvm.bean.BaseBean;
import com.base.mvvm.feature.main.order.OrderInfoBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * 描述: 订单列表ViewModel层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-19 10:40
 */

public class OrderListViewModel {
    private BaseQuickAdapter adapter;
    private OrderListModel model;

    public OrderListViewModel(BaseQuickAdapter adapter) {
        this.adapter = adapter;
        this.model = new OrderListModel(this);
    }

    public void getOrderList(String condition) {
        model.getOrderList(condition);
    }

    public void updateOrderListResult(BaseBean<List<OrderInfoBean>> listBaseBean) {
        adapter.getData().clear();
        adapter.getData().addAll(listBaseBean.result);
        adapter.notifyDataSetChanged();
    }

    public void confirmReceive(String orderNo, String inputCode) {
        model.confirmReceive(orderNo, inputCode);
    }

    public void updateConfirmReceiveResult(BaseBean<Object> objectBaseBean) {

    }
}
