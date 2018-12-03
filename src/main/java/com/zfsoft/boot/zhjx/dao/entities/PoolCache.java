package com.zfsoft.boot.zhjx.dao.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PoolCache {

	 //缓存超时时间 10分钟  
    private static Long timeOutSecond = 2 * 60 * 60L;  
      
    //每2小时清理一次缓存  
    private static Long cleanIntervalSecond = 2 * 60 * 60L;  
      
    public static ConcurrentHashMap<String, ScanPool> cacheMap = new ConcurrentHashMap<String, ScanPool>();  
      
    static{  
        new Thread(new Runnable()  
        {  
              
            @Override  
            public void run()  
            {  
                // TODO Auto-generated method stub  
                while (true)  
                {  
                    try  
                    {  
                        Thread.sleep(cleanIntervalSecond*1000);  
                    } catch (InterruptedException e)  
                    {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    }  
                    clean();  
                }  
            }  
              
            public void clean(){  
                if(cacheMap.keySet().size() > 0){  
                    Iterator<String> iterator = cacheMap.keySet().iterator();  
                    while (iterator.hasNext())  
                    {  
                        String key = iterator.next();  
                        ScanPool old = cacheMap.get(key);  
                        if(System.currentTimeMillis() - old.getCreateTime() > timeOutSecond * 1000){  
                            cacheMap.remove(key);  
                        }  
                    }  
                }  
            }  
        }).start();  
    }  
  
	
}
