package com.zfsoft.boot.zhjx.util;

public class Cache {   
    private String key;//缓存ID   
    private Object value;//缓存数据   
    private long timeOut;//更新时间   

    public Cache() {   
            super();   
    }   

    public Cache(String key, Object value, long timeOut) {   
            this.key = key;   
            this.value = value;   
            this.timeOut = timeOut;   
      
    }   

    public String getKey() {   
            return key;   
    }   

    public long getTimeOut() {   
            return timeOut;   
    }   

    public Object getValue() {   
            return value;   
    }   

    public void setKey(String string) {   
            key = string;   
    }   

    public void setTimeOut(long l) {   
            timeOut = l;   
    }   

    public void setValue(Object object) {   
            value = object;   
    }   

}   