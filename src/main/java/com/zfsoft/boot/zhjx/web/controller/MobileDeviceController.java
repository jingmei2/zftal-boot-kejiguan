package com.zfsoft.boot.zhjx.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.boot.zhjx.dao.entities.CarrierCountModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileDeviceModel;
import com.zfsoft.boot.zhjx.dao.entities.NationModel;
import com.zfsoft.boot.zhjx.dao.entities.PolygonModel;
import com.zfsoft.boot.zhjx.dao.entities.PoolCache;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.dao.entities.ScanPool;
import com.zfsoft.boot.zhjx.dao.entities.SignModel;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;
import com.zfsoft.boot.zhjx.dao.entities.WarningTypeEnum;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileApService;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileDeviceService;
import com.zfsoft.boot.zhjx.service.svcinterface.IWarningService;
import com.zfsoft.boot.zhjx.service.svcinterface.SignService;
import com.zfsoft.boot.zhjx.util.Cache;
import com.zfsoft.boot.zhjx.util.CacheManager;
import com.zfsoft.boot.zhjx.util.DateUtil;
import com.zfsoft.boot.zhjx.util.HttpUntil;
import com.zfsoft.boot.zhjx.util.Point;
import com.zfsoft.boot.zhjx.util.UUIDUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/mDevice")
public class MobileDeviceController {
	
	private final String wxyjUrl = "http://127.0.0.1:8080/zftal-mobile/pushMsg/pushMsg_fxptadd.html";
	
	@Autowired
	private IMobileDeviceService mobileDeviceService;
	
	@Autowired
	private IMobileApService mobileApService;
	
	@Autowired
	private IWarningService warningService;
	
	@Autowired
	private SignService signService;
	
	


