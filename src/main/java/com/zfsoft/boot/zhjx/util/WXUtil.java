package com.zfsoft.boot.zhjx.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zfsoft.boot.zhjx.dao.entities.PoolCache;
import com.zfsoft.boot.zhjx.dao.entities.ScanPool;



public class WXUtil {
	private static String appid = "wx8b0c08eb508e8da2";// 公众号AppID
	private static String secret = "41737b5afaeff716c80fd29980489be6";// 公众号凭证密钥

	/**
	 * 获取AccessToken 7200秒过期时重新获取
	 * 
	 * @return
	 */
	public static String getAccessToken() {
		String access_token = "";
		/*ScanPool cacheToken = PoolCache.cacheMap.get("access_token");
		if (cacheToken != null) {
			if (cacheToken.getCacheToken() != null) {
				System.out.println("取的是缓存里的token");
				return cacheToken.getCacheToken();
			}
		}*/
		try {
			String action = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+ appid + "&secret=" + secret;
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String json = new String(jsonBytes, "UTF-8");
			access_token = parseJson(json, "access_token");// access_token
			//token存入缓存
			ScanPool pool = new ScanPool(); 
			pool.setCacheToken(access_token);
			PoolCache.cacheMap.put("access_token", pool);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}


	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @param openid
	 * @return
	 */
	public static String getUserInfo(String openid,
			String access_token) {
		String json = "";
		try {
			String action = "https://api.weixin.qq.com/sns/userinfo?access_token="
					+ access_token + "&openid=" + openid + "&lang=zh_CN";
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			json = new String(jsonBytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 获取用户openid
	 * 
	 * @param code
	 * @return
	 */
	public static String getOpenid(String code) {
		String grant_type = "authorization_code";
		String action = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ appid
				+ "&secret="
				+ secret
				+ "&code="
				+ code
				+ "&grant_type=" + grant_type;
		String json = "";
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			OutputStream os = http.getOutputStream();
			os.flush();
			os.close();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			json = new String(jsonBytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}


	/**
	 * 获取ticker
	 * 
	 * @param access_token
	 * @return
	 */
	public static String getJsapiTicket(String access_token) {
		String ticket = "";
		try {
			String action = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
					+ access_token + "&type=jsapi";
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String json = new String(jsonBytes, "UTF-8");
			ticket = parseJson(json, "ticket");// access_token
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}


	public static String signature(String jsapi_ticket, String timestamp,
			String noncestr, String url) {
		jsapi_ticket = "jsapi_ticket=" + jsapi_ticket;
		timestamp = "timestamp=" + timestamp;
		noncestr = "noncestr=" + noncestr;
		url = "url=" + url;
		String[] arr = new String[] { jsapi_ticket, timestamp, noncestr, url };
		// 将token、timestamp、nonce,url参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
			if (i != arr.length - 1) {
				content.append("&");
			}
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		content = null;
		return tmpStr;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {

		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}

	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}


	/**
	 * 传入json字符串、key获取对应值
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static String parseJson(String json, String key) {
		JSONObject obj = (JSONObject) JSON.parse(json);
		return obj.getString(key);
	}

	public static void main(String[] args) {
		String a = parseJson(
				"{\"access_token\":\"RxfCxYkKcm59Cl413q3kvelMkehkUp7x"
						+ "RGmcpi1wTqkw-c58-HcaD_u_ek8JeiAnZGPbRBi5vEq2c5zSTIzCod30MsC79eOBhX0vFoB_9-M\",\"ex"
						+ "pires_in\":7200,\"refresh_token\":\"WEvR0UwDWqQ-4koZ65xZwtXjpDrH4xn7Qx240EnTxpOGc1vx"
						+ "pA9y24VcwXqyuHg-fTmCEp65E-SRsmKVMXFPfsy7vNlIa1NQ75D5xaxf8AE\",\"openid\":\"oCY-JwxwN"
						+ "yUjcbzhUHo6FDcFXCx8\",\"scope\":\"snsapi_userinfo\"}",
				"openid");
		System.err.println(a);
	}
}
