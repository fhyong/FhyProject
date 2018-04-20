package com.base.mvvm.feature.main.order.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.adapter.CommonAdapter;
import com.base.mvvm.databinding.ActivityOrderDetailBinding;
import com.base.mvvm.databinding.CommodityItemLayoutBinding;
import com.base.mvvm.feature.main.order.OrderCommListBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private ActivityOrderDetailBinding binding;
    private OrderDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);
        final OrderDetailHandler orderDetailHandler = new OrderDetailHandler(binding);
        binding.setOrderHandler(orderDetailHandler);
        List<OrderCommListBean> dataList = new ArrayList();
        CommonAdapter adapter = new CommonAdapter(R.layout.commodity_item_layout, dataList) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {
                itemBinding.setVariable(BR.commodityBean, item);
                orderDetailHandler.setCommodityBinding((CommodityItemLayoutBinding) itemBinding);
            }
        };
        viewModel = new OrderDetailViewModel(binding, adapter);
        binding.rvOrderCommodityList.setLayoutManager(new LinearLayoutManager(this));
        adapter.bindToRecyclerView(binding.rvOrderCommodityList);
        viewModel.getOrderDetail(getIntent().getStringExtra("orderNo"));
    }
}
