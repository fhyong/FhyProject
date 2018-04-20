package com.base.mvvm.feature.main.order;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.base.bean.ImageBindingBean;
import com.base.mvvm.R;
import com.base.util.img.ImageUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 描述: 订单信息中商品的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-01-05 14:58
 */

public class OrderCommListBean implements Serializable, ImageBindingBean {

    public int comId;
    public String orderNo;
    public String code;
    public String name;
    public String type;
    public String typeName;
    public double price;
    public double actPrice;
    public int count;
    public double subTotal;
    public Object isPackage;
    public String pictureUrl;
    public Object areaCode;
    public Object areaName;
    /**
     * 是否为预售商品：
     *      0：及时达商品
     *      1：预售商品
     */
    public int isReady;
    /**
     * 预售商品时间：
     *      null：及时达商品
     *      不为null：预售商品
     */
    public Long readyEndTime;
    public String standard;
    public Object sumCount;
    public Object sumTotal;
    public String categoryCode;
    public String categoryName;
    public int commoditySource;
    public int isGotten;
    public Object isGottenNum;
    public int isFree;
    public Object deliverTime;

    /******** 新加字段 *******/
    public Object secKillPrice;
    /**
     * 是否秒杀商品
     */
    public int isSecKill;
    public Object secKillNum;
    public Object secKillName;
    public Object secKillStTime;
    public Object secKillEndTime;
    public Object secKillDate;

    /**
     * 新加字段：如果已经评论过，则把评论的数据复值到以下属性
     */
    public List<String> commodityCommentPictures;
    public double commentGrade;
    public String commentContent;
    public String replyComment;


    /***** 新增字段：代表可以积分兑换，所需积分 ****/
    public Integer integralCount = 0;
    public Integer integral = 0;
    /***** 新增字段：代表是否是积分商品 ****/
    public boolean integralShop;

    @Override
    public String getImageUrl() {
        return pictureUrl;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageUtil.getInstance().loadImage(view, imageUrl, view.getContext().getResources()
                .getDrawable(R.drawable.ic_launcher), ImageUtil.TO_ROUND);
    }

    @Override
    public String toString() {
        return "OrderCommListBean{" +
                "comId=" + comId +
                ", orderNo='" + orderNo + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", price=" + price +
                ", actPrice=" + actPrice +
                ", count=" + count +
                ", subTotal=" + subTotal +
                ", isPackage=" + isPackage +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", areaCode=" + areaCode +
                ", areaName=" + areaName +
                ", isReady=" + isReady +
                ", readyEndTime=" + readyEndTime +
                ", standard=" + standard +
                ", sumCount=" + sumCount +
                ", sumTotal=" + sumTotal +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", commoditySource=" + commoditySource +
                ", isGotten=" + isGotten +
                ", isGottenNum=" + isGottenNum +
                ", isFree=" + isFree +
                ", deliverTime=" + deliverTime +
                ", secKillPrice=" + secKillPrice +
                ", isSecKill=" + isSecKill +
                ", secKillNum=" + secKillNum +
                ", secKillName=" + secKillName +
                ", secKillStTime=" + secKillStTime +
                ", secKillEndTime=" + secKillEndTime +
                ", secKillDate=" + secKillDate +
                ", commodityCommentPictures=" + commodityCommentPictures +
                ", commentGrade=" + commentGrade +
                ", commentContent='" + commentContent + '\'' +
                ", replyComment='" + replyComment + '\'' +
                ", integralCount=" + integralCount +
                ", integral=" + integral +
                ", integralShop=" + integralShop +
                '}';
    }

    /**
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
    */
}
