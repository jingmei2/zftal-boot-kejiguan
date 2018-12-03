package com.zfsoft.boot.zhjx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.IDatatableDao;
import com.zfsoft.boot.zhjx.dao.daointerface.IMobileDeviceDao;
import com.zfsoft.boot.zhjx.dao.entities.CarrierCountModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileDeviceModel;
import com.zfsoft.boot.zhjx.dao.entities.NationModel;
import com.zfsoft.boot.zhjx.dao.entities.PolygonModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileDeviceService;

@Service
public class MobileDeviceServiceImpl  extends BaseServiceImpl<MobileDeviceModel, IMobileDeviceDao> implements IMobileDeviceService{
	@Autowired
	protected IMobileDeviceDao mobileDeviceDao;
	
	/**
	 * 检查是否存在该条记录
	 * @param mobileDeviceModel
	 * @return
	 */
	public MobileDeviceModel checkDeviceInfo(MobileDeviceModel mobileDeviceModel) {
		return mobileDeviceDao.checkDeviceInfo(mobileDeviceModel);
	}
	
	/**
	 * 插入高德地图多边形经纬度数据
	 * @param polygonModel
	 * @return
	 */
	public boolean insertPolygon(PolygonModel polygonModel) {
		int rows = mobileDeviceDao.insertPolygon(polygonModel);
		return rows>0;
	}
	
	/**
	 * 获取高德地图所有多边形经纬度
	 * @return
	 */
	public List<PolygonModel> getPolygonList(PolygonModel polygonModel){
		List<PolygonModel> polygonList = mobileDeviceDao.getPolygonList(polygonModel);
		return polygonList;
	}
	
	/**
	 * 
	 * @param mobileDeviceModel
	 */
	public PolygonModel getPolygonById(PolygonModel polygonModel) {
		PolygonModel polygonModelQueryed = mobileDeviceDao.getPolygonById(polygonModel);
		return polygonModelQueryed;
	}
	
	/**
	 * 修改区域
	 * @param polygonModel
	 * @return
	 */
	public int updatePolygon(PolygonModel polygonModel){
		return mobileDeviceDao.updatePolygon(polygonModel);
	}
	
	
	/**
	 * 删除区域
	 * @param polygonModel
	 * @return
	 */
	public int deletePolygonById(PolygonModel polygonModel){
		return mobileDeviceDao.deletePolygonById(polygonModel);
	}
	
	/**
	 * 获取设备类型ios或者android
	 * @return
	 */
	public String getMtaDeviceType() {
		return mobileDeviceDao.getMtaDeviceType();
	}
	
	/**
	 * 修改设备类型ios或者android
	 * @return
	 */
	public int updateMtaDeviceType() {
		return mobileDeviceDao.updateMtaDeviceType();
	}
	

	/**
	 * 统计运营商信息
	 * @return
	 */
	public List<CarrierCountModel> getCarrierCountList(Map<String,Object> params){
		return mobileDeviceDao.getCarrierCountList(params);
	}
	
	/**
	 * 根据用户账号获取设备信息
	 * @param mobileDeviceModel
	 * @return
	 */
	public MobileDeviceModel getDeviceInfo(MobileDeviceModel mobileDeviceModel){
		return mobileDeviceDao.getDeviceInfo(mobileDeviceModel);
	}
	
	
	@Override
	public void updateByOpenid(String userName, String openid) {
		mobileDeviceDao.updateByOpenid(userName,openid);
	}

	@Override
	public PolygonModel getWgPolygonList(String  username) {
		List<PolygonModel> polygonList = mobileDeviceDao.getWgPolygonList(username);
		if (polygonList.size() > 0) {
			return polygonList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Integer checkLeave(String username, String time) {
		return mobileDeviceDao.checkLeave(username,time);
	}

	@Override
	public List<PolygonModel> getPolygons() {
		return mobileDeviceDao.getPolygons();
	}

	@Override
	public List<MobileDeviceModel> getLocationsByUser(Map<String, Object> params) {
		return mobileDeviceDao.getLocationsByUser(params);
	}

	@Override
	public List<NationModel> getNation() {
		return mobileDeviceDao.getNation();
	}
}
