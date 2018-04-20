package com.base.mvvm.feature.main.order;

import java.util.List;

/**
 * 描述: 订单详情实体类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-01-06 17:47
 */

public class OrderDetailBean {
    /**
     * orderId : 92241
     * orderNo : 33567238291456983
     * type : 0
     * customer : 刘德建
     * customerCode : 3983
     * repCustomer : null
     * repCustomerCode : null
     * receiver : 张萌萌
     * receiverCode : null
     * phone : 13666869866
     * zone : 四川省成都市
     * cityCode : null
     * cityName : null
     * addr : 四川省 成都市 青白江区
     * carriage : 0
     * deliverTime : 0
     * modifier : null
     * modifierCode : null
     * modifyTime : 1515212213811
     * site : null-四川省 成都市 青白江区
     * siteCode : null
     * isBill : 1
     * billType : null
     * billTitle : null
     * billContent : null
     * userComments : null
     * orderAmount : 0.01
     * comAmount : 0.01
     * discount : 0
     * status : 3
     * createTime : 1515212213000
     * sign : null
     * verification : 907458
     * resource : 2
     * sellDiscount : null
     * fullDiscount : null
     * ylcardDiscount : null
     * sellDiscountId : null
     * fullDiscountId : null
     * sendAddr : null
     * sendType : 0
     * sendReceiver : null
     * sendPhone : null
     * sendTimeName : null
     * sendTimeValue : null
     * sendDate : null
     * serialno :
     * orderCommList : [{"comId":164334,"orderNo":"33567238291456983","code":"9520000000079","name":"燕麦米  散装  500g","type":"08003009","typeName":"燕麦","price":0.01,"actPrice":0.01,"count":1,"subTotal":0.01,"isPackage":null,"pictureUrl":"http://image.ylfood.com/group1/M00/00/09/rBLsjVkGkCCAFJj5AAkdZuf5wdE500.jpg","areaCode":"0","areaName":"常温区","isReady":0,"readyEndTime":null,"standard":null,"sumCount":null,"sumTotal":null,"categoryCode":null,"categoryName":null,"commoditySource":0,"isGotten":0,"isGottenNum":null,"isFree":0,"deliverTime":null,"commentContent":null,"replyComment":null}]
     * orderLogList : [{"oprId":259565,"orderNo":"33567238291456983","oprTime":1515212214000,"oprContent":"订单发往仓库备货","oprator":"订单系统","opratorCode":"System"},{"oprId":259564,"orderNo":"33567238291456983","oprTime":1515212214000,"oprContent":"已经成功支付0.01元","oprator":"刘德建","opratorCode":"3983"},{"oprId":259563,"orderNo":"33567238291456983","oprTime":1515212213000,"oprContent":"订单已成功提交","oprator":"刘德建","opratorCode":"3983"}]
     * orderPay : {"payId":84658,"orderNo":"33567238291456983","isPay":0,"paySum":0.01,"payTime":1515212214000,"payType":3,"transcation":null,"payeeCode":null,"payee":"刘德建","account":"2017122801","isArrived":0,"isSendFms":null}
     * orderTemp : null
     */

    public int orderId;
    public String orderNo;
    public int type;
    public String customer;
    public String customerCode;
    public Object repCustomer;
    public Object repCustomerCode;
    public String receiver;
    public Object receiverCode;
    public String phone;
    public String zone;
    public Object cityCode;
    public Object cityName;
    public String addr;
    public int carriage;
    public Long deliverTime;
    public Object modifier;
    public Object modifierCode;
    public long modifyTime;
    public String site;
    public String siteCode;
    public int isBill;
    public Object billType;
    public Object billTitle;
    public Object billContent;
    public Object userComments;
    public double orderAmount;
    public double comAmount;
    public int discount;
    public int status;
    public long createTime;
    public Object sign;
    public String verification;
    public int resource;
    public Object sellDiscount;
    public Object fullDiscount;
    public Object ylcardDiscount;
    public Object sellDiscountId;
    public Object fullDiscountId;
    public String sendAddr;
    public int sendType;
    public Object sendReceiver;
    public String sendPhone;
    public String sendTimeName;
    public Long sendTimeValue;
    public Long sendDate;
    public String serialno;
    public OrderPayBean orderPay;
    public Object orderTemp;
    public List<OrderCommListBean> orderCommList;
    public List<OrderLogBean> orderLogList;

    /****** 新加字段 *******/
    public Object orders;
    /**
     * 其父订单编号
     */
    public String orderNoParent;
    /**
     * 是否为父订单：
     *      0: 子订单
     *      1：父订单
     *      null：无父子关系
     */
    public int isParent;
    public Object voucherDiscount;
    public Object voucherDiscountI;

    /**
     * 新增字段，积分订单总金额
     */
    public Integer orderIntegral;

    @Override
    public String toString() {
        return "OrderDetailBean{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", type=" + type +
                ", customer='" + customer + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", repCustomer=" + repCustomer +
                ", repCustomerCode=" + repCustomerCode +
                ", receiver='" + receiver + '\'' +
                ", receiverCode=" + receiverCode +
                ", phone='" + phone + '\'' +
                ", zone='" + zone + '\'' +
                ", cityCode=" + cityCode +
                ", cityName=" + cityName +
                ", addr='" + addr + '\'' +
                ", carriage=" + carriage +
                ", deliverTime=" + deliverTime +
                ", modifier=" + modifier +
                ", modifierCode=" + modifierCode +
                ", modifyTime=" + modifyTime +
                ", site='" + site + '\'' +
                ", siteCode=" + siteCode +
                ", isBill=" + isBill +
                ", billType=" + billType +
                ", billTitle=" + billTitle +
                ", billContent=" + billContent +
                ", userComments=" + userComments +
                ", orderAmount=" + orderAmount +
                ", comAmount=" + comAmount +
                ", discount=" + discount +
                ", status=" + status +
                ", createTime=" + createTime +
                ", sign=" + sign +
                ", verification='" + verification + '\'' +
                ", resource=" + resource +
                ", sellDiscount=" + sellDiscount +
                ", fullDiscount=" + fullDiscount +
                ", ylcardDiscount=" + ylcardDiscount +
                ", sellDiscountId=" + sellDiscountId +
                ", fullDiscountId=" + fullDiscountId +
                ", sendAddr=" + sendAddr +
                ", sendType=" + sendType +
                ", sendReceiver=" + sendReceiver +
                ", sendPhone=" + sendPhone +
                ", sendTimeName='" + sendTimeName + '\'' +
                ", sendTimeValue=" + sendTimeValue +
                ", sendDate=" + sendDate +
                ", serialno='" + serialno + '\'' +
                ", orderPay=" + orderPay +
                ", orderTemp=" + orderTemp +
                ", orderCommList=" + orderCommList +
                ", orderLogList=" + orderLogList +
                ", orders=" + orders +
                ", orderNoParent='" + orderNoParent + '\'' +
                ", isParent=" + isParent +
                ", voucherDiscount=" + voucherDiscount +
                ", voucherDiscountI=" + voucherDiscountI +
                ", orderIntegral=" + orderIntegral +
                '}';
    }
}