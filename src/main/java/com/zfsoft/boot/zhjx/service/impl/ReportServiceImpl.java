package com.zfsoft.boot.zhjx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.IReportDao;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.ReportModel;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IReportService;

@Service
public class ReportServiceImpl  extends BaseServiceImpl<ReportModel, IReportDao> implements IReportService{
	@Autowired
	protected IReportDao reportDao;
	
	/**
	 * 首页分析报告和舆情监控列表
	 * @param reportModel
	 * @return
	 */
	public List<ReportModel> getReportList(ReportModel reportModel){
		return reportDao.getReportList(reportModel);
	}
	
	/**
	 * 详情
	 * @param reportModel
	 * @return
	 */
	public ReportModel getDetail(ReportModel reportModel) {
		return reportDao.getDetail(reportModel);
	}

	@Override
	public Integer getWanguiCount(String startTime, String endTime, String institute) {
		return reportDao.getWanguiCount(startTime,endTime,institute);
	}

	@Override
	public List<InstituteInfoModel> getInstituteList() {
		return reportDao.getInstituteList();
	}

	@Override
	public void insertReport(ReportModel reportModel) {
		reportDao.insertReport(reportModel);
	}

	@Override
	public List<WarningModel> getWanguiList(String startTime, String endTime, String institute) {
		return reportDao.getWanguiList(startTime,endTime,institute);
	}

	@Override
	public List<WarningModel> getWanguiListByType(String type) {
		return reportDao.getWanguiListByType(type);
	}
}