	/**
	 * 提交设备数据到后台
	 * @param request
	 * @param model
	 * @param mobileDeviceModel
	 * @return
	 */
	@RequestMapping(value="/submitDeviceInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity submitDeviceInfo(HttpServletRequest request, Model model,MobileDeviceModel mobileDeviceModel) {
		boolean flag = false;
		//mobileDeviceModel.setImsi(null);
		/*
		MobileDeviceModel deviceInfo = mobileDeviceService.checkDeviceInfo(mobileDeviceModel);
		if(deviceInfo!=null) {
			mobileDeviceModel.setLastLoginTime(new Date());
			flag = mobileDeviceService.update(mobileDeviceModel);
		}else {
			mobileDeviceModel.setLastLoginTime(new Date());
			flag = mobileDeviceService.insert(mobileDeviceModel);
		}
		*/
		
		//mobileDeviceModel = changeLoc(mobileDeviceModel);
		
		mobileDeviceModel.setLastLoginTime(new Date());
		flag = mobileDeviceService.insert(mobileDeviceModel);
		
		
		//判断是否晚归
		//判断账号是否请假
		//获取登录账号所属的取舍区域
		PolygonModel polygonModel = mobileDeviceService.getWgPolygonList(mobileDeviceModel.getUserName());
		if (polygonModel != null) {
			Integer leaveNum = mobileDeviceService.checkLeave(mobileDeviceModel.getUserName(), DateUtil.fSecond(new Date()));
			if (leaveNum == 0) {
				checkLocation(polygonModel, mobileDeviceModel,"0");
			}else {
				checkLocation(polygonModel, mobileDeviceModel,"1");
			}
		}
		
		
		//危险预警，进行推送
		/*List<PolygonModel> list = mobileDeviceService.getPolygons();
		if (list.size() > 0) {
			//推送
			for (PolygonModel polygonModel2 : list) {
				checkYjLocation(polygonModel2,mobileDeviceModel);
			}
		}*/
		
		
		ResultEntity result = null;
		if(flag) {
			result = new ResultEntity(ResultEntity.SUCCESS_CODE,"提交成功",flag);
		}else {
			result = new ResultEntity(ResultEntity.ERROR_CODE,"提交失败",flag);
		}
		
		
		//提交wifi信息
		MobileApModel apModel = new MobileApModel();
		apModel.setUserName(mobileDeviceModel.getUserName());
		apModel.setWifiIp(mobileDeviceModel.getWifiIp());
		apModel.setWifiName(mobileDeviceModel.getWifiName());
		apModel.setNetworkType(mobileDeviceModel.getNetworkType());
		apModel.setCellIp(mobileDeviceModel.getCellIp());
		apModel.setWifiList(mobileDeviceModel.getWifiList());
		apModel.setCreateTime(DateUtil.fSecond(new Date()));
		mobileApService.insert(apModel);
		
		return result;
	}
	

	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity getList(HttpServletRequest request, Model model,MobileDeviceModel mobileDeviceModel) {
		if ("1".equals(mobileDeviceModel.getStatus())) {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			mobileDeviceModel.setStartTime(sd.format(new Date()));
		}
		List<MobileDeviceModel> list = mobileDeviceService.getModelList(mobileDeviceModel);
		
		if(list==null) {
			list = new ArrayList<MobileDeviceModel>();
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", list);
		return resultEntity;
	}
	
	/**
	 * 提交高德地图多边形数据到后台
	 * @param request
	 * @param model
	 * @param mobileDeviceModel
	 * @return
	 */
	@RequestMapping(value="/submitPolygonLnglat",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity submitPolygonLnglat(HttpServletRequest request, Model model,PolygonModel polygonModel) {
		polygonModel.setPolygonId(UUIDUtil.getUUID());
		boolean flag = mobileDeviceService.insertPolygon(polygonModel);
		ResultEntity result = null;
		if(flag) {
			result = new ResultEntity(ResultEntity.SUCCESS_CODE,"提交成功",flag);
		}else {
			result = new ResultEntity(ResultEntity.ERROR_CODE,"提交失败",flag);
		}
		return result;
	}
	
	/**
	 * 删除高德地图多边形数据
	 * @param request
	 * @param model
	 * @param mobileDeviceModel
	 * @return
	 */
	@RequestMapping(value="/deletePolygon",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity deletePolygon(HttpServletRequest request,PolygonModel polygonModel) {
		int rows = mobileDeviceService.deletePolygonById(polygonModel);
		ResultEntity result = null;
		if(rows>0) {
			result = new ResultEntity(ResultEntity.SUCCESS_CODE,"提交成功",rows);
		}else {
			result = new ResultEntity(ResultEntity.ERROR_CODE,"提交失败",rows);
		}
		return result;
	}
	
	/**
	 * 修改高德地图多边形数据
	 * @param request
	 * @param mobileDeviceModel
	 * @return
	 */
	@RequestMapping(value="/updatePolygon",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity updatePolygon(HttpServletRequest request,PolygonModel polygonModel) {
		int rows = mobileDeviceService.updatePolygon(polygonModel);
		ResultEntity result = null;
		if(rows>0) {
			result = new ResultEntity(ResultEntity.SUCCESS_CODE,"提交成功",rows);
		}else {
			result = new ResultEntity(ResultEntity.ERROR_CODE,"提交失败",rows);
		}
		return result;
	}
	
	/**
	 * 获取已绘制地图区域
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getPolygonList",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity getPolygonList(HttpServletRequest request, Model model,PolygonModel polygonModel) {
		List<PolygonModel> polygonList = mobileDeviceService.getPolygonList(polygonModel);
		if(polygonList==null) {
			polygonList = new ArrayList<PolygonModel>();
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", polygonList);
		return resultEntity;
	}
	
	/**
	 * 详情
	 * @return
	 */
	@RequestMapping(value="/getPolygonById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity getPolygonById(PolygonModel polygonModel){
		PolygonModel polygonModelQueryed = mobileDeviceService.getPolygonById(polygonModel);
		if(polygonModelQueryed==null) {
			polygonModelQueryed = new PolygonModel();
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", polygonModelQueryed);
		return resultEntity;
	}
	
	/**
	 * 获取设备类型ios或者android
	 * @return
	 */
	@RequestMapping(value="/getMtaDeviceType",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity getMtaDeviceType() {
		String deviceType = mobileDeviceService.getMtaDeviceType();
		if(deviceType==null) {
			deviceType = "android";
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", deviceType);
		return resultEntity;
	}
	
	/**
	 * 修改设备类型ios或者android
	 * @return
	 */
	@RequestMapping(value="/updateMtaDeviceType",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity updateMtaDeviceType() {
		int rows = mobileDeviceService.updateMtaDeviceType();
		ResultEntity resultEntity = null;
		if(rows>0) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "提交成功", rows);
		}else {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "提交失败", rows);
		}
		return resultEntity;
	}
	
	/**
	 * 获取设备运营商统计信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getCarrierCountList",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity getCarrierCountList(HttpServletRequest request, Model model,String lastDates) {
		Map<String,Object> params = new HashMap<String,Object>();
		String starDateStr = DateUtil.getPastDate(0)+" 00:00:00";
		String endDateStr = DateUtil.getPastDate(0)+" 11:59:59";
		
		switch(lastDates) {
		  case "0":{
			  starDateStr = DateUtil.getPastDate(0)+" 00:00:00";
			  endDateStr = DateUtil.getPastDate(0)+" 11:59:59";
			  break;
		  }
		  case "7":{
			  starDateStr = DateUtil.getPastDate(7)+" 00:00:00";
			  endDateStr = DateUtil.getPastDate(0)+" 11:59:59";
			  break;
		  }
		  case "14":{
			  starDateStr = DateUtil.getPastDate(14)+" 00:00:00";
			  endDateStr = DateUtil.getPastDate(0)+" 11:59:59";
			  break;
		  }
		  case "30":{
			  starDateStr = DateUtil.getPastDate(30)+" 00:00:00";
			  endDateStr = DateUtil.getPastDate(0)+" 11:59:59";
			  break;
		  }
		  case "total":{
			  starDateStr = "";
			  endDateStr = "";
			  break;
		  }
		  default:{
			  starDateStr = lastDates+" 00:00:00";
			  endDateStr = lastDates+" 11:59:59";
			  break;
		  }
		}
		params.put("starDateStr", starDateStr);
		params.put("endDateStr", endDateStr);
		
		List<CarrierCountModel> carrierCountModelList = mobileDeviceService.getCarrierCountList(params);
		if(carrierCountModelList==null) {
			carrierCountModelList = new ArrayList<CarrierCountModel>();
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", carrierCountModelList);
		return resultEntity;
	}
	
	/**
	 * 测试用
	 * @return
	 */
	@RequestMapping(value="/toAddPage",method=RequestMethod.GET)
	public String toAddPage(){
		
		return "html/test";
	}
	
	/**
	 * 根据账号获取设备信息
	 * @return
	 */
	@RequestMapping(value="/getDeviceInfo",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity getDeviceInfo(MobileDeviceModel mobileDeviceModel) {
		MobileDeviceModel mobileDeviceModelQueryed = mobileDeviceService.getDeviceInfo(mobileDeviceModel);
		if(mobileDeviceModelQueryed==null) {
			mobileDeviceModelQueryed = new MobileDeviceModel();
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", mobileDeviceModelQueryed);
		return resultEntity;
	}
	
	//分析平台签到
	@RequestMapping("/signIn")
	@ResponseBody
	public ResultEntity signIn(String userName,String signData,String location,String content,String address) {
		SignModel signModel = new SignModel();
		signModel.setYhm(userName);
		//判断当天是否已签到
		signModel.setSignData(signData.substring(0,11));
		Integer a = signService.countByYhm(signModel);
		int result = 0;
		if (a > 0) {
			result = 1;
		}
		
		signModel.setSignData(signData);
		signModel.setLocation(location);
		signModel.setContent(content);
		signModel.setAddress(address);
		signService.insert(signModel);
		
		
		
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", result);
		return resultEntity;
	}

	
	//判断上传的坐标是否晚归
	public void checkLocation(PolygonModel polygonModel,MobileDeviceModel mobileDeviceModel,String status) {
		String address = mobileDeviceModel.getLocInfo();
		String[] ss = address.split(",");
		double aString = Double.parseDouble(ss[0]);
		double bString = Double.parseDouble(ss[1]);
		
		ArrayList<Double> x = new ArrayList<>();
		
		ArrayList<Double> y = new ArrayList<>();
		String area = polygonModel.getLnglatStr();
		String[] areas = area.split(";");
		for (String string : areas) {
			String[] zb = string.split(",");
			x.add(Double.parseDouble(zb[0]));
			y.add(Double.parseDouble(zb[1]));
		}
		
		String time1 = polygonModel.getEndTime();
		time1 = time1.substring(11);
		
		String time2 = DateUtil.fSecond(new Date());
		time2 = time2.substring(11, 16);
		System.out.println(time2);
		boolean a = DateUtil.compTime(time1, time2);
		if (!a) {
			System.out.println("a="+a);
			boolean point = Point.isPointInPolygon(aString,bString,x,y);
			if (!point) {
				System.out.println("point = "+point);
				String username = mobileDeviceModel.getUserName();
				String time = DateUtil.fDate(new Date());
				
				
				Cache cache = CacheManager.getCacheInfo(username);
				if (cache == null) {
					//charu
					WarningModel warningModel = new WarningModel();
					warningModel.setUsername(username);
					warningModel.setTime(DateUtil.fDate(new Date()));
					warningModel.setPolygonId(polygonModel.getPolygonId());
					warningModel.setType(WarningTypeEnum.WGYJ.getIndex()+"");
					warningModel.setLeaveStatus(status);
					warningService.insertWarning(warningModel);
					System.out.println("insert ");
					
					Cache c1 = new Cache();
					c1.setKey(username);
					c1.setValue(time);
					c1.setTimeOut(2*60*60*1000);
					CacheManager.putCacheInfo(username, c1, 2*60*60*1000);
				}
			}
		}
	}
	
	
	private void checkYjLocation(PolygonModel polygonModel, MobileDeviceModel mobileDeviceModel) {
		String address = mobileDeviceModel.getLocInfo();
		String[] ss = address.split(",");
		double aString = Double.parseDouble(ss[0]);
		double bString = Double.parseDouble(ss[1]);
		
		ArrayList<Double> x = new ArrayList<>();
		
		ArrayList<Double> y = new ArrayList<>();
		String area = polygonModel.getLnglatStr();
		String[] areas = area.split(";");
		for (String string : areas) {
			String[] zb = string.split(",");
			x.add(Double.parseDouble(zb[0]));
			y.add(Double.parseDouble(zb[1]));
		}
		
		boolean point = Point.isPointInPolygon(aString,bString,x,y);
		if (point) {
			System.out.println("point = "+point);
			String username = mobileDeviceModel.getUserName();
			String time = DateUtil.fDate(new Date());
			
			Cache cache = CacheManager.getCacheInfo("wxyj" + username);
			if (cache == null) {
				//推送
				Map<String,Object> param = new HashMap<>();
				param.put("pushMsg.appType", "YDXY");
				param.put("pushMsg.timeToLive", "86400");
				param.put("pushMsg.tsbt", " ");
				param.put("pushMsg.tsdx", "20080108,");
				param.put("pushMsg.tsdxlx", "ALIAS");
				param.put("pushMsg.tsfs", "1");
				param.put("pushMsg.tsnr", mobileDeviceModel.getXm()+"进入了危险区域"+polygonModel.getTitle());
				param.put("pushMsg.tspt", "ALL");
				param.put("pushMsg.tsry", "admin");
				
				try {
					HttpUntil.doPost(wxyjUrl, param);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Cache c1 = new Cache();
				c1.setKey("wxyj" + username);
				c1.setValue(time);
				c1.setTimeOut(10*60*1000);
				CacheManager.putCacheInfo("wxyj" + username, c1, 10*60*1000);
			}
			
			//charu
			WarningModel warningModel = new WarningModel();
			warningModel.setUsername(username);
			warningModel.setTime(DateUtil.fSecond(new Date()));
			warningModel.setPolygonId(polygonModel.getPolygonId());
			warningModel.setType(WarningTypeEnum.WXYJ.getIndex()+"");
			warningModel.setPlace(polygonModel.getTitle());
			
			warningService.insertWarning(warningModel);
			System.out.println("insert ");
			
		}
		
	}


	//分析平台签到列表
	@RequestMapping("/selectSignList")
	@ResponseBody
	public ResultEntity selectSignList(SignModel signModel) {
		/*SignModel signModel = new SignModel();
		signModel.setKssj(kssj);
		signModel.setJssj(jssj);
		signModel.setClassId(classId);
		signModel.setInstituteId(instituteId);
		signModel.setAddress(address);*/
		
		List<SignModel> signList = signService.selectSignList(signModel);
		
		if(signList == null) {
			signList = new ArrayList<SignModel>();
		}
		
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", signList);
		return resultEntity;
	}

	//查询用户轨迹信息
	@RequestMapping("/locations")
	@ResponseBody
	public ResultEntity getLocations(String daySelected,@RequestParam String username) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		String startDate = DateUtil.getPastDate(0); 
		String endDate = DateUtil.getPastDate(-1);
		
		if(daySelected!=null&&!"".equals(daySelected)){
			if("todaySp".equals(daySelected)){
				startDate = DateUtil.getPastDate(0);
			}else if("sevenDaySp".equals(daySelected)) {
				startDate = DateUtil.getPastDate(7);
			}else if("fourteenSp".equals(daySelected)) {
				startDate = DateUtil.getPastDate(14);
			}else if("thirtySp".equals(daySelected)) {
				startDate = DateUtil.getPastDate(30);
			}else if("totalSp".equals(daySelected)) {
				startDate = "";
			}else{
				startDate = daySelected;
				endDate = daySelected;
			}
		}
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("username", username);
		List<MobileDeviceModel> list =  mobileDeviceService.getLocationsByUser(params);
		
		List<String> x = new ArrayList<>();
		List<String> y = new ArrayList<>();
		for (MobileDeviceModel mobileDeviceModel : list) {
			String[] loc = mobileDeviceModel.getLocInfo().split(",");
			x.add(loc[0]);
			y.add(loc[1]);
		}
		result.put("x", x);
		result.put("y", y);
		
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", result);
		
		return resultEntity;
	}
	
	private MobileDeviceModel changeLoc(MobileDeviceModel mobileDeviceModel) {
		
		String result = "";
		
		//坐标转换
		Map<String,Object> param = new HashMap<>();
		param.put("key", "84f61c1b98a8b31f8381ea0ace209a5d");
		param.put("locations", "120.067361,30.290183");
		param.put("coordsys", "baidu");
		
		try {
			result = HttpUntil.doPost("http://restapi.amap.com/v3/assistant/coordinate/convert", param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		System.out.println("locations = " + jsonObject.get("locations"));
		mobileDeviceModel.setLocInfo(jsonObject.get("locations").toString());
		
		return mobileDeviceModel;
	}
	
	
	@RequestMapping("/getNation")
	@ResponseBody
	public ResultEntity getNation() {
		
		List<NationModel> list = mobileDeviceService.getNation();
		
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功",list);
		return resultEntity;
	}
	
}
