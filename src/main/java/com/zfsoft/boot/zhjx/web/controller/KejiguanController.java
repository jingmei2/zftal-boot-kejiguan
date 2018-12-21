package com.zfsoft.boot.zhjx.web.controller;

import com.zfsoft.boot.zhjx.dao.entities.ActivityModel;
import com.zfsoft.boot.zhjx.dao.entities.BookModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.service.svcinterface.IActivityService;
import com.zfsoft.boot.zhjx.service.svcinterface.IBookService;
import com.zfsoft.boot.zhjx.util.DateUtil;
import com.zfsoft.boot.zhjx.util.FileUntils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
		ResultEntity resultEntity ;
		bookModel.setCreateTime(DateUtil.fSecond(new Date()));
		bookModel.setActivityId(activityId);
		boolean flag = bookService.insert(bookModel);

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
		bookModel.setCreateTime(DateUtil.fSecond(new Date()));
		flag = bookService.update(bookModel);

		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"修改成功",flag);

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

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",bookModel);


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

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"删除成功",flag);

		return resultEntity;
	}

	@ApiOperation(value = "查询预定列表", notes = "", response = String.class)
	@RequestMapping(value="/selectBookListById",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity selectBookListById(HttpServletRequest request, Model model, BookModel bookModel) {
		List<BookModel> bookModelList = bookService.getModelList(bookModel);
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",bookModelList);
		return resultEntity;
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

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"新增成功",flag);

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

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"修改成功",flag);

		return resultEntity;
	}

	@ApiOperation(value = "查询活动", notes = "", response = String.class)
	@RequestMapping(value="/selectActivityById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity selectActivityById(@RequestParam("id") String id) {
		boolean flag = false;
		ResultEntity resultEntity;
		ActivityModel activityModel = new ActivityModel();
		activityModel.setId(id);
		ActivityModel bookModel = activityService.getModel(activityModel);

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",bookModel);

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

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"删除成功",flag);

		return resultEntity;
	}

	@ApiOperation(value = "查询活动列表", notes = "", response = String.class)
	@RequestMapping(value="/selectActivityListById",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity selectActivityListById(HttpServletRequest request, Model model, ActivityModel activityModel) {
		boolean flag = false;
		ResultEntity resultEntity;

		List<ActivityModel> bookModelList = activityService.getModelList(activityModel);
		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",bookModelList);

		return resultEntity;
	}


	//接口服务不可有相同的服务
	@ApiOperation(value = "活动图片接口", notes = "", response = String.class)
	@RequestMapping(value="/saveorupdatePic",method=RequestMethod.POST)
	public ResultEntity saveorupdate(HttpServletRequest request,
							   HttpServletResponse response, Model model, ActivityModel activityModel,@RequestParam("faceLegalFile") MultipartFile file){
		ResultEntity resultEntity = new ResultEntity();

		FileUntils fileunits=new FileUntils();

		String realpath=request.getSession().getServletContext().getRealPath("/");
		/**
		 *修改服务，判断是否属于进行了图片修改；
		 *是：删除原图片， 保存新上传图片
		 *否：只更新服务
		 */
		if(!file.isEmpty()){

			/**
			 **是：保存新上传图片
			 */
			Map<String,Object> map = fileunits.savePic(file,realpath,FileUntils.PICPATHSERVICE);
			if(map!=null){
				if((boolean) map.get("issuccess")){
					//删除原文件
					fileunits.deletePic(realpath,activityModel.getPicPath());
					//获取新文件
					String path = (String) map.get("path");
					activityModel.setPicPath(path);
					//修改数据库
					activityModel.setCreateTime(DateUtil.fSecond(new Date()));
					boolean flag = activityService.update(activityModel);
					resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"新增图片成功",activityModel);
				} else {
					resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"新增图片失败",activityModel);
				}
			}
		}

		return resultEntity;
	}


}
