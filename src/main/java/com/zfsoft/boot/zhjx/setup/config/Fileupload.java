package com.zfsoft.boot.zhjx.setup.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by  Administrator on 2018-12-27 02:14 PM
 * @author Magi
 */
@Configuration
public class Fileupload {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小  测试后从配置取值。
        factory.setMaxFileSize("50MB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
}
