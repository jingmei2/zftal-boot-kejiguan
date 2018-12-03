package com.zfsoft.boot.zhjx.dao.daointerface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.zhjx.dao.entities.CarrierCountModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileDeviceModel;
import com.zfsoft.boot.zhjx.dao.entities.NationModel;
import com.zfsoft.boot.zhjx.dao.entities.PolygonModel;

@Mapper
public interface IMobileDeviceDao extends BaseDao<MobileDeviceModel>{

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
	int insertPolygon(PolygonModel polygonModel);
	
	/**
	 * 获取高德地图所有多边形经纬度
	 * @return
	 */
	List<PolygonModel> getPolygonList(PolygonModel polygonModel);
	
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
	
	/**
	 * 
	 * @param mobileDeviceModel
	 */
	PolygonModel getPolygonById(PolygonModel polygonModel);
	
	void updateByOpenid(@Param("userName")String userName,@Param("openid") String openid);

	List<PolygonModel> getWgPolygonList(@Param("username")String  username);

	Integer checkLeave(@Param("username")String username, @Param("time")String time);

	List<PolygonModel> getPolygons();

	List<MobileDeviceModel> getLocationsByUser(Map<String, Object> params);

	List<NationModel> getNation();
}
