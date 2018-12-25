package com.zfsoft.boot.zhjx.dao.entities;

import com.zfsoft.boot.zhjx.util.UUIDUtil;
import io.swagger.annotations.ApiModel;


/**
 * 场馆实体类
 * @author Majing
 *
 */
@ApiModel(value = "场馆对象")
public class VenueModel {
    private String id ;
	private String titles;
	private String picPath;
	private String desc;
	private String voicePath;
	private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
//		this.id = id;
        this.id = (null == id ? UUIDUtil.getUUID() : id.trim());
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
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

    public String getVoicePath() {
        return voicePath;
    }

    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
