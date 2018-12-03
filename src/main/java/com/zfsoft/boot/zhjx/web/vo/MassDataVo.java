package com.zfsoft.boot.zhjx.web.vo;

/**
 * 高德地图海量点实体
 * @author liucb
 *
 */
public class MassDataVo {
    private String lnglat; //[109.831765, 27.201876]
    private String name;
    private String style;
    
    public MassDataVo(){
    	
    }
    
	public MassDataVo(String lnglat, String name, String style) {
		super();
		this.lnglat = lnglat;
		this.name = name;
		this.style = style;
	}
	
	public String getLnglat() {
		return lnglat;
	}
	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
}
