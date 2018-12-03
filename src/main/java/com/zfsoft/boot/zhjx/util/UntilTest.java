package com.zfsoft.boot.zhjx.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class UntilTest {
		 public static void main(String[] args) throws UnsupportedEncodingException {
			String key = "AA5Y37ME5CMK&";
			/**
	         * The data that we're going to hash
	         * method GET
	         * path /ctr_active_anal/get_offline_data
	         * app_id 3102595408
	         * end_date 2015-08-17
	         * start_date 2015-07-01
	         * idx 10201,10202,10203
	        */
			String requestMethod = "GET";
			String url = "/ctr_user_basic/get_offline_data";
			String paramsStr = "app_id=3103363952&end_date=2018-07-06&idx=10101,10102&start_date=2015-07-05";
			System.out.println(requestMethod+"&"+java.net.URLEncoder.encode(url,"UTF-8")+"&"+java.net.URLEncoder.encode(paramsStr,"UTF-8"));
			
			String toHash = requestMethod+"&"+java.net.URLEncoder.encode(url,"UTF-8")+"&"+java.net.URLEncoder.encode(paramsStr,"UTF-8");
		   // String toHash =  "GET&%2Fctr_active_anal%2Fget_offline_data&app_id%3D3102595408%26end_date%3D2015-08-17%26idx%3D10201%2C10202%2C10203%26start_date%3D2015-07-01";
			System.out.println(toHash);
		    toHash = toHash.replace("~","%7E");
			key = key.replace('-','+').replace('_','/');
			String hash = hmac_sha1(toHash, key);
		    try{
		       MessageDigest md = MessageDigest.getInstance("MD5");
	           md.update(hash.getBytes());
	           byte[] resultByteArray = md.digest();
	           String sign = new BigInteger(1, resultByteArray).toString(16);
		       System.out.println(sign);
		       System.out.println(url+"?"+paramsStr+"&sign="+sign);
		    }catch(java.security.NoSuchAlgorithmException ex){

		    }

			
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
		
}
