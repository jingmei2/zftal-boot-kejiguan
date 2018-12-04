package com.zfsoft.boot.zhjx;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching(proxyTargetClass = true)
@ServletComponentScan(basePackages = { "com.zfsoft","com.zfsoft.boot" })
@ComponentScan(basePackages = {"com.zfsoft.boot.*"})
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class KejiguanApplication implements CommandLineRunner{

	Logger logger = LoggerFactory.getLogger(KejiguanApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(KejiguanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("start");
		logger.error("######################start######################");
	}
}
