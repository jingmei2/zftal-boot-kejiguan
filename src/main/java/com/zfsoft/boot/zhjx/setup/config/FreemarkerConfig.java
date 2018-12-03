package com.zfsoft.boot.zhjx.setup.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.zfsoft.boot.freemarker.template.method.CapacityUnitMethod;
import com.zfsoft.boot.freemarker.template.method.EnvironmentMethod;
import com.zfsoft.boot.freemarker.template.method.VersionMethod;
import com.zfsoft.boot.shiro.template.method.ValidateCaptcha;
import com.zfsoft.web.utils.WebRequestUtils;
import com.zfsoft.webmvc.template.method.MessageMethod;

@Configuration
public class FreemarkerConfig {
	
	@Autowired
	private MessageMethod messageMethod;
	@Autowired
	private CapacityUnitMethod capacityUnit;
	@Autowired
	private VersionMethod versionMethod;
	@Autowired
	private ValidateCaptcha validateCaptcha;
	@Autowired
	private EnvironmentMethod envMethod;
	
	 
	/**
	 * 增加自定义视图变量和方法
	 * 
	 * @return
	 */
	@Bean
	public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				// 增加视图
				// resolver.setViewClass(MyFreemarkerView.class);
				// 添加自定义解析器
				Map<String, Object> map = resolver.getAttributesMap();

				map.put("validateCaptcha", validateCaptcha);
				map.put("capacityUnit", capacityUnit);
				map.put("messageUtil", messageMethod);
				map.put("versionUtil", versionMethod);
				map.put("envMethod", envMethod);
				
			}
		};
	}

	public class MyFreemarkerView extends FreeMarkerView {

		public void init() {

		}

		protected MyFreemarkerView() {
			super();
		}

		@Override
		protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
			model.put("hostPath", WebRequestUtils.getHostPath(request));
			model.put("base", request.getContextPath());
			super.exposeHelpers(model, request);
		}

	}

}
