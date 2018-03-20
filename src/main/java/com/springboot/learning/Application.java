package com.springboot.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
// 该注解使得定时任务的配置生效，不用时可以去掉
// @EnableScheduling
// 该注解使得异步调用的配置生效，不用时可以去掉
@EnableAsync
// 该注解使得缓存用启用
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
