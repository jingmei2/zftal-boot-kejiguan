package com.zfsoft.boot.zhjx.service.svcinterface;

import java.util.List;
import java.util.Map;

import org.apache.batik.util.io.StreamNormalizingReader;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.zhjx.dao.entities.CarrierCountModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileDeviceModel;
import com.zfsoft.boot.zhjx.dao.entities.NationModel;
import com.zfsoft.boot.zhjx.dao.entities.PolygonModel;

public interface IMobileDeviceService  extends BaseService<MobileDeviceModel>{
	/**
	 * 检查是否存在该条记录
	 * @param mobileDeviceModel
	 * @return
	 */
	MobileDeviceModel checkDeviceInfo(MobileDeviceModel mobileDeviceModel);
	
	/**
	 * 插入高德地图多边形经纬度数据
	 * @param polygonModel
	 * @return
	 */
	boolean insertPolygon(PolygonModel polygonModel);
	
	/**
	 * 获取高德地图所有多边形经纬度
	 * @return
	 */
	List<PolygonModel> getPolygonList(PolygonModel polygonModel);
	
	/**
	 * 
	 * @param mobileDeviceModel
	 */
	PolygonModel getPolygonById(PolygonModel polygonModel);
	
	/**
	 * 删除区域
	 * @param polygonModel
	 * @return
	 */
	int deletePolygonById(PolygonModel polygonModel);
	
	/**
	 * 修改区域
	 * @param polygonModel
	 * @return
	 */
	int updatePolygon(PolygonModel polygonModel);
	
	
	/**
	 * 获取设备类型ios或者android
	 * @return
	 */
	String getMtaDeviceType();
	
	/**
	 * 修改设备类型ios或者android
	 * @return
	 */
	int updateMtaDeviceType();
	

	/**
	 * 统计运营商信息
	 * @return
	 */
	List<CarrierCountModel> getCarrierCountList(Map<String,Object> params);
	
	/**
	 * 根据用户账号获取设备信息
	 * @param mobileDeviceModel
	 * @return
	 */
	MobileDeviceModel getDeviceInfo(MobileDeviceModel mobileDeviceModel);
	
	void updateByOpenid(String userName,String openid);
	
	/**
	 * 获取晚归坐标轴列表
	 * @return
	 */
	PolygonModel getWgPolygonList(String  username);
	
	/**
	 * 判断账号是否请假
	 * @return
	 */
	Integer checkLeave(String username,String time);

	//查询预警区域
	List<PolygonModel> getPolygons();

	List<MobileDeviceModel> getLocationsByUser(Map<String, Object> params);
	
	//获取民族
	List<NationModel> getNation();
}
