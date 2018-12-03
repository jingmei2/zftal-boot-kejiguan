package com.zfsoft.boot.zhjx.web.vo;

/**
 * 应用基础指标vo
 * @author liucb
 *
 */
public class TencentMtaVo {
    private String app_id;    //开发者应用ID
    private String start_date;//开始时间(Y-m-d)
    private String end_date;  //结束时间(Y-m-d)
    private String idx;       //指标列表
    private String version;   //应用某版本
    private String channel;   //应用某渠道
    private String sign;      //查询鉴权签名
    private String ty;        //终端类型（1：操作系统，2：分辨率，3：网络环境，4：运营商，5：设备型号）
    private String err_ty;    //1：App输出错误、2：App捕获异常、3：AppCrash、4：NativeCrash、5：SDK异常
    
	public String getErr_ty() {
		return err_ty;
	}
	public void setErr_ty(String err_ty) {
		this.err_ty = err_ty;
	}
	public String getTy() {
		return ty;
	}
	public void setTy(String ty) {
		this.ty = ty;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
