package com.zfsoft.boot.zhjx.dao.entities;

import com.zfsoft.boot.zhjx.util.UUIDUtil;


/**
 * 活动实体类
 * @author liucb
 *
 */
public class ActivityModel {
	private String id = UUIDUtil.getUUID().trim();
	private String title;
	private String picPath;
	private String desc;
	private String number;
	private String createTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
//		this.id = id;
		this.id = (null == id ? UUIDUtil.getUUID() : id.trim());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
