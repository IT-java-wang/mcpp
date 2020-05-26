package com.yada.qrcode.payment.vspos.spos;

import com.yada.qrcode.payment.common.sequence.sequence.SequenceGeneratorDbSequence;
import com.yada.qrcode.payment.common.sequence.sequence.SequenceGeneratorDbSequenceUtil;
import com.yada.qrcode.payment.vspos.dao.proc.TerminalDao;
import com.yada.qrcode.payment.vspos.dao.proc.TerminalPositionInfoDao;
import com.yada.qrcode.payment.vspos.entity.proc.SuperPos;
import com.yada.qrcode.payment.vspos.entity.proc.Terminal;
import com.yada.qrcode.payment.vspos.entity.proc.TerminalPk;
import com.yada.qrcode.payment.vspos.entity.proc.TerminalPositionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实现的存储密钥的管理类，其中保存（save）的方法都使用了新事务，当调用保存的方法时
 * 会新创建一个事务，跟原有的事务无关
 *
 * @author quhan
 */
@Service
public class DbSmartPosStorageImpl implements SmartPosStorage {

    @Autowired
    TerminalDao terminalDao;
    @Autowired
    TerminalPositionInfoDao terminalPositionInfoDao;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveLmkTmk(String vendorCode, String tuSn, String lmkTmk) {
        TerminalPk terminalPk = new TerminalPk(vendorCode, tuSn);
        Terminal terminal = this.terminalDao.findById(terminalPk).orElse(new Terminal(terminalPk));
        terminal.setLmkTmk(lmkTmk);

        terminal.setNeedParamDownload(false);
        this.terminalDao.saveAndFlush(terminal);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveLmkDek(String vendorCode, String tuSn, String lmkDek) {
        TerminalPk terminalPk = new TerminalPk(vendorCode, tuSn);
        Terminal terminal = this.terminalDao.findById(terminalPk).orElse(new Terminal(terminalPk));
        terminal.setLmkDek(lmkDek);
        this.terminalDao.saveAndFlush(terminal);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveSmartPosInfo(String vendorCode, String tuSn, SmartPosInfo smartPosInfo) {
        TerminalPk terminalPk = new TerminalPk(vendorCode, tuSn);
        Terminal terminal = this.terminalDao.findById(terminalPk).orElse(new Terminal(terminalPk));
        terminal.setMerchantNo(smartPosInfo.merchantNo);
        terminal.setTermNo(smartPosInfo.terminalNo);
        terminal.setLmkTmk(smartPosInfo.lmkTmk);
        terminal.setLmkDek(smartPosInfo.lmkDek);
        terminal.setLmkZek(smartPosInfo.zekInfo.lmkZek);
        terminal.setZekKeyInd(smartPosInfo.zekInfo.zekKeyInd);

        this.terminalDao.saveAndFlush(terminal);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveMerchantNoTerminalNoAndZek(String vendorCode, String tuSn, String merchantNo, String terminalNo,
                                               String lmkZek, String zekKeyInd) {

        Terminal oldTerminal = this.terminalDao.findTerminalByMerchantNoAndTermNo(merchantNo, terminalNo);

        if (oldTerminal != null) {
            if (!oldTerminal.getVendorCode().equals(vendorCode) && !oldTerminal.getSn().equals(tuSn)) {
                oldTerminal.setMerchantNo(null);
                oldTerminal.setTermNo(null);
                this.terminalDao.saveAndFlush(oldTerminal);
            }
        }

        TerminalPk terminalPk = new TerminalPk(vendorCode, tuSn);
        Terminal terminal = this.terminalDao.findById(terminalPk).orElse(new Terminal(terminalPk));
        terminal.setMerchantNo(merchantNo);
        terminal.setTermNo(terminalNo);
        terminal.setLmkZek(lmkZek);
        terminal.setZekKeyInd(zekKeyInd);
        terminal.setNeedSignIn(false);

        this.terminalDao.saveAndFlush(terminal);

        //当签到结束以后，更新位置表中的终端信息
        TerminalPositionInfo positionInfo = terminal.getPositionInfo();
        if (positionInfo != null) {
            positionInfo.setTermNo(terminalNo);
            positionInfo.setMerchantNo(merchantNo);
            positionInfo.setSn(tuSn);
            positionInfo.setVendorCode(vendorCode);
            terminalPositionInfoDao.saveAndFlush(positionInfo);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveLmkZek(String vendorCode, String tuSn, ZekInfo zekInfo) {
        TerminalPk terminalPk = new TerminalPk(vendorCode, tuSn);
        Terminal terminal = this.terminalDao.findById(terminalPk).orElse(new Terminal(terminalPk));
        terminal.setLmkZek(zekInfo.lmkZek);
        terminal.setZekKeyInd(zekInfo.zekKeyInd);
        this.terminalDao.saveAndFlush(terminal);
    }

    @Override
    public String getLmkTmk(String vendorCode, String tuSn) {
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        return terminal == null ? null : terminal.getLmkTmk();
    }

    @Override
    public String getLmkDek(String vendorCode, String tuSn) {
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        return terminal == null ? null : terminal.getLmkDek();
    }

    @Override
    public ZekInfo getLmkZek(String vendorCode, String tuSn) {
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        ZekInfo zekInfo = new ZekInfo();
        zekInfo.lmkZek = terminal == null ? null : terminal.getLmkZek();
        zekInfo.zekKeyInd = terminal == null ? null : terminal.getZekKeyInd();
        return zekInfo;
    }

    @Override
    public SmartPosInfo getSmartPosInfo(String vendorCode, String tuSn) {
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        SmartPosInfo smartPosInfo = new SmartPosInfo();
        if (terminal != null) {
            smartPosInfo.merchantNo = terminal.getMerchantNo();
            smartPosInfo.terminalNo = terminal.getTermNo();
            smartPosInfo.lmkDek = terminal.getLmkDek();
            smartPosInfo.lmkTmk = terminal.getLmkTmk();

            ZekInfo zekInfo = new ZekInfo();
            zekInfo.lmkZek = terminal.getLmkZek();
            zekInfo.zekKeyInd = terminal.getZekKeyInd();

            smartPosInfo.zekInfo = zekInfo;

            return smartPosInfo;
        } else {
            return smartPosInfo;
        }
    }

    @Override
    public SuperPos getSuperPos(String vendorCode, String tuSn) {
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        return terminal == null ? null : terminal.getSuperPos();
    }

    @Override
    public boolean checkNeedSignIn(String vendorCode, String tuSn) {
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        if (terminal == null) {
            return true;
        } else {
            return terminal.getNeedSignIn() == null || terminal.getNeedSignIn();
        }
    }

    @Override
    public boolean checkNeedParamDownload(String vendorCode, String tuSn) {
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        if (terminal == null) {
            return true;
        } else {
            return terminal.getNeedParamDownload() == null || terminal.getNeedParamDownload();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveNeedSignInStatus(String vendorCode, String tuSn, boolean needSignIn) {
        TerminalPk terminalPk = new TerminalPk(vendorCode, tuSn);
        Terminal terminal = this.terminalDao.findById(terminalPk).orElse(new Terminal(terminalPk));

        if (terminal.getNeedSignIn() == null || needSignIn != terminal.getNeedSignIn()) {
            terminal.setNeedSignIn(needSignIn);
            this.terminalDao.saveAndFlush(terminal);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveNeedParamDownload(String vendorCode, String tuSn, boolean needParamDownload) {
        TerminalPk terminalPk = new TerminalPk(vendorCode, tuSn);
        Terminal terminal = this.terminalDao.findById(terminalPk).orElse(new Terminal(terminalPk));

        if (terminal.getNeedParamDownload() == null || needParamDownload != terminal.getNeedParamDownload()) {
            terminal.setNeedParamDownload(needParamDownload);
            this.terminalDao.saveAndFlush(terminal);
        }
    }

    @Override
    public void saveNeedPositionInfoUpload(String vendorCode, String tuSn, boolean needUpload) {
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        if (terminal == null) {
            throw new RuntimeException("保存需要上传位置信息参数时获取终端为空");
        }
        TerminalPositionInfo positionInfo = terminal.getPositionInfo();
        positionInfo.setNeedUpload(needUpload);
        positionInfo.setMerchantNo(terminal.getMerchantNo());
        positionInfo.setTermNo(terminal.getTermNo());
        terminalPositionInfoDao.saveAndFlush(positionInfo);
    }

    @Override
    public Terminal getAllTerminalInfo(String vendorCode, String tuSn) {
        return this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public String getBatchNo(String vendorCode, String tuSn) {
        String strBatchNo;
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        assert terminal != null;
        if (terminal.getBatchNo() == null) {
            SequenceGeneratorDbSequence sequenceGeneratorDbSequence = SequenceGeneratorDbSequenceUtil.
                    createSequenceGeneratorDb("batch_" + terminal.getMerchantNo() + terminal.getTermNo());
            int batchNo = sequenceGeneratorDbSequence.getSequence();
            strBatchNo = String.format("%06d", batchNo);
            terminal.setBatchNo(strBatchNo);
            terminalDao.saveAndFlush(terminal);
        } else {
            strBatchNo = terminal.getBatchNo();
        }

        return strBatchNo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public String updateBatchNo(String vendorCode, String tuSn) {
        Terminal terminal = this.terminalDao.findById(new TerminalPk(vendorCode, tuSn)).orElse(null);
        assert terminal != null;
        SequenceGeneratorDbSequence sequenceGeneratorDbSequence = SequenceGeneratorDbSequenceUtil.
                createSequenceGeneratorDb("batch_" + terminal.getMerchantNo() + terminal.getTermNo());

        int batchNo = sequenceGeneratorDbSequence.getSequence();
        String strBatchNo = String.format("%06d", batchNo);
        terminal.setBatchNo(strBatchNo);
        terminalDao.saveAndFlush(terminal);
        return strBatchNo;
    }
}
