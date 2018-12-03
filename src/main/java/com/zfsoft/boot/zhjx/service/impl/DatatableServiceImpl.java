/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.zhjx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.boot.zhjx.dao.daointerface.IDatatableDao;
import com.zfsoft.boot.zhjx.dao.entities.DatatableModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IDatatableService;

@Service
public class DatatableServiceImpl implements IDatatableService {

	@Autowired
	protected IDatatableDao datatableDao;

	@Override
	public DatatableModel get() {
		return datatableDao.get();
	}
	
}
