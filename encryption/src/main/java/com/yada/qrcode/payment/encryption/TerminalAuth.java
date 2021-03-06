package com.yada.qrcode.payment.encryption;

import java.nio.ByteBuffer;

public class TerminalAuth {
	private String lmkTmk;
	private String lmkTak;
	private String lmkTpk;
	private IEncryption encryptionMachine;

	public TerminalAuth(IEncryption encryptionMachine) {
		this.encryptionMachine = encryptionMachine;
	}

	public void setTmk(String zmkTmk) {
		lmkTmk = encryptionMachine.getLmkTmk(zmkTmk);
	}
	public void setTmkByLmkTmk(String lmkTmk) {
		this.lmkTmk = lmkTmk;
	}

	public void setTak(String tmkTak) {
		lmkTak = encryptionMachine.getLmkTak(lmkTmk, tmkTak);
	}

	public void setTpk(String tmkTpk) {
		lmkTpk = encryptionMachine.getLmkTpk(lmkTmk, tmkTpk);
	}

	public String getPin(String accountNo, String pin) {
		return encryptionMachine.getTpkPin(accountNo, pin, lmkTpk);
	}

	public String pin2pin(String pinBlock){

		return null;
	}

	public ByteBuffer getMac(ByteBuffer macData) {
		return encryptionMachine.getTakMac(macData, lmkTak);
	}

	public String getLmkTmk() {
		return lmkTmk;
	}

	public String getLmkTak() {
		return lmkTak;
	}

	public String getLmkTpk() {
		return lmkTpk;
	}
}
