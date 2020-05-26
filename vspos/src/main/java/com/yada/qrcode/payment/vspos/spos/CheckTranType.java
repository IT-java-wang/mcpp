package com.yada.qrcode.payment.vspos.spos;

import com.yada.qrcode.payment.vspos.dao.proc.AuthCodeTranTypeDao;
import com.yada.qrcode.payment.vspos.entity.proc.AuthCodeTranType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author quhan
 */
@Repository
public class CheckTranType {

    private static AuthCodeTranTypeDao authCodeTranTypeDao;

    /**
     * 授权码标识：交易类型
     * 的存储
     */
    private static Map<String, String> MAP = new HashMap<>();

    public static void setAuthCodeTranTypeDao(AuthCodeTranTypeDao authCodeTranTypeDao) {
        CheckTranType.authCodeTranTypeDao = authCodeTranTypeDao;
        List<AuthCodeTranType> allAuthCodeTranType = CheckTranType.authCodeTranTypeDao.findAll();

        for (AuthCodeTranType authCodeTranType : allAuthCodeTranType) {
            MAP.put(authCodeTranType.getCodeMark(), authCodeTranType.getTranType());
        }
    }

    /**
     * 可以通过交易授权码获取交易类型,如果未找到授权码对应的付款类型，则会返回 null
     *
     * @param authCode 校验授权码
     * @return 交易类型
     */
    public static PayTypeEnum checkPayType(String authCode) {
        authCode = authCode.substring(0, 2);
        String strPayType = MAP.get(authCode);
        if (strPayType == null) {
            return null;
        }
        if (PayTypeEnum.WEIX.getValue().equals(strPayType)) {
            return PayTypeEnum.WEIX;
        }
        if (PayTypeEnum.ZFBA.getValue().equals(strPayType)) {
            return PayTypeEnum.ZFBA;
        }
        if (PayTypeEnum.DZZF.getValue().equals(strPayType)) {
            return PayTypeEnum.DZZF;
        }
        if (PayTypeEnum.MPZF.getValue().equals(strPayType)) {
            return PayTypeEnum.MPZF;
        }
        if (PayTypeEnum.UPAY.getValue().equals(strPayType)) {
            return PayTypeEnum.UPAY;
        }

        return null;
    }

}
