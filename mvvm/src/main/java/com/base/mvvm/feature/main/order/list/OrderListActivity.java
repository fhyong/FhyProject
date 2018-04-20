package com.base.mvvm.feature.main.order.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.base.adapter.CommonAdapter;
import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityOrderListBinding;
import com.base.mvvm.databinding.OrderListItemLayoutBinding;
import com.base.mvvm.feature.main.order.OrderInfoBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 订单列表View层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-19 10:24
 */

public class OrderListActivity extends AppCompatActivity {
    private ActivityOrderListBinding binding;
    private OrderListViewModel viewModel;
    private BaseQuickAdapter adapter;
    private List<OrderInfoBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_list);
        adapter = new CommonAdapter<OrderInfoBean, OrderListItemLayoutBinding>(R.layout.order_list_item_layout, dataList) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {
                itemBinding.setVariable(BR.orderBean, item);// 绑定订单实例
                itemBinding.setVariable(BR.orderHandler, new OrderListHandler(itemBinding));// 绑定Handler实例
            }
        };
        viewModel = new OrderListViewModel(adapter);
        binding.rvOrderList.setLayoutManager(new LinearLayoutManager(this));
        adapter.bindToRecyclerView(binding.rvOrderList);
        viewModel.getOrderList("");
    }
}
