package com.zfsoft.boot.zhjx.setup.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zfsoft.boot.web.ZFBootMvcConfig;
import com.zfsoft.boot.web.ZFWebAutoConfiguration;
import com.zfsoft.boot.zhjx.setup.converter.DateConverter;
import com.zfsoft.security.algorithm.DesBase64Codec;

@Configuration
@Import(ZFBootMvcConfig.class)
@EnableWebMvc
@AutoConfigureAfter(value = { ZFWebAutoConfiguration.class})
public class BootWebMvcConfig implements WebMvcConfigurer {

	private DesBase64Codec dbencrypt = new DesBase64Codec();
	
	@Bean(name = "dbencrypt")
    public DesBase64Codec dbencrypt() {
        return dbencrypt;
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/wxpage/**").addResourceLocations("classpath:/templates/wxpage/");
	}
 
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }
}
