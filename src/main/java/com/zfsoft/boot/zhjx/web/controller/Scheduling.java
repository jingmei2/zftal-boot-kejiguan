package com.zfsoft.boot.zhjx.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.ReportModel;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IReportService;

@Configuration
@EnableScheduling
public class Scheduling {
	
	@Autowired
	private IReportService reportService;
	
	//每周报告
	@Scheduled(cron = "0 1 0 ? * MON")
	//@Scheduled(cron = "0 13 15 * * ? ")
	public void createWorks() {
		//获取时间
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
       
        //统计数量
  		//总晚归人数
  		Integer total = reportService.getWanguiCount(daya, time, null);
		
		//生成全校报告
		Runnable run1 = new Runnable() {
			@Override
			public void run() {
				//sb.append("1+");
				StringBuilder sb1  = new StringBuilder("");
				ReportModel report = new ReportModel();
				report.setTitle(year + "年" + month + "月第" + week + "周晚归学生分析报告");
				report.setTime(time);
				report.setType("0");
				report.setInstituteId("admin");
				
				sb1.append(year + "年" + month + "月第" + week + "周共有" + total + "名晚归学生，其中</br>");
				//统计各学院数据
				List<InstituteInfoModel> list = reportService.getInstituteList();
				int count = 0;
				for (InstituteInfoModel instituteInfoModel : list) {
					count =  reportService.getWanguiCount(daya, time, instituteInfoModel.getInstituteId());
					sb1.append(instituteInfoModel.getInstituteName() + count + "人，</br>");
				}
				sb1.append("名单如下</br>");
				for (InstituteInfoModel instituteInfoModel : list) {
					sb1.append(instituteInfoModel.getInstituteName() + ":</br>");
					List<WarningModel> warns = reportService.getWanguiList(daya, time, instituteInfoModel.getInstituteId());
					for (WarningModel warningModel : warns) {
						System.out.println(warningModel.getUsername());
						String username = warningModel.getUsername();
						String name = "";
						if (StringUtils.isNoneBlank(warningModel.getName())) {
							name = warningModel.getName();
						}
						sb1.append(username);
						sb1.append("&nbsp;&nbsp;" + name + "&nbsp;&nbsp;" + warningModel.getTime() + "</br>");
					}
					sb1.append("</br>");
				}
				
				report.setContent(sb1.toString());
				reportService.insertReport(report);
				System.out.println("生成全校报告。。。");
			}
		};
		
		//生成各学院报告
		Runnable run2 = new Runnable() {
			
			@Override
			public void run() {
				List<InstituteInfoModel> list = reportService.getInstituteList();
				int count = 0;
				for (InstituteInfoModel instituteInfoModel : list) {
					StringBuilder sb1  = new StringBuilder("");
					ReportModel report = new ReportModel();
					report.setTitle(year + "年" + month + "月第" + week + "周" + instituteInfoModel.getInstituteName() + "晚归学生分析报告");
					report.setTime(time);
					report.setType("0");
					report.setInstituteId(instituteInfoModel.getInstituteId());
					count =  reportService.getWanguiCount(daya, time, instituteInfoModel.getInstituteId());
					sb1.append(year + "年" + month + "月第" + week + "周" + instituteInfoModel.getInstituteName() + "共有" + count + "名晚归学生，名单如下</br>");
					//学生名单
					List<WarningModel> warns = reportService.getWanguiList(daya, time, instituteInfoModel.getInstituteId());
					for (WarningModel warningModel : warns) {
						System.out.println(warningModel.getUsername());
						String username = warningModel.getUsername();
						String name = "";
						if (StringUtils.isNoneBlank(warningModel.getName())) {
							name = warningModel.getName();
						}
						sb1.append(username);
						sb1.append("&nbsp;&nbsp;" + name + "&nbsp;&nbsp;" + warningModel.getTime() + "</br>");
					}
					
					report.setContent(sb1.toString());
					reportService.insertReport(report);
					System.out.println("生成" + instituteInfoModel.getInstituteName() + "分析报告...");
				}
			}
		};
		
		run1.run();
		//run2.run();
		System.out.println("周分析报告生成结束" + new Date());
	}
	
	
	//每余月报告
	@Scheduled(cron = "0 1 0 1 * ?")
	public void createMonths() {
		//获取时间
		Calendar cale = null;  
        cale = Calendar.getInstance();  
        int year = cale.get(Calendar.YEAR);  
        int month = cale.get(Calendar.MONTH) + 1;  
  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        String time = format.format(new Date());

        
        String firstday, lastday;  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, -1);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        firstday = format.format(cale.getTime());  
        // 获取前月的最后一天  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 1);  
        cale.set(Calendar.DAY_OF_MONTH, 0);  
        lastday = format.format(cale.getTime());  
       
