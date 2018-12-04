package com.zfsoft.boot.zhjx.web.controller;

import com.zfsoft.boot.zhjx.dao.entities.BookModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.service.svcinterface.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Majing
 */
@Controller
@RequestMapping("/kejiguan")
public class KejiguanController {

	@Autowired
	private IBookService bookService;

	@RequestMapping("/test")
	@ResponseBody
	public ResultEntity userLogin(HttpServletRequest request, Model model) {
		ResultEntity resultEntity = new ResultEntity();
		BookModel bookModel = new BookModel();
		bookModel.setId(1);
		bookService.insert(bookModel);

		return resultEntity;
	}

}
