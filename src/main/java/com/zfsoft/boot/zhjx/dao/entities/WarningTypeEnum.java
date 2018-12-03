package com.zfsoft.boot.zhjx.dao.entities;

public enum WarningTypeEnum {
	
	//预警类型:0危险预警,1晚归预警,2假期预警,3消费预警,4成绩预警,5借阅预警
	
	WXYJ("WXYJ", 0), WGYJ("WGYJ", 1), JQYJ("JQYJ", 2), XFYJ("XFYJ", 3), CJYJ("CJYJ", 4), JYYJ("JYYJ", 5);  
    // 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private WarningTypeEnum(String name, int index) {  
        this.name = name;  
        this.index = index;  
    } 
    
    // 普通方法  
    public static String getName(int index) {  
        for (WarningTypeEnum c : WarningTypeEnum.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    // get set 方法  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
    
    public static void main(String[] args) {
		System.out.println(WarningTypeEnum.CJYJ.getIndex());
	}
}

