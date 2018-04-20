package com.base.net;

/**
 * 描述: 接口服务，Retrofit请求接口配置文件
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-19 14:46
 */

public interface ApiService {
    String BASR_URL = "";// 主机地址
/*
    *//**
     * 登陆：
     * @param account       用户名(account)
     * @param password      密码(password)
     * @return
     *//*
    @POST("/uias-web/login/applogin")
    Observable<BaseBean<Integer>> appLogin(@Query("account") String account,
                                           @Query("password") String password);
    *//**
     * 获取转运箱
     * @param dcode       设备编码
     * @return
     *//*
    @GET("/ts-web/ts/selectCarGoDetail/{dcode}")
    Observable<BaseBean<DeviceDetailBean>> selectCarGoDetail(@Path("dcode") String dcode);

    *//**
     * 修改周转筐的状态信息
     * @param did           周转筐id
     * @param loginName     登陆用户名
     * @return
     *//*
    @GET("/ts-web/ts/updateIsArriveDes/{did}/{loginName}")
    Observable<BaseBean<Object>> updateIsArriveDes(@Path("did") String did,
                                                   @Path("loginName") String loginName);

    *//**
     * 查询历史周转筐的信息
     * @param arriveDesDate
     * @return
     *//*
    @GET("/ts-web/ts/selectByArriveDesDate/{arriveDesDate}")
    Observable<BaseBean<List<ZZDeviceHistoryBean>>> selectByArriveDesDate(@Path("arriveDesDate") String arriveDesDate);

    *//**
     * 查询订单列表
     * @param loginName         登陆用户名
     * @param condition         查询条件
     * @return
     *//*
    @GET("/ts-web/ts/findDeliveryOrder")
    Observable<BaseBean<List<OrderInfoBean>>> findDeliveryOrder(@Query("loginName") String loginName,
                                                                @Query("condition") String condition);

    *//**
     * 查询订单详情
     * @param orderNo       订单号
     * @return
     *//*
    @GET("/ts-web/ts/getOrderDetail/{orderNo}")
    Observable<BaseBean<OrderDetailBean>> getOrderDetail(@Path("orderNo") String orderNo);

    *//**
     * 修改订单以送达
     * @param orderNo       订单号
     * @param takeCode      提货码
     * @param loginName     登陆用户名
     * @return
     *//*
    @GET("/ts-web/ts/confirmPickUp/{orderNo}/{takeCode}")
    Observable<BaseBean<Object>> confirmPickUp(@Path("orderNo") String orderNo,
                                               @Path("takeCode") String takeCode,
                                               @Query("loginName") String loginName);

    *//**
     * 收货信息
     * @param loginName         登陆用户名
     * @param startTime         开始时间
     * @param endTime           截止时间
     * @return
     *//*
    @GET("/ts-web/order/getGoodsReceipt")
    Observable<BaseBean<Object>> getGoodsReceipt(@Query("loginName") String loginName,
                                                 @Query("startTime") long startTime,
                                                 @Query("endTime") long endTime);
                                                 */
}
