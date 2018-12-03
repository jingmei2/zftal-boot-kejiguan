package com.zfsoft.boot.zhjx.dao.entities;

/**
 * 网络ap实体
 * @author liucb
 */
public class MobileApModel {
    private String userName; //用户账号
    private String wifiIp;   //wifi的IP地址
    private String wifiName; //wifi名称
    private String networkType;//网络类型
    private String cellIp;   //公网ip
    private String wifiList; //wifi mac地址
    private String createTime; //创建时间
    
	public String getUserName() {
		return userName;
	}
	public String getWifiIp() {
		return wifiIp;
	}
	public String getWifiName() {
		return wifiName;
	}
	public String getNetworkType() {
		return networkType;
	}
	public String getCellIp() {
		return cellIp;
	}
	public String getWifiList() {
		return wifiList;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setWifiIp(String wifiIp) {
		this.wifiIp = wifiIp;
	}
	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public void setCellIp(String cellIp) {
		this.cellIp = cellIp;
	}
	public void setWifiList(String wifiList) {
		this.wifiList = wifiList;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
