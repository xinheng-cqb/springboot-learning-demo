package com.springboot.learning.mq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author caiqibin
 * @date 2017年8月8日
 * @introduce:
 */
@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String content = "Hello , this is server ,date is " + new Date();
		this.rabbitTemplate.convertAndSend("hello", content);
	}
}
