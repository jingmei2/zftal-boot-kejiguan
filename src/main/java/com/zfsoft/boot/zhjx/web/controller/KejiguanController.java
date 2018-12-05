package com.zfsoft.boot.zhjx.web.controller;

import com.zfsoft.api.web.session.User;
import com.zfsoft.boot.zhjx.dao.entities.ActivityModel;
import com.zfsoft.boot.zhjx.dao.entities.BookModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.service.svcinterface.IActivityService;
import com.zfsoft.boot.zhjx.service.svcinterface.IBookService;
import com.zfsoft.boot.zhjx.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Majing
 */
@Controller
@RequestMapping("/kejiguan")
public class KejiguanController {

	@Autowired
	private IBookService bookService;
	@Autowired
	private IActivityService activityService;

	@RequestMapping("/addBook")
	@ResponseBody
	public ResultEntity addBook(HttpServletRequest request, Model model, BookModel bookModel, String activityId) {
		boolean flag = false;
		ResultEntity resultEntity ;
		bookModel.setCreateTime(DateUtil.fSecond(new Date()));
		bookModel.setActivityId(activityId);
		flag = bookService.insert(bookModel);

		if(flag) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"新增成功",flag);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"新增失败",flag);
		}

		return resultEntity;
	}

	@RequestMapping("/updateBook")
	@ResponseBody
	public ResultEntity updateBook(HttpServletRequest request, Model model, BookModel bookModel) {
		boolean flag = false;
		ResultEntity resultEntity ;
		bookModel.setCreateTime(DateUtil.fSecond(new Date()));
		flag = bookService.update(bookModel);

		if(flag) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"修改成功",flag);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"修改失败",flag);
		}

		return resultEntity;
	}

	@RequestMapping(value="/selectBookById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity selectBookById(String id) {
		boolean flag = false;
		ResultEntity resultEntity;
		BookModel bm = new BookModel();
		bm.setId(id);
		BookModel bookModel = bookService.getModel(bm);

		if(null!=bookModel) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",flag);
			resultEntity.setData(bookModel);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"查询失败",flag);
		}

		return resultEntity;
	}

	@RequestMapping(value="/deleteBookById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity deleteBookById(String id) {
		boolean flag = false;
		ResultEntity resultEntity;
		BookModel bookMode = new BookModel();
		bookMode.setId(id);
		flag = bookService.delete(bookMode);

		if(flag) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"删除成功",flag);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"删除失败",flag);
		}

		return resultEntity;
	}

	@RequestMapping(value="/selectBookListById",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity selectBookListById(HttpServletRequest request, Model model, BookModel bookModel) {
		boolean flag = false;
		ResultEntity resultEntity;

		List<BookModel> bookModelList = bookService.getModelList(bookModel);

		if(!bookModelList.isEmpty()) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",flag);
			resultEntity.setData(bookModelList);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"查询失败",flag);
		}

		return resultEntity;
	}

	//*********************************   活动   *************************************************//
	@RequestMapping("/addActivity")
	@ResponseBody
	public ResultEntity addActivity(HttpServletRequest request, Model model, ActivityModel activityModel) {
		boolean flag = false;
		ResultEntity resultEntity ;
		activityModel.setCreateTime(DateUtil.fSecond(new Date()));
		flag = activityService.insert(activityModel);

		if(flag) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"新增成功",flag);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"新增失败",flag);
		}

		return resultEntity;
	}

	@RequestMapping("/updateActivity")
	@ResponseBody
	public ResultEntity updateActivity(HttpServletRequest request, Model model, ActivityModel activityModel) {
		boolean flag = false;
		ResultEntity resultEntity ;
		activityModel.setCreateTime(DateUtil.fSecond(new Date()));
		flag = activityService.update(activityModel);

		if(flag) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"修改成功",flag);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"修改失败",flag);
		}

		return resultEntity;
	}

	@RequestMapping(value="/selectActivityById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity selectActivityById(String id) {
		boolean flag = false;
		ResultEntity resultEntity;
		ActivityModel activityModel = new ActivityModel();
		activityModel.setId(id);
		ActivityModel bookModel = activityService.getModel(activityModel);

		if(null!=bookModel) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",flag);
			resultEntity.setData(bookModel);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"查询失败",flag);
		}

		return resultEntity;
	}

	@RequestMapping(value="/deleteActivityById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity deleteActivityById(String id) {
		boolean flag = false;
		ResultEntity resultEntity;
		ActivityModel activityModel = new ActivityModel();
		activityModel.setId(id);
		flag = activityService.delete(activityModel);

		if(flag) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"删除成功",flag);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"删除失败",flag);
		}

		return resultEntity;
	}


}
