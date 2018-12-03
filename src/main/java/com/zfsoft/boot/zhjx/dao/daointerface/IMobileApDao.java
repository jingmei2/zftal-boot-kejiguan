package com.zfsoft.boot.zhjx.dao.daointerface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.zhjx.dao.entities.ClassInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.LdxxbModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApCountModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApModel;

@Mapper
public interface IMobileApDao extends BaseDao<MobileApModel>{

	List<MobileApCountModel> selectApCountList(Map<String,Object> params);
	
	List<InstituteInfoModel> getInstituteList();

	List<ClassInfoModel> getClassList(@Param(value = "xyId") String xyId);

	List<LdxxbModel> getLddmList();
}
