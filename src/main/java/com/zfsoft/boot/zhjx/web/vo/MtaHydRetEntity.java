package com.zfsoft.boot.zhjx.web.vo;

import java.util.Map;

/**
 * 腾讯移动分析应用分析-活跃度数据返回实体
 * @author liucb
 */
public class MtaHydRetEntity {
	private String ret_code;
	private String ret_msg;
	private Map<String,MtaHydEntity> ret_data;
	
	public String getRet_code() {
		return ret_code;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public Map<String, MtaHydEntity> getRet_data() {
		return ret_data;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	public void setRet_data(Map<String, MtaHydEntity> ret_data) {
		this.ret_data = ret_data;
	}
}
