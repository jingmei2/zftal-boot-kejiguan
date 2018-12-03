package com.zfsoft.boot.zhjx.web.vo;

/**
 * 移动设备信息实体
 * @author liucb
 *
 */
public class MobileDeviceVo {
	private String userName;     //用户账号
	private String wifiIp;       //wifi的IP地址
	private String language;     //语言
	private String idfa;         //app唯一标识
	private String operaDesc;    //手机型号
	private String appVersion;   //app版本号
	private String resolution;   //分辨率
	private String systemType;   //app类别
	private String isRooted;     //是否获取root
	private String wifiName;     //wifi名称
	private String networkType;  //网络类型
	private String carrier;      //carrier运营商类别
	private String cellIp;       //公网ip
	private String regId;        //
	private String systemVersion;//系统版本号
	private String wifiList;     //wifi、mac地址
	private String phoneNumber;  //本手机号
	private String locInfo;      //经纬度
	private String lastLoginTime;//app端最后一次登陆时间
	
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWifiIp() {
		return wifiIp;
	}
	public void setWifiIp(String wifiIp) {
		this.wifiIp = wifiIp;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getOperaDesc() {
		return operaDesc;
	}
	public void setOperaDesc(String operaDesc) {
		this.operaDesc = operaDesc;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getIsRooted() {
		return isRooted;
	}
	public void setIsRooted(String isRooted) {
		this.isRooted = isRooted;
	}
	public String getWifiName() {
		return wifiName;
	}
	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getCellIp() {
		return cellIp;
	}
	public void setCellIp(String cellIp) {
		this.cellIp = cellIp;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getSystemVersion() {
		return systemVersion;
	}
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}
	public String getWifiList() {
		return wifiList;
	}
	public void setWifiList(String wifiList) {
		this.wifiList = wifiList;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocInfo() {
		return locInfo;
	}
	public void setLocInfo(String locInfo) {
		this.locInfo = locInfo;
	}
}
