package com.yada.qrcode.payment.vspos.dao.proc;


import com.yada.qrcode.payment.vspos.dao.BaseDao;
import com.yada.qrcode.payment.vspos.entity.proc.Terminal;
import com.yada.qrcode.payment.vspos.entity.proc.TerminalPk;

import java.util.List;

/**
 * 终端信息Dao，继承 {@link BaseDao}
 *
 * @author quhan
 */
public interface TerminalDao extends BaseDao<Terminal, TerminalPk> {

    /**
     * 根据商户号以及终端号查找终端信息
     *
     * @param merNo  商户号
     * @param termNo 终端号
     * @return 终端信息
     */
    Terminal findTerminalByMerchantNoAndTermNo(String merNo, String termNo);

    /**
     * 根据位置信息ID查询对应的设备
     *
     * @param positionInfoId 位置信息ID
     * @return 对应的设备
     */
    List<Terminal> findTerminalsByPositionInfoId(String positionInfoId);
}
