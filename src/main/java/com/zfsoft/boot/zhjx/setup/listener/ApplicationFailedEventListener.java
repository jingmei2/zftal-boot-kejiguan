/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.zhjx.setup.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 
 * @className	： MyApplicationFailedEventListener
 * @description	： ApplicationFailedEvent:spring boot启动异常时执行事件 
 * @author 		：万大龙（743）
 * @date		： 2017年9月1日 下午3:05:15
 * @version 	V1.0
 */
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        Throwable throwable = event.getException();
        handleThrowable(throwable);
    }

    /*处理异常*/
    private void handleThrowable(Throwable throwable) {
    }

}
