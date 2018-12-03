package com.zfsoft.boot.zhjx.web.vo;

/**
 * 腾讯移动分析应用分析-实时数据实体
 * @author liucb
 */
public class MtaSssjEntity {
    private String NewUser;
    private String ActiveUser;
    private String SessionCount;
    private String TotalUser;
    private String Qv;
    
	public String getNewUser() {
		return NewUser;
	}
	public String getActiveUser() {
		return ActiveUser;
	}
	public String getSessionCount() {
		return SessionCount;
	}
	public String getTotalUser() {
		return TotalUser;
	}
	public String getQv() {
		return Qv;
	}
	public void setNewUser(String newUser) {
		NewUser = newUser;
	}
	public void setActiveUser(String activeUser) {
		ActiveUser = activeUser;
	}
	public void setSessionCount(String sessionCount) {
		SessionCount = sessionCount;
	}
	public void setTotalUser(String totalUser) {
		TotalUser = totalUser;
	}
	public void setQv(String qv) {
		Qv = qv;
	}
}
