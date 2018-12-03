package com.zfsoft.boot.zhjx.web.vo;

/**
 * 腾讯移动分析应用分析-活跃度数据实体
 * @author liucb
 */
public class MtaHydEntity {
	private String DayUv;
	private String WeekUv;
	private String MonthUv;
	
	public String getDayUv() {
		return DayUv;
	}
	public String getWeekUv() {
		return WeekUv;
	}
	public String getMonthUv() {
		return MonthUv;
	}
	public void setDayUv(String dayUv) {
		DayUv = dayUv;
	}
	public void setWeekUv(String weekUv) {
		WeekUv = weekUv;
	}
	public void setMonthUv(String monthUv) {
		MonthUv = monthUv;
	}
}
