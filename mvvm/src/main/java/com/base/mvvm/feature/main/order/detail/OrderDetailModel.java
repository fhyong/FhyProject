package com.base.mvvm.feature.main.order.detail;

import com.base.mvvm.R;
import com.base.mvvm.bean.BaseBean;
import com.base.mvvm.feature.main.order.OrderDetailBean;
import com.base.mvvm.net.ApiServiceFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述: 订单详情Model层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-20 8:55
 */

public class OrderDetailModel {
    private OrderDetailViewModel viewModel;

    public OrderDetailModel(OrderDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void getOrderDetail(String orderNo) {
        ApiServiceFactory
                .getApiService()
                .getOrderDetail(orderNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<OrderDetailBean>>() {
                    @Override
                    public void accept(BaseBean<OrderDetailBean> orderDetailBeanBaseBean) throws Exception {
                        viewModel.updateOrderDetailUI(orderDetailBeanBaseBean);
                    }
                });
    }
}
