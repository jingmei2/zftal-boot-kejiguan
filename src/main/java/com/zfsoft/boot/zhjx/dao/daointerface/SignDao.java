package com.zfsoft.boot.zhjx.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.boot.zhjx.dao.entities.SignModel;

@Mapper
public interface SignDao {
	void insert(SignModel model);
	
	Integer countByYhm(SignModel model);
	
	List<SignModel> selectSignList(SignModel model);
}
