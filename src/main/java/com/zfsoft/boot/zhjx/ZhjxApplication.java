package com.zfsoft.boot.zhjx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zfsoft.boot.web.ZFBootConfig;

@EnableCaching(proxyTargetClass = true)
@ServletComponentScan(basePackages = { "com.zfsoft" })
@Import(ZFBootConfig.class)
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class ZhjxApplication implements CommandLineRunner{

	Logger logger = LoggerFactory.getLogger(ZhjxApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ZhjxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("start");
		logger.error("######################start######################");
	}
}
