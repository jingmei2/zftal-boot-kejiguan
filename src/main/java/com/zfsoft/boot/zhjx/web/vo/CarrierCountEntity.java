package com.zfsoft.boot.zhjx.web.vo;

/**
 * 设备运营商统计实体
 * @author liucb
 *
 */
public class CarrierCountEntity {
	private String carrierName; //运营商名字
	private long amount;  //数量
	
	public String getCarrierName() {
		return carrierName;
	}
	public long getAmount() {
		return amount;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
}
