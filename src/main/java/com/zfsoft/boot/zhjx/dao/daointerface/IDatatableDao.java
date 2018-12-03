/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.zhjx.dao.daointerface;

import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.boot.zhjx.dao.entities.DatatableModel;

@Mapper
public interface IDatatableDao {

	DatatableModel get();
}
