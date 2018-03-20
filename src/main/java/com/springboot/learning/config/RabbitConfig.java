package com.springboot.learning.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author caiqibin
 * @date 2017年8月8日
 * @introduce:
 */
@Configuration
public class RabbitConfig {
	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}
}
