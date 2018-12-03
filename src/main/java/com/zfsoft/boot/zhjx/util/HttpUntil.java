package com.zfsoft.boot.zhjx.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Http网络请求工具
 * @author liucb
 *
 */
public class HttpUntil {
	private static final String REQUEST_POST = "POST" ;
    private static final String REQUEST_GET = "GET" ;

    /**
     * 参数拼接
     * @param paramMap
     * @return
     */
    private   static  String prepareParam(Map<String,Object> paramMap){
        StringBuffer sb = new  StringBuffer();
        if (paramMap.isEmpty()){
            return   ""  ;
        }else {
            for (String key: paramMap.keySet()){
                String value = (String)paramMap.get(key);
                if (sb.length()< 1 ){
                    sb.append(key).append("=" ).append(value);
                }else {
                    sb.append("&" ).append(key).append( "=" ).append(value);
                }
            }
            return  sb.toString();
        }
    }

    /**
     * 发送post请求
     * @param urlStr
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String  doPost(String urlStr,Map<String,Object> paramMap) throws Exception{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(REQUEST_POST);
        String paramStr = prepareParam(paramMap);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.write(paramStr.getBytes("UTF-8"));
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line ;
        String result ="";
        while( (line =br.readLine()) != null ){
            result += ("\n"+line);
        }
        br.close();
        return result;

    }

    /**
     * 发送get请求
     * @param urlStr
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String doGet(String urlStr,Map<String,Object> paramMap) throws Exception{
        String paramStr = prepareParam(paramMap);
        if(paramStr == null || paramStr.trim().length()<1){

        }else{
            urlStr +="?"+paramStr;
        }
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(REQUEST_GET);
        conn.setRequestProperty("Content-Type","text/html; charset=UTF-8");

        conn.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line ;
        String result ="";
        while( (line =br.readLine()) != null ){
            result += line;
        }
        br.close();
        return result;
    }

    public static void main(String[] args) {
    	  String urlStr = "http://10.71.33.67/zftal-mobile/appCenter/appCenter_getSubMapList.html";
          Map<String,Object> paramMap = new HashMap<String,Object>();
          paramMap.put("name", "紫金港");
          try {
              System.out.println("------------------------------");
              System.out.println(HttpUntil.doPost(urlStr, paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
      }
}
