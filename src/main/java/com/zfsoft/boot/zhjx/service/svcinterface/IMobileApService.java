package com.zfsoft.boot.zhjx.service.svcinterface;

import java.util.List;
import java.util.Map;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.zhjx.dao.entities.ClassInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.LdxxbModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApCountModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApModel;

public interface IMobileApService extends BaseService<MobileApModel>{
	
	List<MobileApCountModel> selectApCountList(Map<String,Object> params);
	
	List<InstituteInfoModel> getInstituteList();

	List<ClassInfoModel> getClassList(String xyId);
	
	List<LdxxbModel> getLddmList();
}
