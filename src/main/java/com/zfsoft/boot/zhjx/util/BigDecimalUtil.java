package com.zfsoft.boot.zhjx.util;

import java.text.DecimalFormat;

public class BigDecimalUtil {

	/**
	 * 保留两位小数
	 * @param val
	 * @return
	 */
	public static double getFormatDoubleVal(double val){
		DecimalFormat df = new DecimalFormat(".00");
	    return Double.valueOf(df.format(val));
	}
	
	
	public static void main(String[] args) {
		double val = 0.00;
		System.out.println(getFormatDoubleVal(val));
	}
}
