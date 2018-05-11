package com.base.mvvm.feature.main.order.list;

import com.base.mvvm.R;
import com.base.mvvm.bean.BaseBean;
import com.base.mvvm.bean.UserBean;
import com.base.mvvm.feature.main.order.OrderInfoBean;
import com.base.mvvm.net.ApiServiceFactory;
import com.base.mvvm.util.PrefrenceUtil;
import com.base.util.common.StringUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述: 订单列表Model层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-19 10:38
 */

public class OrderListModel {
    private OrderListViewModel viewModel;

    public OrderListModel(OrderListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void getOrderList(String condition) {
        UserBean user = PrefrenceUtil.getUserBean();
        if(user == null) {
            return;
        }
        String userName = user.userName;
        ApiServiceFactory
                .getApiService()
                .findDeliveryOrder(userName, condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<OrderInfoBean>>>() {
                    @Override
                    public void accept(BaseBean<List<OrderInfoBean>> listBaseBean) throws Exception {
                        viewModel.updateOrderListResult(listBaseBean);
                    }
                });
    }

    public void confirmReceive(String orderNo, String inputCode) {
        UserBean user = PrefrenceUtil.getUserBean();
        if(user == null) {
            return;
        }
        String userName = user.userName;
        ApiServiceFactory
                .getApiService()
                .confirmPickUp(orderNo, inputCode, userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<Object>>() {
                    @Override
                    public void accept(BaseBean<Object> objectBaseBean) throws Exception {
                        viewModel.updateConfirmReceiveResult(objectBaseBean);
                    }
                });
    }
}
