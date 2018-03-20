package com.springboot.learning.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author caiqibin
 * @date 2017年8月8日
 * @introduce:
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {
	@RabbitHandler
	public void process(String info) {
		System.out.println("Receiver :" + info);
	}
}
