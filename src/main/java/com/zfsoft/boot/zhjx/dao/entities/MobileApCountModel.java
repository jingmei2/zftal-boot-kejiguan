package com.zfsoft.boot.zhjx.dao.entities;

/**
 * 网络ap统计实体
 * @author liucb
 */
public class MobileApCountModel {
    private String wifiName; //wifi名称
    private String useTimes;//使用次数
    
	public String getWifiName() {
		return wifiName;
	}
	public String getUseTimes() {
		return useTimes;
	}
	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}
	public void setUseTimes(String useTimes) {
		this.useTimes = useTimes;
	}
}
