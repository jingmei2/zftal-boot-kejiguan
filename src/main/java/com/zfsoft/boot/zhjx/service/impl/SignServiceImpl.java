package com.zfsoft.boot.zhjx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.boot.zhjx.dao.daointerface.SignDao;
import com.zfsoft.boot.zhjx.dao.entities.SignModel;
import com.zfsoft.boot.zhjx.service.svcinterface.SignService;

@Service
public class SignServiceImpl implements SignService{

	@Autowired
	private SignDao dao;
	
	
	@Override
	public void insert(SignModel signModel) {
		dao.insert(signModel);
	}

	@Override
	public Integer countByYhm(SignModel model) {
		return dao.countByYhm(model);
	}
	
	@Override
	public List<SignModel> selectSignList(SignModel model){
		return dao.selectSignList(model);
	}
}
