package com.yada.qrcode.payment.vspos.config;

import com.yada.qrcode.payment.vspos.dao.proc.AuthCodeTranTypeDao;
import com.yada.qrcode.payment.vspos.spos.CheckTranType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 给 {@link CheckTranType} 类注入 {@link AuthCodeTranTypeDao} ,并初始化
 *
 * @author quhan
 */
@Component
public class CheckTranTypeConfig implements ApplicationRunner {

    @Autowired
    AuthCodeTranTypeDao authCodeTranTypeDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CheckTranType.setAuthCodeTranTypeDao(authCodeTranTypeDao);
    }
}
