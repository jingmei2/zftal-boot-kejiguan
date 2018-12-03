package com.zfsoft.boot.zhjx.dao.entities;

import java.util.List;

public class LeftMenuModel {
	private String fjgndm;
	private String sfgnym;
	private	String dyym;
	private	String gnmkdm;
	private	String tblj;
	private	String gnmkmc;
	private String xssx;
	private List<LeftMenuModel> children;
	
	public String getFjgndm() {
		return fjgndm;
	}
	public void setFjgndm(String fjgndm) {
		this.fjgndm = fjgndm;
	}
	public String getSfgnym() {
		return sfgnym;
	}
	public void setSfgnym(String sfgnym) {
		this.sfgnym = sfgnym;
	}
	public String getDyym() {
		return dyym;
	}
	public void setDyym(String dyym) {
		this.dyym = dyym;
	}
	public String getGnmkdm() {
		return gnmkdm;
	}
	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}
	public String getTblj() {
		return tblj;
	}
	public void setTblj(String tblj) {
		this.tblj = tblj;
	}
	public String getGnmkmc() {
		return gnmkmc;
	}
	public void setGnmkmc(String gnmkmc) {
		this.gnmkmc = gnmkmc;
	}
	public List<LeftMenuModel> getChildren() {
		return children;
	}
	public void setChildren(List<LeftMenuModel> children) {
		this.children = children;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	
}
