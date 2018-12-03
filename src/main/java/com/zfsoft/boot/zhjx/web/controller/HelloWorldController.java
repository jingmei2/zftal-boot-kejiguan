/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.zhjx.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfsoft.boot.zhjx.service.svcinterface.IDatatableService;

@RestController
public class HelloWorldController {
	
	@Autowired
	protected IDatatableService datatableService;
	
}
