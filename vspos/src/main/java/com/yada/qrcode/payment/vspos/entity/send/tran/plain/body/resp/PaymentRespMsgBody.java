package com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * 消费交易响应数据
 *
 * @author quhan
 */
public class PaymentRespMsgBody extends AbstractTranRespMsgBody {

    /**
     * 支付方式
     */
    @JSONField(name = "PayType")
    private String payType;

    /**
     * 支付授权码  M M 从客户手机扫码获取的付款码
     */
    @JSONField(name = "AuthCode")
    private String authCode;

    /**
     * M 取值范围[000000000001,
     * 001000000000]
     * 表示取值范围从[0.01元,
     * 10000000.00元]
     * 交易金额
     */
    @JSONField(name = "TranAmt")
    private String tranAmt;

    /**
     * 外部商户订单号 C C
     * 请求:如前端系统存在此域则上送;
     * 回应:同请求
     */
    @JSONField(name = "MerOrderNo")
    private String merOrderNo;

    /**
     * 外部系统日期
     * C 外部系统完成交易的日期, 如外部系
     */
    @JSONField(name = "OutDate")
    private String outDate;

    /**
     * 统返回则存在此域
     * 外部系统时间
     * C 外部系统完成交易的时间, 如外部系
     */
    @JSONField(name = "OutTime")
    private String outTime;

    /**
     * 统返回则存在此域
     * 商户订单号
     * C 仅在支付宝和微信交易成功时返回此域
     */

    @JSONField(name = "OrderNo")
    private String orderNo;

    /**
     * 支付宝交易单号C
     * 仅PayType=ZFBA 时此域有值
     */
    @JSONField(name = "TradeNo")
    private String tradeNo;

    /**
     * 微信交易单号 C 微信返回的交易单号
     * 仅 PayType = WEIX
     * 时此域有值
     */
    @JSONField(name = "TradeId")
    private String tradeId;

    /**
     * 仅PayType=DZZF时此域有值
     */
    @JSONField(name = "OthTradeNo")
    private String othTradeNo;

    /**
     * 银联付款凭证号 C
     * 仅PayType=
     * UPAY 时此域有值
     */
    @JSONField(name = "PayVounum")
    private String payVounum;

    /**
     * 银联支付订单号  C 仅
     * PayType=
     * UPAY 时此域有值
     */
    @JSONField(name = "PayNo")
    private String payNo;

    /**
     * 支付有效时间，最短为1，最长为10，小于1分钟按1分钟计算，大于10分钟按10分钟计算
     */
    @JSONField(name = "PayValidTime")
    private String payValidTime;


    public PaymentRespMsgBody() {
    }

    public PaymentRespMsgBody(String merId, String termId) {
        super(merId, termId);
    }

    public PaymentRespMsgBody(String merId, String termId, String payLs, String traceNo, String batchNo, String respCode, String respMsg, String bankDate, String bankTime, String payType, String authCode, String tranAmt, String merOrderNo, String outDate, String outTime, String orderNo, String tradeNo, String tradeId, String othTradeNo, String payVounum, String payNo, String payValidTime) {
        super(merId, termId, payLs, traceNo, batchNo, respCode, respMsg, bankDate, bankTime);
        this.payType = payType;
        this.authCode = authCode;
        this.tranAmt = tranAmt;
        this.merOrderNo = merOrderNo;
        this.outDate = outDate;
        this.outTime = outTime;
        this.orderNo = orderNo;
        this.tradeNo = tradeNo;
        this.tradeId = tradeId;
        this.othTradeNo = othTradeNo;
        this.payVounum = payVounum;
        this.payNo = payNo;
        this.payValidTime = payValidTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getMerOrderNo() {
        return merOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getPayVounum() {
        return payVounum;
    }

    public void setPayVounum(String payVounum) {
        this.payVounum = payVounum;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getOthTradeNo() {
        return othTradeNo;
    }

    public void setOthTradeNo(String othTradeNo) {
        this.othTradeNo = othTradeNo;
    }

    public String getPayValidTime() {
        return payValidTime;
    }

    public void setPayValidTime(String payValidTime) {
        this.payValidTime = payValidTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PaymentRespMsgBody that = (PaymentRespMsgBody) o;
        return Objects.equals(payType, that.payType) &&
                Objects.equals(authCode, that.authCode) &&
                Objects.equals(tranAmt, that.tranAmt) &&
                Objects.equals(merOrderNo, that.merOrderNo) &&
                Objects.equals(outDate, that.outDate) &&
                Objects.equals(outTime, that.outTime) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(tradeNo, that.tradeNo) &&
                Objects.equals(tradeId, that.tradeId) &&
                Objects.equals(othTradeNo, that.othTradeNo) &&
                Objects.equals(payVounum, that.payVounum) &&
                Objects.equals(payNo, that.payNo) &&
                Objects.equals(payValidTime, that.payValidTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), payType, authCode, tranAmt, merOrderNo, outDate, outTime, orderNo, tradeNo, tradeId, othTradeNo, payVounum, payNo, payValidTime);
    }

    @Override
    public String toString() {
        return "PaymentRespMsgBody{" +
                "payType='" + payType + '\'' +
                ", authCode='" + authCode + '\'' +
                ", tranAmt='" + tranAmt + '\'' +
                ", merOrderNo='" + merOrderNo + '\'' +
                ", outDate='" + outDate + '\'' +
                ", outTime='" + outTime + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", tradeId='" + tradeId + '\'' +
                ", othTradeNo='" + othTradeNo + '\'' +
                ", payVounum='" + payVounum + '\'' +
                ", payNo='" + payNo + '\'' +
                ", payValidTime='" + payValidTime + '\'' +
                '}';
    }
}
