package com.zfsoft.boot.zhjx.dao.entities;

/**
 * controller返回数据封装实体
 * @author liucb
 *
 */
public class ResultEntity {
	public static final int SUCCESS_CODE=1;
	public static final int ERROR_CODE=0;
	public static final String SUCCESS_STATUS="success";
	public static final String ERROR_STATUS="error";
	
	private int code;  //状态码
	private String status;//信息
	private Object data;  //返回数据
	
	public ResultEntity() {}
	
	public ResultEntity(int code, String status, Object data) {
		super();
		this.code = code;
		this.status = status;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
