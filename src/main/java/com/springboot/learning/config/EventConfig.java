package com.springboot.learning.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author caiqibin
 * @date 2017年8月15日
 * @introduce: 所有StateMachineListener接口中定义的事件都能通过注解的方式来进行配置实现
 */
@WithStateMachine
public class EventConfig {
	private final Log logger = LogFactory.getLog(EventConfig.class);

	@OnTransition(target = "UNPAID")
	public void create() {
		logger.info("订单创建，待支付");
	}

	@OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
	public void pay() {
		logger.info("用户完成支付，待收货");
	}

	@OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
	public void receive() {
		logger.info("用户已收货，订单完成");
	}
}
