package com.zfsoft.boot.zhjx.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.boot.zhjx.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileDeviceService;
import com.zfsoft.boot.zhjx.setup.config.AppInfoConfig;
import com.zfsoft.boot.zhjx.util.DateUtil;
import com.zfsoft.boot.zhjx.util.HttpUntil;
import com.zfsoft.boot.zhjx.util.TencentUntil;
import com.zfsoft.boot.zhjx.web.vo.MtaHydEntity;
import com.zfsoft.boot.zhjx.web.vo.MtaHydRetEntity;
import com.zfsoft.boot.zhjx.web.vo.MtaSssjEntity;
import com.zfsoft.boot.zhjx.web.vo.MtaSssjRetEntity;
import com.zfsoft.boot.zhjx.web.vo.MtaUsageEntity;
import com.zfsoft.boot.zhjx.web.vo.MtaUsageRetEntity;
import com.zfsoft.boot.zhjx.web.vo.TencentMtaVo;

/**
 * 获取腾讯开放api接口数据
 * @author liucb
 *
 */
@Controller
@RequestMapping("/mta")
public class MtaController {
	private static final String YYJBZB_LXSJ_URL = "/ctr_user_basic/get_offline_data";
	private static final String YYJBZB_SSSJ_URL = "/ctr_user_basic/get_realtime_data";
	private static final String ZDSBSJ_URL      = "/ctr_terminal_anal/get_offline_data";
	private static final String YHXW_ACTIVE_URL = "/ctr_active_anal/get_offline_data";
	private static final String YHXW_USAGE_URL  = "/ctr_usage_anal/get_offline_data";
	private static final String YHXW_FREQ_URL   = "/ctr_usage_anal/get_freq_dis";
	private static final String KFZCSJ_OFFLINE_URL = "/ctr_crash_anal/get_offline_data";
	private static final String KFZCSJ_ENV_URL  = "/ctr_crash_anal/get_env_dis";
	private static final String KFZCSJ_ERR_URL  = "/ctr_crash_anal/get_err_list";

	@Autowired
	private AppInfoConfig appInfoConfig;
	@Autowired
	private IMobileDeviceService mobileDeviceService;

