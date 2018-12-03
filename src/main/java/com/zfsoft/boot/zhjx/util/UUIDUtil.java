package com.zfsoft.boot.zhjx.util;

import java.util.UUID;

public class UUIDUtil {

	public static String getUUID(){    
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");    
		return uuid;    
	}   
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
