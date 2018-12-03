package com.zfsoft.boot.zhjx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.IWarningDao;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IWarningService;

@Service
public class WarningServiceImpl  extends BaseServiceImpl<WarningModel, IWarningDao> implements IWarningService {
	
	@Autowired
	protected IWarningDao warningDao;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	public List<WarningModel> getWarningList(Map<String,Object> params){
		return warningDao.getWarningList(params);
	}
	
	/**
	 * 天预警统计
	 * @param params
	 * @return
	 */
	public int getWarningDayCount(Map<String,Object> params){
		return warningDao.getWarningDayCount(params);
	}
	
	/**
	 * 月预警统计
	 * @param params
	 * @return
	 */
	public int getWarningMonthCount(Map<String,Object> params){
		return warningDao.getWarningMonthCount(params);
	}
	
	/**
	 * 年预警统计
	 * @param params
	 * @return
	 */
	public int getWarningYearCount(Map<String,Object> params){
		return warningDao.getWarningYearCount(params);
	}
	
	/**
	 * 根据类型统计总数
	 * @param params
	 * @return
	 */
	public int getSumByYjType(Map<String,Object> params){
		return warningDao.getSumByYjType(params);
	}
	
	/**
	 * 插入预警数据
	 * @param warningModel
	 */
	public int insertWarning(WarningModel warningModel) {
		return warningDao.insertWarning(warningModel);
	}

	@Override
	public List<WarningModel> getYhxwfxWarningList(Map<String, Object> params) {
		return warningDao.getYhxwfxWarningList(params);
	}

	@Override
	public Integer countByTime(String username, String time) {
		return warningDao.countByTime(username, time);
	}
}
