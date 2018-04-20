package com.base.mvvm.net;

import com.base.mvvm.bean.BaseBean;
import com.base.mvvm.feature.main.order.OrderDetailBean;
import com.base.mvvm.feature.main.order.OrderInfoBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 描述: TS-App接口服务
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-19 14:46
 */

public interface ApiService {
    String BASR_URL = "http://test.ylfood.com/";// 预发布环境

    /**
     * 登陆：
     *
     * @param account  用户名(account)
     * @param password 密码(password)
     * @return
     */
    @POST("/uias-web/login/applogin")
    Observable<BaseBean<Integer>> appLogin(@Query("account") String account,
                                           @Query("password") String password);

    /**
     * 查询订单列表
     *
     * @param loginName 登陆用户名
     * @param condition 查询条件
     * @return
     */
    @GET("/ts-web/ts/findDeliveryOrder")
    Observable<BaseBean<List<OrderInfoBean>>> findDeliveryOrder(@Query("loginName") String loginName,
                                                                @Query("condition") String condition);

    /**
     * 查询订单详情
     *
     * @param orderNo 订单号
     * @return
     */
    @GET("/ts-web/ts/getOrderDetail/{orderNo}")
    Observable<BaseBean<OrderDetailBean>> getOrderDetail(@Path("orderNo") String orderNo);

    /**
     * 修改订单以送达
     *
     * @param orderNo   订单号
     * @param takeCode  提货码
     * @param loginName 登陆用户名
     * @return
     */
    @GET("/ts-web/ts/confirmPickUp/{orderNo}/{takeCode}")
    Observable<BaseBean<Object>> confirmPickUp(@Path("orderNo") String orderNo,
                                               @Path("takeCode") String takeCode,
                                               @Query("loginName") String loginName);
}