        //统计数量
  		//总晚归人数
  		Integer total = reportService.getWanguiCount(firstday, lastday, null);
		
		//生成全校报告
		Runnable run1 = new Runnable() {
			@Override
			public void run() {
				//sb.append("1+");
				StringBuilder sb1  = new StringBuilder("");
				ReportModel report = new ReportModel();
				report.setTitle(year + "年" + month + "月晚归学生分析报告");
				report.setTime(time);
				report.setType("0");
				report.setInstituteId("admin");
				
				sb1.append(year + "年" + month + "月共有" + total + "名晚归学生，其中</br>");
				//统计各学院数据
				List<InstituteInfoModel> list = reportService.getInstituteList();
				int count = 0;
				for (InstituteInfoModel instituteInfoModel : list) {
					count =  reportService.getWanguiCount(firstday, lastday, instituteInfoModel.getInstituteId());
					sb1.append(instituteInfoModel.getInstituteName() + count + "人，</br>");
				}
				sb1.append("名单如下</br>");
				for (InstituteInfoModel instituteInfoModel : list) {
					sb1.append(instituteInfoModel.getInstituteName() + ":</br>");
					List<WarningModel> warns = reportService.getWanguiList(firstday, lastday, instituteInfoModel.getInstituteId());
					for (WarningModel warningModel : warns) {
						System.out.println(warningModel.getUsername());
						String username = warningModel.getUsername();
						String name = "";
						if (StringUtils.isNoneBlank(warningModel.getName())) {
							name = warningModel.getName();
						}
						sb1.append(username);
						sb1.append("&nbsp;&nbsp;" + name + "&nbsp;&nbsp;" + warningModel.getTime() + "</br>");
					}
					sb1.append("</br>");
				}
				
				report.setContent(sb1.toString());
				reportService.insertReport(report);
				System.out.println("生成全校报告。。。");
			}
		};
		
		//生成各学院报告
		Runnable run2 = new Runnable() {
			
			@Override
			public void run() {
				List<InstituteInfoModel> list = reportService.getInstituteList();
				int count = 0;
				for (InstituteInfoModel instituteInfoModel : list) {
					StringBuilder sb1  = new StringBuilder("");
					ReportModel report = new ReportModel();
					report.setTitle(year + "年" + month + "月" + instituteInfoModel.getInstituteName() + "晚归学生分析报告");
					report.setTime(time);
					report.setType("0");
					report.setInstituteId(instituteInfoModel.getInstituteId());
					count =  reportService.getWanguiCount(firstday, lastday, instituteInfoModel.getInstituteId());
					sb1.append(year + "年" + month + "月" + instituteInfoModel.getInstituteName() + "共有" + count + "名晚归学生，名单如下</br>");
					//学生名单
					List<WarningModel> warns = reportService.getWanguiList(firstday, lastday, instituteInfoModel.getInstituteId());
					for (WarningModel warningModel : warns) {
						System.out.println(warningModel.getUsername());
						String username = warningModel.getUsername();
						String name = "";
						if (StringUtils.isNoneBlank(warningModel.getName())) {
							name = warningModel.getName();
						}
						sb1.append(username);
						sb1.append("&nbsp;&nbsp;" + name + "&nbsp;&nbsp;" + warningModel.getTime() + "</br>");
					}
					
					report.setContent(sb1.toString());
					reportService.insertReport(report);
					System.out.println("生成" + instituteInfoModel.getInstituteName() + "分析报告...");
				}
			}
		};
		
		run1.run();
		run2.run();
		System.out.println("月分析报告生成结束" + new Date());
	}
	
}