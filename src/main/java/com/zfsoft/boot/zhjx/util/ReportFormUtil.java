package com.zfsoft.boot.zhjx.util;

/**
 * 报表统计类
 * xAxis x轴参数
 * yAxis y轴参数
 * @author Administrator
 *
 */
public class ReportFormUtil {
	/** X轴参数 */
	private String xAxis;
	/** Y轴参数1 */	
	private Integer yAxis;
	/** Y轴参数2 */	
	private Integer yAxis2;
	/** Y轴参数3 */	
	private Integer yAxis3;

	public ReportFormUtil(String xAxis, Integer yAxis, Integer yAxis2,
			Integer yAxis3) {
		super();
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.yAxis2 = yAxis2;
		this.yAxis3 = yAxis3;
	}

	public ReportFormUtil(String xAxis, Integer yAxis, Integer yAxis2) {
		super();
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.yAxis2 = yAxis2;
	}

	public ReportFormUtil(String xAxis, Integer yAxis) {
		super();
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public ReportFormUtil() {
		super();
	}

	public String getxAxis() {
		return xAxis;
	}

	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

	public Integer getyAxis() {
		return yAxis;
	}

	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}

	public Integer getyAxis2() {
		return yAxis2;
	}

	public void setyAxis2(Integer yAxis2) {
		this.yAxis2 = yAxis2;
	}

	public Integer getyAxis3() {
		return yAxis3;
	}

	public void setyAxis3(Integer yAxis3) {
		this.yAxis3 = yAxis3;
	}
	
}
