package com.zfsoft.boot.zhjx.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 腾讯鉴权签名工具
 * @author liucb
 *
 */
public class TencentUntil {
	/**
	 * <p>腾讯接口访问地址<p/>
	 * HTTP改为HTTPS 不然会报错
	 * By MaJing
	 */
	private static final String TENCENT_BASE_URL = "https://openapi.mta.qq.com";

	public static void main(String[] args) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("app_id", "3103363952");
		params.put("start_date", "2018-01-02");
		params.put("end_date", "2018-01-31");
		params.put("idx", "10101,10102,10103,10104,10105");

		String result = getUrlWithSign("GET", "/ctr_user_basic/get_offline_data", params, "AA5Y37ME5CMK");
		System.out.println(result);
	}

	public static String hmac_sha1(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            // Convert raw bytes to Hex
            String hexBytes = byte2hex(rawHmac);

            return hexBytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	private static String byte2hex(final byte[] b){
	    String hs="";
	    String stmp="";
	    for (int n=0; n<b.length; n++){
	        stmp=(java.lang.Integer.toHexString(b[n] & 0xFF));
	        if (stmp.length()==1) hs=hs+"0"+stmp;
	            else hs=hs+stmp;
	    }
	    return hs;
	}


	public static String getUrlWithSign(String requestMethod,String url,Map<String,Object> paramsMap,String appKey) {
		/**
		 * 1、将请求URI进行url编码，得到：%2Fctr_active_anal%2Fget_offline_data
		 * 2、将参数列表，移除sign后，进行字典升序排序
		 * 3、将上步得到的字典升序的字典(k=v)用'&'拼接起来，并进行url编码，得到：app_id%3D3100955822%26end_date%3D2015-08-17%26idx%3D10201%2C10202%2C10203%26start_date%3D2015-07-01
		 * 4、将请求方式（GET）、以及1、3得到的串用'&'拼接起来，得到：GET&%2Fctr_active_anal%2Fget_offline_data&app_id%3D3100955822%26end_date%3D2015-08-17%26idx%3D10201%2C10202%2C10203%26start_date%3D2015-07-01
		 * 5、AppKey末尾追加一个字节的‘&’，即{APPKEY}&，例如：AU2EF43EYR1L&
		 * 6、使用HMAC-SHA1加密算法，将Step2中的到的源串以及Step3中得到的密钥进行加密，并以原始二进制格式输出。
		 * 7、然后将加密后的字符串经过md5编码，得到sign签名密钥
		 */
		try {
			String encodedUrl = java.net.URLEncoder.encode(url, "UTF-8");

			Collection<String> keyset= paramsMap.keySet();
			List<String> list= new ArrayList<String>(keyset);
			Collections.sort(list);
			String paramsStr="";
			for(int i=0;i<list.size();i++){
			   if(i==0) {
				   if(paramsMap.containsKey(list.get(i))){
					   paramsStr += list.get(i)+"="+paramsMap.get(list.get(i));
				   }
			   }else {
				   if(paramsMap.containsKey(list.get(i))){
					   paramsStr += ("&"+list.get(i)+"="+paramsMap.get(list.get(i)));
				   }
			   }
			}

			String encodedParamsStr = java.net.URLEncoder.encode(paramsStr, "UTF-8");
			String totalUrlStr = requestMethod+"&"+encodedUrl+"&"+encodedParamsStr;
			String appKeyAnd = appKey+"&";

			totalUrlStr = totalUrlStr.replace("~","%7E");
			appKey = appKey.replace('-','+').replace('_','/');
			String hash = hmac_sha1(totalUrlStr, appKeyAnd);
	        MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(hash.getBytes());
            byte[] resultByteArray = md.digest();
            String sign = new BigInteger(1, resultByteArray).toString(16);
            String totalUrlWithSign = TENCENT_BASE_URL+url+"?"+paramsStr+"&sign="+sign;
			return totalUrlWithSign;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
