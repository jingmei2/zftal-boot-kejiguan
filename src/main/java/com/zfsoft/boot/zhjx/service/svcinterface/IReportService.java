package com.zfsoft.boot.zhjx.service.svcinterface;

import java.util.List;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.ReportModel;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;

public interface IReportService extends BaseService<ReportModel>{
	
	/**
	 * 首页分析报告和舆情监控列表
	 * @param reportModel
	 * @return
	 */
	List<ReportModel> getReportList(ReportModel reportModel);
	
	/**
	 * 详情
	 * @param reportModel
	 * @return
	 */
	public ReportModel getDetail(ReportModel reportModel);
	
	Integer getWanguiCount(String startTime,String endTime,String institute);

	List<InstituteInfoModel> getInstituteList();

	void insertReport(ReportModel reportModel);

	List<WarningModel> getWanguiList(String daya, String time, String instituteId);

	List<WarningModel> getWanguiListByType(String type);
}
