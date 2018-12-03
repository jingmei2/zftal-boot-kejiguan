package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.zfsoft.boot.zhjx.dao.entities.PoolCache;
import com.zfsoft.boot.zhjx.dao.entities.ScanPool;
import com.zfsoft.boot.zhjx.util.DateUtil;
import com.zfsoft.boot.zhjx.util.HttpUntil;
import com.zfsoft.boot.zhjx.util.Point;
import com.zfsoft.boot.zhjx.util.WXUtil;

import net.sf.json.JSONObject;

public class TestSome {

	@Test
	public void getAccess_token() throws ParseException {
		
		Calendar cale = null;  
        cale = Calendar.getInstance();  
        int year = cale.get(Calendar.YEAR);  
        int month = cale.get(Calendar.MONTH) + 1;  
        int day = cale.get(Calendar.DATE);  
        int week = cale.get(Calendar.WEEK_OF_MONTH);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        String time = format.format(new Date());
        
        cale.setTime(new Date());
        cale.add(Calendar.DATE, - 7);
        Date d = cale.getTime();
        String daya = format.format(d);
        System.out.println(time);
		
		/*Calendar cale = null;  
        cale = Calendar.getInstance();  
        int year = cale.get(Calendar.YEAR);  
        int month = cale.get(Calendar.MONTH) + 1;  
        int day = cale.get(Calendar.DATE);  
        int hour = cale.get(Calendar.HOUR_OF_DAY);  
        int minute = cale.get(Calendar.MINUTE);  
        int second = cale.get(Calendar.SECOND);  
        int dow = cale.get(Calendar.DAY_OF_WEEK);  
        int dom = cale.get(Calendar.DAY_OF_MONTH);  
        int doy = cale.get(Calendar.DAY_OF_YEAR);  
        int week = cale.get(Calendar.WEEK_OF_MONTH);
  
        System.out.println("Current Date: " + cale.getTime());  
        System.out.println("Year: " + year);  
        System.out.println("Month: " + month);  
        System.out.println("Day: " + day);  
        System.out.println("Hour: " + hour);  
        System.out.println("Minute: " + minute);  
        System.out.println("Second: " + second);  
        System.out.println("Day of Week: " + dow);  
        System.out.println("Day of Month: " + dom);  
        System.out.println("Day of Year: " + doy);  
        System.out.println("week of month: " + week);  
        System.out.println(year + "年" + month + "月第" + week + "周晚归学生分析报告");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        firstday = format.format(cale.getTime());  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 1);  
        cale.set(Calendar.DAY_OF_MONTH, 0);  
        lastday = format.format(cale.getTime());  
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " , " + lastday); 
        
        
        cale.setTime(new Date());
        cale.add(Calendar.DATE, - 7);
        Date d = cale.getTime();
        String daya = format.format(d);
        cale.setTime(new Date());
        cale.add(Calendar.DATE, +1);
        d = cale.getTime();
        System.out.println("过去七天："+daya);
        System.out.println("后一天：" + format.format(d));
        */
	}
	
}
