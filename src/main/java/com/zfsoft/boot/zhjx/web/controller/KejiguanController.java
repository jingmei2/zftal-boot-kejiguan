package com.zfsoft.boot.zhjx.web.controller;

import com.zfsoft.boot.zhjx.dao.entities.*;
import com.zfsoft.boot.zhjx.service.svcinterface.IActivityService;
import com.zfsoft.boot.zhjx.service.svcinterface.IBookService;
import com.zfsoft.boot.zhjx.service.svcinterface.INavigationService;
import com.zfsoft.boot.zhjx.service.svcinterface.IVenueService;
import com.zfsoft.boot.zhjx.util.DateUtil;
import com.zfsoft.boot.zhjx.util.FileUntils;
import com.zfsoft.boot.zhjx.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
	@Autowired
	private IVenueService venueService;
	@Autowired
	private INavigationService navigationService;

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

		List<ActivityModel> list = activityService.getModelList(activityModel);
		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",list);

		return resultEntity;
	}


	//接口服务不可有相同的服务
	@ApiOperation(value = "活动图片接口", notes = "", response = String.class)
	@RequestMapping(value="/saveorupdatePic",method=RequestMethod.POST)
	public ResultEntity saveorupdate(HttpServletRequest request,
							   HttpServletResponse response, Model model, ActivityModel activityModel,@RequestParam(value="faceLegalFile", required=false) MultipartFile faceLegalFile){
		ResultEntity resultEntity = new ResultEntity();

		FileUntils fileunits=new FileUntils();

		String realpath=request.getSession().getServletContext().getRealPath("/");
		/**
		 *修改服务，判断是否属于进行了图片修改；
		 *是：删除原图片， 保存新上传图片
		 *否：只更新服务
		 */
		if(faceLegalFile!=null){

			/**
			 **是：保存新上传图片
			 */
			Map<String,Object> map = fileunits.savePic(faceLegalFile,realpath,FileUntils.PICPATHSERVICE);
			if(map!=null){
				if((boolean) map.get("issuccess")){
					//删除原文件
					fileunits.deletePic(realpath,activityModel.getPicPath());
					//获取新文件
					String path = (String) map.get("path");
					activityModel.setPicPath(path);
				}
			}
		}
		//id=null 说明是新增
		if(null!=activityModel&&activityModel.getId()!=null){
			//修改数据库
			activityModel.setCreateTime(DateUtil.fSecond(new Date()));
			boolean flag = activityService.update(activityModel);
			if(flag){
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"修改成功",activityModel);
			} else {
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"修改失败",activityModel);
			}
		} else {
			activityModel.setId(UUIDUtil.getUUID().trim());
			activityModel.setCreateTime(DateUtil.fSecond(new Date()));
			boolean flag = activityService.insert(activityModel);
			if(flag){
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"新增成功",activityModel);
			} else {
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"新增失败",activityModel);
			}
		}

		return resultEntity;
	}

	//*********************************   场馆   *************************************************//
	@ApiOperation(value = "新增场馆", notes = "", response = String.class)
	@RequestMapping(value="/addVenue",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity addVenue(HttpServletRequest request, Model model, VenueModel venueModel) {
		boolean flag = false;
		ResultEntity resultEntity ;
		venueModel.setCreateTime(DateUtil.fSecond(new Date()));
		flag = venueService.insert(venueModel);

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"新增成功",flag);

		return resultEntity;
	}

	@ApiOperation(value = "修改场馆", notes = "", response = String.class)
	@RequestMapping(value = "/updateVenue",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity updateVenue(HttpServletRequest request, Model model, VenueModel venueModel) {
		boolean flag = false;
		ResultEntity resultEntity ;
		venueModel.setCreateTime(DateUtil.fSecond(new Date()));
		flag = venueService.update(venueModel);

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"修改成功",flag);

		return resultEntity;
	}

	@ApiOperation(value = "查询场馆", notes = "", response = String.class)
	@RequestMapping(value="/selectVenueById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity selectVenueById(@RequestParam("id") String id) {
		boolean flag = false;
		ResultEntity resultEntity;
		VenueModel venueModel = new VenueModel();
		venueModel.setId(id);
		VenueModel bookModel = venueService.getModel(venueModel);

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",bookModel);

		return resultEntity;
	}

	@ApiOperation(value = "删除场馆", notes = "", response = String.class)
	@RequestMapping(value="/deleteVenueById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity deleteVenueById(String id) {
		boolean flag = false;
		ResultEntity resultEntity;
		VenueModel venueModel = new VenueModel();
		venueModel.setId(id);
		flag = venueService.delete(venueModel);

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"删除成功",flag);

		return resultEntity;
	}

	@ApiOperation(value = "查询场馆列表", notes = "", response = String.class)
	@RequestMapping(value="/selectVenueListById",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity selectVenueListById(HttpServletRequest request, Model model, VenueModel venueModel) {
		boolean flag = false;
		ResultEntity resultEntity;

		List<VenueModel> bookModelList = venueService.getModelList(venueModel);
		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",bookModelList);

		return resultEntity;
	}


	//接口服务不可有相同的服务
	@ApiOperation(value = "场馆图片接口", notes = "", response = String.class)
	@RequestMapping(value="/saveorupdatePicForVenue",method=RequestMethod.POST)
	public ResultEntity saveorupdatePicForVenue(HttpServletRequest request,
									 HttpServletResponse response, Model model, VenueModel venueModel,@RequestParam(value="faceLegalFile", required=false) MultipartFile faceLegalFile,@RequestParam(value="voice", required=false) MultipartFile voice,@RequestParam(value="video", required=false) MultipartFile video){
		ResultEntity resultEntity = new ResultEntity();

		FileUntils fileunits=new FileUntils();

		String realpath=request.getSession().getServletContext().getRealPath("/");
		/**
		 *修改服务，判断是否属于进行了图片修改；
		 *是：删除原图片， 保存新上传图片
		 *否：只更新服务
		 */
		if(faceLegalFile!=null){

			/**
			 **是：保存新上传图片
			 */
			Map<String,Object> map = fileunits.savePic(faceLegalFile,realpath,FileUntils.PICPATHSERVICE);
			if(map!=null){
				if((boolean) map.get("issuccess")){
					//删除原文件
					fileunits.deletePic(realpath,venueModel.getPicPath());
					//获取新文件
					String path = (String) map.get("path");
					venueModel.setPicPath(path);
				}

			}
		}
		/**
		 *修改服务，判断是否属于进行了音频修改；
		 *是：删除原音频， 保存新上传音频
		 *否：只更新服务
		 */
		if(voice!=null){

			/**
			 **是：保存新上传音频
			 */
			Map<String,Object> map = fileunits.savePic(voice,realpath,FileUntils.PICPATHSERVICE);
			if(map!=null){
				if((boolean) map.get("issuccess")){
					//删除原文件
					fileunits.deletePic(realpath,venueModel.getPicPath());
					//获取新文件
					String path = (String) map.get("path");
					venueModel.setVoicePath(path);
				}

			}
		}
		/**
		 *修改服务，判断是否属于进行了视频修改；
		 *是：删除原视频， 保存新上传视频
		 *否：只更新服务
		 */
		if(video!=null){

			/**
			 **是：保存新上传视频
			 */
			Map<String,Object> map = fileunits.savePic(video,realpath,FileUntils.PICPATHSERVICE);
			if(map!=null){
				if((boolean) map.get("issuccess")){
					//删除原文件
					fileunits.deletePic(realpath,venueModel.getPicPath());
					//获取新文件
					String path = (String) map.get("path");
					venueModel.setVideoPath(path);
				}

			}
		}
		//id=null 说明是新增
		if(null!=venueModel&&venueModel.getId()!=null){
			//修改数据库
			venueModel.setCreateTime(DateUtil.fSecond(new Date()));
			boolean flag = venueService.update(venueModel);
			if(flag){
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"修改成功",venueModel);
			} else {
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"修改失败",venueModel);
			}
		} else {
			venueModel.setId(UUIDUtil.getUUID().trim());
			venueModel.setCreateTime(DateUtil.fSecond(new Date()));
			boolean flag = venueService.insert(venueModel);
			if(flag){
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"新增成功",venueModel);
			} else {
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"新增失败",venueModel);
			}
		}

		return resultEntity;
	}

	//*********************************   导航图   *************************************************//
	@ApiOperation(value = "新增导航图", notes = "", response = String.class)
	@RequestMapping(value="/addNavigation",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity addNavigation(HttpServletRequest request, Model model, NavigationModel navigationModel) {
		boolean flag = false;
		ResultEntity resultEntity ;
		navigationModel.setCreateTime(DateUtil.fSecond(new Date()));
		flag = navigationService.insert(navigationModel);

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"新增成功",flag);

		return resultEntity;
	}

	@ApiOperation(value = "修改导航图", notes = "", response = String.class)
	@RequestMapping(value = "/updateNavigation",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity updateNavigation(HttpServletRequest request, Model model, NavigationModel navigationModel) {
		boolean flag = false;
		ResultEntity resultEntity ;
		navigationModel.setCreateTime(DateUtil.fSecond(new Date()));
		flag = navigationService.update(navigationModel);

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"修改成功",flag);

		return resultEntity;
	}

	@ApiOperation(value = "查询导航图", notes = "", response = String.class)
	@RequestMapping(value="/selectNavigationById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity selectNavigationById(@RequestParam("id") String id) {
		boolean flag = false;
		ResultEntity resultEntity;
		NavigationModel navigationModel = new NavigationModel();
		navigationModel.setId(id);
		NavigationModel bookModel = navigationService.getModel(navigationModel);

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",bookModel);

		return resultEntity;
	}

	@ApiOperation(value = "删除导航图", notes = "", response = String.class)
	@RequestMapping(value="/deleteNavigationById",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity deleteNavigationById(String id) {
		boolean flag = false;
		ResultEntity resultEntity;
		NavigationModel navigationModel = new NavigationModel();
		navigationModel.setId(id);
		flag = navigationService.delete(navigationModel);

		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"删除成功",flag);

		return resultEntity;
	}

	@ApiOperation(value = "查询导航图列表", notes = "", response = String.class)
	@RequestMapping(value="/selectNavigationListById",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity selectNavigationListById(HttpServletRequest request, Model model, NavigationModel navigationModel) {
		boolean flag = false;
		ResultEntity resultEntity;

		List<NavigationModel> bookModelList = navigationService.getModelList(navigationModel);
		resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE,"查询成功",bookModelList);

		return resultEntity;
	}


	//接口服务不可有相同的服务
	@ApiOperation(value = "导航图图片接口", notes = "", response = String.class)
	@RequestMapping(value="/saveorupdatePicForNavigation",method=RequestMethod.POST)
	public ResultEntity saveorupdatePicForVenue(HttpServletRequest request,
												HttpServletResponse response, Model model, NavigationModel navigationModel,@RequestParam(value="faceLegalFile", required=false) MultipartFile faceLegalFile){
		ResultEntity resultEntity = new ResultEntity();

		FileUntils fileunits=new FileUntils();

		String realpath=request.getSession().getServletContext().getRealPath("/");
		/**
		 *修改服务，判断是否属于进行了图片修改；
		 *是：删除原图片， 保存新上传图片
		 *否：只更新服务
		 */
		if(faceLegalFile!=null){

			/**
			 **是：保存新上传图片
			 */
			Map<String,Object> map = fileunits.savePic(faceLegalFile,realpath,FileUntils.PICPATHSERVICE);
			if(map!=null){
				if((boolean) map.get("issuccess")){
					//删除原文件
					fileunits.deletePic(realpath,navigationModel.getPicPath());
					//获取新文件
					String path = (String) map.get("path");
					navigationModel.setPicPath(path);
				}
			}
		}
		//id=null 说明是新增
		if(null!=navigationModel&&navigationModel.getId()!=null){
			//修改数据库
			navigationModel.setCreateTime(DateUtil.fSecond(new Date()));
			boolean flag = navigationService.update(navigationModel);
			if(flag){
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"修改成功",navigationModel);
			} else {
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"修改失败",navigationModel);
			}
		} else {
			navigationModel.setId(UUIDUtil.getUUID().trim());
			System.out.println();
			navigationModel.setCreateTime(DateUtil.fSecond(new Date()));
			boolean flag = navigationService.insert(navigationModel);
			if(flag){
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"新增成功",navigationModel);
			} else {
				resultEntity = new ResultEntity( ResultEntity.SUCCESS_CODE,"新增失败",navigationModel);
			}
		}

		return resultEntity;
	}


}
