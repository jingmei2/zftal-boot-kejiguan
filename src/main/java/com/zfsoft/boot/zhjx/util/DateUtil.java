package com.zfsoft.boot.zhjx.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static void main(String[] args) {
		System.out.println(DateUtil.getPastDate(30));
		System.out.println(DateUtil.getPastMonth(1));
		System.out.println(DateUtil.getPastYear(1));
		System.out.println(DateUtil.getCuttentHours());
	}
	
	
   /**
     * 获取过去第几天的日期(- 操作) 或者 未来 第几天的日期( + 操作)
     * @param past
     * @return
     */
     public static String getPastDate(int past) {
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
         Date pastDay = calendar.getTime();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         String result = format.format(pastDay);
         return result;
     }
     
     /**
      * 获取过去第几个月份字符串(- 操作) 或者 未来 第几天的日期( + 操作)
      * @param past
      * @return
      */
     public static String getPastMonth(int past) {
    	 Calendar calendar = Calendar.getInstance();
    	 calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-past);
    	 Date pastDay = calendar.getTime();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
         String result = format.format(pastDay);
    	 return result;
     }
     
     /**
      * 获取过去第几个年份字符串(- 操作) 或者 未来 第几天的日期( + 操作)
      * @param past
      * @return
      */
     public static String getPastYear(int past) {
    	 Calendar calendar = Calendar.getInstance();
    	 calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-past);
    	 Date pastDay = calendar.getTime();
         SimpleDateFormat format = new SimpleDateFormat("yyyy");
         String result = format.format(pastDay);
    	 return result;
     }
     
     /**
      * 格式化日期到日期
      * @param date
      * @return
      */
     public static String fDate(Date date) {
    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	 return format.format(date);
     }
     
     /**
      * 格式化日期到秒
      * @param date
      * @return
      */
     public static String fSecond(Date date) {
    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 return format.format(date);
     }
     
     /**
      * 获取当前所在小时
      */
     public static int getCuttentHours() {
    	 return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
     }
     
     public static boolean compTime(String s1,String s2){  
         try {  
             if (s1.indexOf(":")<0||s1.indexOf(":")<0) {  
                 System.out.println("格式不正确");  
             }else{  
                 String[]array1 = s1.split(":");  
                 int total1 = Integer.valueOf(array1[0])*3600+Integer.valueOf(array1[1])*60;  
                 String[]array2 = s2.split(":");  
                 int total2 = Integer.valueOf(array2[0])*3600+Integer.valueOf(array2[1])*60;  
                 return total1-total2>0?true:false;  
             }  
         } catch (NumberFormatException e) {  
             // TODO Auto-generated catch block  
             return true;  
         }  
         return false;  
   
     }  
     
}
