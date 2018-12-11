package com.zfsoft.boot.zhjx.web.controller;

import com.zfsoft.boot.zhjx.dao.entities.ActivityModel;
import com.zfsoft.boot.zhjx.dao.entities.BookModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.service.svcinterface.IActivityService;
import com.zfsoft.boot.zhjx.service.svcinterface.IBookService;
import com.zfsoft.boot.zhjx.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "【科技馆】接口")
@RestController
@Controller
@RequestMapping("/kejiguan")
public class KejiguanController {

	@Autowired
	private IBookService bookService;
	@Autowired
	private IActivityService activityService;

	@ApiOperation(value = "新增预定", notes = "", response = String.class)
	@RequestMapping(value="/addBook",method=RequestMethod.POST)
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
	@ApiOperation(value="修改预定", notes="")
	@RequestMapping(value="/updateBook",method=RequestMethod.POST)
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

	@ApiOperation(value="查询预定", notes="")
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

	@ApiOperation(value="删除预定", notes="")
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

	@ApiOperation(value = "查询预定列表", notes = "", response = String.class)
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

	@RequestMapping("/selectBookList")
	public String selectBookList(HttpServletRequest request, Model model) {
		//model.addAttribute("target", "jcfx/index");
		System.out.println("selectBookList");
		return "/yhfx/book";
	}

	//*********************************   活动   *************************************************//
	@ApiOperation(value = "新增活动", notes = "", response = String.class)
	@RequestMapping(value="/addActivity",method=RequestMethod.POST)
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

	@ApiOperation(value = "修改活动", notes = "", response = String.class)
	@RequestMapping(value = "/updateActivity",method=RequestMethod.POST)
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

	@ApiOperation(value = "查询活动", notes = "", response = String.class)
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

	@ApiOperation(value = "删除活动", notes = "", response = String.class)
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

	@ApiOperation(value = "查询活动列表", notes = "", response = String.class)
	@RequestMapping(value="/selectActivityListById",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity selectActivityListById(HttpServletRequest request, Model model, ActivityModel activityModel) {
		boolean flag = false;
		ResultEntity resultEntity;

		List<ActivityModel> bookModelList = activityService.getModelList(activityModel);

		if(!bookModelList.isEmpty()) {
			resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",flag);
			resultEntity.setData(bookModelList);
		}else {
			resultEntity = new ResultEntity(ResultEntity.ERROR_CODE,"查询失败",flag);
		}

		return resultEntity;
	}

}
