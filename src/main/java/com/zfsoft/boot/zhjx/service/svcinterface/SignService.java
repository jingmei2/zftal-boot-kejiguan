package com.zfsoft.boot.zhjx.service.svcinterface;

import java.util.List;

import com.zfsoft.boot.zhjx.dao.entities.SignModel;

public interface SignService {
	void insert (SignModel signModel);
		
	Integer countByYhm(SignModel model);
	
	List<SignModel> selectSignList(SignModel model);
}
