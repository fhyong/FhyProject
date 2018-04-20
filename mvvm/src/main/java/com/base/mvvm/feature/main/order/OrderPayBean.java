package com.base.mvvm.feature.main.order;

/**
 * 描述: 订单支付信息实体类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-01-08 11:09
 */


public class OrderPayBean {
    /**
     * payId : 84658
     * orderNo : 33567238291456983
     * isPay : 0
     * paySum : 0.01
     * payTime : 1515212214000
     * payType : 3
     * transcation : null
     * payeeCode : null
     * payee : 刘德建
     * account : 2017122801
     * isArrived : 0
     * isSendFms : null
     */

    public int payId;
    public String orderNo;
    public int isPay;
    public double paySum;
    public long payTime;
    /**
     * 生成订单后第一次选择的支付方式
     * 0：微信
     * 1：支付宝
     * 2：银联
     * 3：银犁卡
     * 4：积分兑换
     * 5：组合支付
     */
    public Integer payType;
    public String sppaySum;//银犁卡冻结金额：47.67
    public Object transcation;
    public Object payeeCode;
    public String payee;
    public String account;
    public int isArrived;
    public Object isSendFms;

    @Override
    public String toString() {
        return "OrderPayBean{" +
                "payId=" + payId +
                ", orderNo='" + orderNo + '\'' +
                ", isPay=" + isPay +
                ", paySum=" + paySum +
                ", payTime=" + payTime +
                ", payType=" + payType +
                ", transcation=" + transcation +
                ", payeeCode=" + payeeCode +
                ", payee='" + payee + '\'' +
                ", account='" + account + '\'' +
                ", isArrived=" + isArrived +
                ", isSendFms=" + isSendFms +
                '}';
    }
}