package com.zfsoft.boot.zhjx.dao.entities;

public class ScanPool {

	
	 //创建时间  
    private Long createTime = System.currentTimeMillis();  
    
    private String cacheToken;

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCacheToken() {
		return cacheToken;
	}

	public void setCacheToken(String cacheToken) {
		this.cacheToken = cacheToken;
	}
    
    
    
}
