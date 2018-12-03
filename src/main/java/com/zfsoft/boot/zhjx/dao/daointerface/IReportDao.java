package com.zfsoft.boot.zhjx.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.ReportModel;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;

@Mapper
public interface IReportDao extends BaseDao<ReportModel>{

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

	Integer getWanguiCount(@Param("startTime")String startTime,@Param("endTime") String endTime,@Param("institute") String institute);
	
	List<InstituteInfoModel> getInstituteList();
	
	void insertReport(ReportModel reportModel);

	List<WarningModel> getWanguiList(@Param("startTime")String startTime,@Param("endTime") String endTime,@Param("institute") String institute);

	List<WarningModel> getWanguiListByType(@Param("type")String type);
}
