package com.base.mvvm.feature.main.order;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 描述: 订单信息实体类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-01-05 14:50
 */

public class OrderInfoBean implements MultiItemEntity, Comparable<OrderInfoBean>, Serializable {

    public Object orderId;
    public String orderNo;
    public int type;
    public String customer;
    public Object customerCode;
    public Object repCustomer;
    public Object repCustomerCode;
    public String receiver;
    public Object receiverCode;
    public String phone;
    public Object zone;
    public Object cityCode;
    public Object cityName;
    public Object addr;
    public int carriage;
    public Object deliverTime;
    public Object modifier;
    public Object modifierCode;
    public Object modifyTime;
    public String site;
    public String siteCode;
    /**
     * 是否需要账单发票
     */
    public int isBill;
    public Object billType;
    public Object billTitle;
    public Object billContent;
    public Object userComments;
    public BigDecimal orderAmount;
    public double comAmount;
    public int discount;
    public int status;
    public long createTime;
    public Object sign;
    public String verification;
    public Object resource;
    public Object sellDiscount;
    public Object fullDiscount;
    public Object ylcardDiscount;
    public Object sellDiscountId;
    public Object fullDiscountId;
    public Object sendAddr;
    public int sendType;
    public Object sendReceiver;
    public Object sendPhone;
    public Object sendTimeName;
    public Object sendTimeValue;
    public Long sendDate;
    public Object serialno;
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
     * 新增字段：订单积分金额
     */
    public Integer orderIntegral;

    @Override
    public String toString() {
        return "OrderInfoBean{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", type=" + type +
                ", customer='" + customer + '\'' +
                ", customerCode=" + customerCode +
                ", repCustomer=" + repCustomer +
                ", repCustomerCode=" + repCustomerCode +
                ", receiver='" + receiver + '\'' +
                ", receiverCode=" + receiverCode +
                ", phone='" + phone + '\'' +
                ", zone=" + zone +
                ", cityCode=" + cityCode +
                ", cityName=" + cityName +
                ", addr=" + addr +
                ", carriage=" + carriage +
                ", deliverTime=" + deliverTime +
                ", modifier=" + modifier +
                ", modifierCode=" + modifierCode +
                ", modifyTime=" + modifyTime +
                ", site='" + site + '\'' +
                ", siteCode='" + siteCode + '\'' +
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
                ", sendTimeName=" + sendTimeName +
                ", sendTimeValue=" + sendTimeValue +
                ", sendDate=" + sendDate +
                ", serialno=" + serialno +
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

    @Override
    public int getItemType() {
       return 0;
    }

    @Override
    public int compareTo(@NonNull OrderInfoBean o) {
        if(orderNoParent != null) {
            if(o.orderNoParent != null) {
                int isSameParent = orderNoParent.compareTo(o.orderNoParent);
                if(isSameParent == 0) {
                    if(orderNo != null) {
                        if(o.orderNo != null) {
                            return orderNo.compareTo(o.orderNo);
                        } else {
                            return 0;
                        }
                    } else {
                        return 1;
                    }
                } else {
                    return isSameParent;
                }
            } else {
                return 0;
            }

        }
        return 1;
    }

/**
       {
        "orderId":null,
        "orderNo":"32836459462656986",
        "type":0,
        "customer":"用户:3986",
        "customerCode":null,
        "repCustomer":null,
        "repCustomerCode":null,
        "receiver":"张萌萌",
        "receiverCode":null,
        "phone":null,
        "zone":null,
        "cityCode":null,
        "cityName":null,
        "addr":null,
        "carriage":0,
        "deliverTime":null,
        "modifier":null,
        "modifierCode":null,
        "modifyTime":null,
        "site":null,
        "siteCode":null,
        "isBill":1,
        "billType":null,
        "billTitle":null,
        "billContent":null,
        "userComments":null,
        "orderAmount":0.01,
        "comAmount":0.01,
        "discount":0,
        "status":1,
        "createTime":1514515289000,
        "sign":null,
        "verification":"827836",
        "resource":null,
        "sellDiscount":null,
        "fullDiscount":null,
        "ylcardDiscount":null,
        "sellDiscountId":null,
        "fullDiscountId":null,
        "sendAddr":null,
        "sendType":0,
        "sendReceiver":null,
        "sendPhone":null,
        "sendTimeName":null,
        "sendTimeValue":null,
        "sendDate":null,
        "serialno":null,
        "orderCommList": [
        {
            "comId":164116,
                "orderNo":"32836459462656986",
                "code":"9520000001359",
                "name":"特级盐焗味碧根果  250g/袋",
                "type":"0A006005",
                "typeName":"碧根果",
                "price":0.01,
                "actPrice":0.01,
                "count":1,
                "subTotal":0.01,
                "isPackage":null,
                "pictureUrl":
            "http://image.ylfood.com/group1/M00/00/DC/rBLsjlmtB1GAeEFLAARjAG2p7-s045.jpg",
                    "areaCode":null,
                "areaName":null,
                "isReady":0,
                "readyEndTime":null,
                "standard":null,
                "sumCount":null,
                "sumTotal":null,
                "categoryCode":"0A006005",
                "categoryName":"碧根果",
                "commoditySource":0,
                "isGotten":0,
                "isGottenNum":null,
                "isFree":0,
                "deliverTime":null,
                "commentContent":null,
                "replyComment":null
        }
        ],
        "orderLogList": [],
        "orderPay":null,
        "orderTemp":null
     }
    */
}
