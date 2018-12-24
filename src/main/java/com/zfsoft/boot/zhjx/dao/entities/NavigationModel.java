package com.zfsoft.boot.zhjx.dao.entities;

import com.zfsoft.boot.zhjx.util.UUIDUtil;
import io.swagger.annotations.ApiModel;


/**
 * 导航图实体类
 * @author Majing
 *
 */
@ApiModel(value = "导航图对象")
public class NavigationModel {
    private String id = UUIDUtil.getUUID().trim();
	private String picPath;
	private String desc;
	private String state;
    private String url;
	private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
//		this.id = id;
        this.id = (null == id ? UUIDUtil.getUUID() : id.trim());
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
