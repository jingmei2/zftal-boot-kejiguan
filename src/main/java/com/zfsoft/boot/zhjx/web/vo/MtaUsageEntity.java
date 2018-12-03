package com.zfsoft.boot.zhjx.web.vo;

public class MtaUsageEntity {

	private String OnlineTimePerUv;
	private String OnlineTimePerSession;
	private String PageCountPerUv;
	private String PageCountPerSession;
	
	public String getOnlineTimePerUv() {
		return OnlineTimePerUv;
	}
	public String getOnlineTimePerSession() {
		return OnlineTimePerSession;
	}
	public String getPageCountPerUv() {
		return PageCountPerUv;
	}
	public String getPageCountPerSession() {
		return PageCountPerSession;
	}
	public void setOnlineTimePerUv(String onlineTimePerUv) {
		OnlineTimePerUv = onlineTimePerUv;
	}
	public void setOnlineTimePerSession(String onlineTimePerSession) {
		OnlineTimePerSession = onlineTimePerSession;
	}
	public void setPageCountPerUv(String pageCountPerUv) {
		PageCountPerUv = pageCountPerUv;
	}
	public void setPageCountPerSession(String pageCountPerSession) {
		PageCountPerSession = pageCountPerSession;
	}
}
