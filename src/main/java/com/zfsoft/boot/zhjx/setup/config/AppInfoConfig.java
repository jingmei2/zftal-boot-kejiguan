package com.zfsoft.boot.zhjx.setup.config;

import org.springframework.stereotype.Component;

@Component
public class AppInfoConfig {
    private String androidAppId = "3103363952";
    private String androidAppKey = "AA5Y37ME5CMK";
    private String iosAppId = "3203363952";
    private String iosAppKey = "IWUP75IP28VN";

	public String getAndroidAppId() {
		return androidAppId;
	}
	public String getAndroidAppKey() {
		return androidAppKey;
	}
	public String getIosAppId() {
		return iosAppId;
	}
	public String getIosAppKey() {
		return iosAppKey;
	}
	public void setAndroidAppId(String androidAppId) {
		this.androidAppId = androidAppId;
	}
	public void setAndroidAppKey(String androidAppKey) {
		this.androidAppKey = androidAppKey;
	}
	public void setIosAppId(String iosAppId) {
		this.iosAppId = iosAppId;
	}
	public void setIosAppKey(String iosAppKey) {
		this.iosAppKey = iosAppKey;
	}
}
