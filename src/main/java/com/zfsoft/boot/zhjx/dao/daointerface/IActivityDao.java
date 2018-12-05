package com.zfsoft.boot.zhjx.dao.daointerface;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.zhjx.dao.entities.ActivityModel;
import com.zfsoft.boot.zhjx.dao.entities.BookModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IActivityDao extends BaseDao<ActivityModel>{

}
