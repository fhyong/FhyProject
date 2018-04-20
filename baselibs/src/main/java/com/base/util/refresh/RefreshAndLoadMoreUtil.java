package com.base.util.refresh;

import android.app.Activity;

import com.base.view.SimpleFooterView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

/**
 * 功能描述：下拉刷新和上拉加载更多工具
 * Author：Created by Fanhy on 2018-01-03.
 * eMail：fanhuayong@yinli56.com
 */

public class RefreshAndLoadMoreUtil {
    /**
     * 下拉刷新框架基本设置
     * @param activity          Activity对象
     * @param trlRefresh        下拉刷新控件
     * @param listenerAdapter    下拉刷新回调接口
     */
    public static void setRefreshAndLoadMore(
             Activity activity,
             TwinklingRefreshLayout trlRefresh,
             RefreshListenerAdapter listenerAdapter,
             int bottomLayoutId) {
        trlRefresh.setHeaderView(new SinaRefreshView(activity));
        trlRefresh.setBottomView(new SimpleFooterView(activity, bottomLayoutId));
        trlRefresh.setAutoLoadMore(false);
        trlRefresh.setEnableOverScroll(false);
        trlRefresh.setOnRefreshListener(listenerAdapter);
    }

    /**
     * 停止加载更多或者刷新
     */
    public static void stopUpdateOrLoadMore(TwinklingRefreshLayout trlRefresh) {
        trlRefresh.finishLoadmore();
        trlRefresh.finishRefreshing();
    }
}