	/**
	 * 应用基本指标-离线数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yyjbzbLxsj")
	@ResponseBody
	public String yyjbzbLxsj(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();

		String startDate = tencentMtaVo.getStart_date();
		String endDate = tencentMtaVo.getEnd_date();
		String idx = tencentMtaVo.getIdx();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",startDate);
		paramsMap.put("end_date",endDate);
		paramsMap.put("idx", idx);

		String requestUrl = TencentUntil.getUrlWithSign("GET",YYJBZB_LXSJ_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 应用基本指标-离线数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yyjbzbLxsjLastThirtyDays")
	@ResponseBody
	public Map<String,Object> yyjbzbLxsjLastThirtyDays(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		Map<String,Object> resultMap = new HashMap<String,Object>();


		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String idx = tencentMtaVo.getIdx();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",DateUtil.getPastDate(30));
		paramsMap.put("end_date",DateUtil.getPastDate(1));
		paramsMap.put("idx", idx);

		String requestUrl = TencentUntil.getUrlWithSign("GET",YYJBZB_LXSJ_URL, paramsMap, appKey);
		try {
			//String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			String result = HttpUtils.sendGet(requestUrl);
			Gson  gson = new Gson();
			MtaSssjRetEntity mtaSssjRetEntity= gson.fromJson(result,MtaSssjRetEntity.class);
			Map<String,MtaSssjEntity> mapResult = mtaSssjRetEntity.getRet_data();

			List<String> keyList = new ArrayList<String>();
			List<Integer> newUserList = new ArrayList<Integer>();
			List<Integer> activeUserList = new ArrayList<Integer>();
			List<Integer> sessionCountList = new ArrayList<Integer>();
			List<Integer> totalUserList = new ArrayList<Integer>();
			List<Integer> qvList = new ArrayList<Integer>();

			for(String key : mapResult.keySet()){
			   //MtaSssjEntity value = mapResult.get(key);
			   keyList.add(key);
			}
			//先对mapResult按照key排序
			Collections.sort(keyList);

			for (int i = 0; i < keyList.size(); i++) {
				MtaSssjEntity value = mapResult.get(keyList.get(i));
				newUserList.add(Integer.parseInt(value.getNewUser()==null? "0":value.getNewUser()));
				activeUserList.add(Integer.parseInt(value.getActiveUser()==null? "0":value.getActiveUser()));
				sessionCountList.add(Integer.parseInt(value.getSessionCount()==null? "0":value.getSessionCount()));
				totalUserList.add(Integer.parseInt(value.getTotalUser()==null? "0":value.getTotalUser()));
				qvList.add(Integer.parseInt(value.getQv()==null? "0":value.getQv()));
			}

			resultMap.put("keyList", keyList);
			resultMap.put("newUserList", newUserList);
			resultMap.put("activeUserList", activeUserList);
			resultMap.put("sessionCountList", sessionCountList);
			resultMap.put("totalUserList", totalUserList);
			resultMap.put("qvList", qvList);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			return resultMap;
		}
	}


	/**
	 * 应用基本指标-实时数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yyjbzbSssj")
	@ResponseBody
	public String yyjbzbSssj(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String idx = tencentMtaVo.getIdx();

		paramsMap.put("app_id", appId);
		paramsMap.put("idx", idx);

		String requestUrl = TencentUntil.getUrlWithSign("GET",YYJBZB_SSSJ_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 终端设备数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/zdsbsj")
	@ResponseBody
	public String zdsbsj(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String startDate = tencentMtaVo.getStart_date();
		String endDate = tencentMtaVo.getEnd_date();
		String idx = tencentMtaVo.getIdx();
		String ty = tencentMtaVo.getTy();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",startDate);
		paramsMap.put("end_date",endDate);
		paramsMap.put("idx", idx);
		paramsMap.put("ty", ty);

		String requestUrl = TencentUntil.getUrlWithSign("GET",ZDSBSJ_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用户行为数据-活跃度
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yhxwsjActive")
	@ResponseBody
	public String yhxwsjActive(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String startDate = tencentMtaVo.getStart_date();
		String endDate = tencentMtaVo.getEnd_date();
		String idx = tencentMtaVo.getIdx();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",startDate);
		paramsMap.put("end_date",endDate);
		paramsMap.put("idx", idx);

		String requestUrl = TencentUntil.getUrlWithSign("GET",YHXW_ACTIVE_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 应用基本指标-离线数据,过去三十天
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yhxwsjActiveLastThirtyDays")
	@ResponseBody
	public Map<String,Object> yhxwsjActiveLastThirtyDays(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		Map<String,Object> resultMap = new HashMap<String,Object>();


		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String idx = tencentMtaVo.getIdx();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",DateUtil.getPastDate(30));
		paramsMap.put("end_date",DateUtil.getPastDate(1));
		paramsMap.put("idx", idx);

		String requestUrl = TencentUntil.getUrlWithSign("GET",YHXW_ACTIVE_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			Gson  gson = new Gson();
			MtaHydRetEntity mtaHydRetEntity= gson.fromJson(result,MtaHydRetEntity.class);
			Map<String,MtaHydEntity> mapResult = mtaHydRetEntity.getRet_data();

			List<String> keyList = new ArrayList<String>();
			List<Integer> dayUvList = new ArrayList<Integer>();
			List<Integer> weekUvList = new ArrayList<Integer>();
			List<Integer> monthUvList = new ArrayList<Integer>();
			List<Double> duvwuvList = new ArrayList<Double>();
			List<Double> duvmuvList = new ArrayList<Double>();

			for(String key : mapResult.keySet()){
			   //MtaSssjEntity value = mapResult.get(key);
			   keyList.add(key);
			}
			//先对mapResult按照key排序
			Collections.sort(keyList);

			for (int i = 0; i < keyList.size(); i++) {
				MtaHydEntity value = mapResult.get(keyList.get(i));
				dayUvList.add(Integer.parseInt(value.getDayUv()==null? "0":value.getDayUv()));
				weekUvList.add(Integer.parseInt(value.getWeekUv()==null? "0":value.getWeekUv()));
				monthUvList.add(Integer.parseInt(value.getMonthUv()==null? "0":value.getMonthUv()));
				duvwuvList.add((double)Integer.parseInt(value.getDayUv()==null? "0":value.getDayUv())/Integer.parseInt(value.getWeekUv()==null? "1":value.getWeekUv()));
				duvmuvList.add((double)Integer.parseInt(value.getDayUv()==null? "0":value.getDayUv())/Integer.parseInt(value.getMonthUv()==null? "1":value.getMonthUv()));
			}

			resultMap.put("keyList", keyList);
			resultMap.put("dayUvList", dayUvList);
			resultMap.put("weekUvList", weekUvList);
			resultMap.put("monthUvList", monthUvList);
			resultMap.put("duvwuvList", duvwuvList);
			resultMap.put("duvmuvList", duvmuvList);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			return resultMap;
		}
	}

	/**
	 * 用户行为数据-用户行为分析
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yhxwsjUsage")
	@ResponseBody
	public String yhxwsjUsage(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String startDate = tencentMtaVo.getStart_date();
		String endDate = tencentMtaVo.getEnd_date();
		String idx = tencentMtaVo.getIdx();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",startDate);
		paramsMap.put("end_date",endDate);
		paramsMap.put("idx", idx);

		String requestUrl = TencentUntil.getUrlWithSign("GET",YHXW_USAGE_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 用户行为数据-最近三十天用户行为分析数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yhxwsjUsageLastThirtyDays")
	@ResponseBody
	public Map<String,Object> yhxwsjUsageLastThirtyDays(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		Map<String,Object> resultMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String idx = tencentMtaVo.getIdx();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",DateUtil.getPastDate(30));
		paramsMap.put("end_date",DateUtil.getPastDate(0));
		paramsMap.put("idx", idx);

		String requestUrl = TencentUntil.getUrlWithSign("GET",YHXW_USAGE_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			Gson  gson = new Gson();
			MtaUsageRetEntity mtaUsageRetEntity= gson.fromJson(result,MtaUsageRetEntity.class);
			Map<String,MtaUsageEntity> mapResult = mtaUsageRetEntity.getRet_data();


			List<String> keyList = new ArrayList<String>();
			List<Double> onlineTimePerSessionList = new ArrayList<Double>();
			List<Double> onlineTimePerUvList = new ArrayList<Double>();
			List<Double> pageCountPerSessionList = new ArrayList<Double>();
			List<Double> pageCountPerUvList = new ArrayList<Double>();

			for(String key : mapResult.keySet()){
			   //MtaSssjEntity value = mapResult.get(key);
			   keyList.add(key);
			}
			//先对mapResult按照key排序
			Collections.sort(keyList);

			for (int i = 0; i < keyList.size(); i++) {
				MtaUsageEntity value = mapResult.get(keyList.get(i));
				onlineTimePerSessionList.add(Double.parseDouble(value.getOnlineTimePerSession().replaceAll(",", "")));
				onlineTimePerUvList.add(Double.parseDouble(value.getOnlineTimePerUv().replaceAll(",", "")));
				pageCountPerSessionList.add(Double.parseDouble(value.getPageCountPerSession().replaceAll(",", "")));
				pageCountPerUvList.add(Double.parseDouble(value.getPageCountPerUv().replaceAll(",", "")));
			}

			resultMap.put("keyList", keyList);
			resultMap.put("onlineTimePerSessionList", onlineTimePerSessionList);
			resultMap.put("onlineTimePerUvList", onlineTimePerUvList);
			resultMap.put("pageCountPerSessionList", pageCountPerSessionList);
			resultMap.put("pageCountPerUvList", pageCountPerUvList);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			return resultMap;
		}
	}

	/**
	 * 用户行为数据-使用频率分析
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yhxwsjFreq")
	@ResponseBody
	public String yhxwsjFreq(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String startDate = tencentMtaVo.getStart_date();
		String endDate = tencentMtaVo.getEnd_date();
		String idx = tencentMtaVo.getIdx();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",startDate);
		paramsMap.put("end_date",endDate);
		paramsMap.put("idx", idx);

		String requestUrl = TencentUntil.getUrlWithSign("GET",YHXW_FREQ_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 开发支持数据-Crash分析
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/kfzcsjOffline")
	@ResponseBody
	public String kfzcsjOffline(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String startDate = tencentMtaVo.getStart_date();
		String endDate = tencentMtaVo.getEnd_date();
		String idx = tencentMtaVo.getIdx();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",startDate);
		paramsMap.put("end_date",endDate);
		paramsMap.put("idx", idx);

		String requestUrl = TencentUntil.getUrlWithSign("GET",KFZCSJ_OFFLINE_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 开发支持数据-Crash终端分析
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/kfzcsjEnv")
	@ResponseBody
	public String kfzcsjEnv(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String startDate = tencentMtaVo.getStart_date();
		String endDate = tencentMtaVo.getEnd_date();
		String idx = tencentMtaVo.getIdx();
		String ty = tencentMtaVo.getTy();


		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",startDate);
		paramsMap.put("end_date",endDate);
		paramsMap.put("idx", idx);
		paramsMap.put("ty", ty);

		String requestUrl = TencentUntil.getUrlWithSign("GET",KFZCSJ_ENV_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 开发支持数据-Crash终端分析
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/kfzcsjErr")
	@ResponseBody
	public String kfzcsjErr(HttpServletRequest request, Model model,TencentMtaVo tencentMtaVo) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String appId = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppId():appInfoConfig.getIosAppId();
		String appKey = "android".equals(mobileDeviceService.getMtaDeviceType())? appInfoConfig.getAndroidAppKey():appInfoConfig.getIosAppKey();
		String startDate = tencentMtaVo.getStart_date();
		String endDate = tencentMtaVo.getEnd_date();
		String idx = tencentMtaVo.getIdx();
		String err_ty = tencentMtaVo.getErr_ty();

		paramsMap.put("app_id", appId);
		paramsMap.put("start_date",startDate);
		paramsMap.put("end_date",endDate);
		paramsMap.put("idx", idx);
		paramsMap.put("err_ty", err_ty);

		String requestUrl = TencentUntil.getUrlWithSign("GET",KFZCSJ_ERR_URL, paramsMap, appKey);
		try {
			String result = HttpUntil.doGet(requestUrl, new HashMap<String,Object>());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
