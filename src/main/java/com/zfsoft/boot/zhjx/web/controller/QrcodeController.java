package com.zfsoft.boot.zhjx.web.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zfsoft.boot.zhjx.dao.entities.ScanPool;
import com.zfsoft.boot.zhjx.service.svcinterface.IUserService;


@Controller
@RequestMapping("/qrCode")
public class QrcodeController {

	@Autowired
	private IUserService userService;

	private HashMap<String, Object> userMap = new HashMap<>();


	@RequestMapping("/getUuid")
	@ResponseBody
	public int index(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model)
	{
	    System.out.println("进入首页,先生成UUID");

	    //int a =  (int) (Math.random() * 100);

	    int a = 1;
	    session.setAttribute("uuid", a);

	    model.addAttribute("uuid", a);

	    System.out.println("id=" + a);

	    return a;
	}

	@RequestMapping("/qrcode/{uuid}")
    @ResponseBody
    String createQRCode(@PathVariable String uuid,HttpServletResponse response)
    {
        System.out.println("生成二维码");

        //String text = "http://boot.free.ngrok.cc/qrCode/login/"+uuid;
        String text = "http://10.71.33.70:9092/qrCode/login/"+uuid;
        int width = 220;
        int height = 220;
        String format = "png";
        //将UUID放入缓存
        ScanPool pool = new ScanPool();
        //PoolCache.cacheMap.put(uuid, pool);
        try
        {
            Map<EncodeHintType, Object> hints= new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //hints.put(EncodeHintType.MARGIN, 1);
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //容错率
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, response.getOutputStream());
        } catch (WriterException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


	/*@RequestMapping("/pool")
    @ResponseBody
    String pool(String uuid,HttpSession session,HttpServletRequest request){
        System.out.println("检测["+uuid+"]是否登录");

        //ScanPool pool = PoolCache.cacheMap.get(uuid);

        if(pool == null){
            return "timeout";
        }

        //使用计时器，固定时间后不再等待扫描结果--防止页面访问超时
       // new Thread(new ScanCounter(uuid)).start();
//        boolean scanFlag = pool.getScanStatus();
        if(scanFlag){
        	System.out.println("扫码成功，设置session");
        	YhglModel user = new YhglModel();
        	Subject subject = SubjectUtils.getSubject();
        	user = (YhglModel) userMap.get(uuid);
        	ZfSsoToken token = new ZfSsoToken(user.getYhm(), WebRequestUtils.getRemoteAddr(request), false);
        	subject.login(token);
//        	session.setAttribute("user", user);
            return "success";
        }else{
        	System.out.println("稍微扫码，5秒后再次检测");
            return "fail";
        }
    }  */
/*
	@RequestMapping("/login/{uuid}")
	@ResponseBody
    String login(@PathVariable String uuid,Model model){

        ScanPool pool = PoolCache.cacheMap.get(uuid);

        if(pool == null){
        	return "登录失败，请刷新重试";
        }
        //登录


        YhglModel user = userService.getUserByUuid(uuid);
        if (user == null) {
			return "请先注册用户";
		}
        userMap.put(uuid, user);
        pool.scanSuccess();
        return "登陆成功";
    }  */


}
