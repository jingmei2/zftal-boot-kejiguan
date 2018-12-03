package com.zfsoft.boot.zhjx.dao.daointerface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;

@Mapper
public interface IWarningDao extends BaseDao<WarningModel>{

	/**
	 * 列表
	 * @param params
	 * @return
	 */
	List<WarningModel> getWarningList(Map<String,Object> params);
	
	/**
	 * 天预警统计
	 * @param params
	 * @return
	 */
	int getWarningDayCount(Map<String,Object> params);
	
	/**
	 * 月预警统计
	 * @param params
	 * @return
	 */
	int getWarningMonthCount(Map<String,Object> params);
	
	/**
	 * 年预警统计
	 * @param params
	 * @return
	 */
	int getWarningYearCount(Map<String,Object> params);
	
	/**
	 * 根据类型统计总数
	 * @param params
	 * @return
	 */
	int getSumByYjType(Map<String,Object> params);
	
	/**
	 * 插入预警数据
	 * @param warningModel
	 */
	int insertWarning(WarningModel warningModel);
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	List<WarningModel> getYhxwfxWarningList(Map<String,Object> params);
	
	Integer countByTime(@Param("username") String username,@Param("time") String time);
}
