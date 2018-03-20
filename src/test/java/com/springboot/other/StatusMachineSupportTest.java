package com.springboot.other;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;
import com.springboot.learning.enums.OrderEvent;
import com.springboot.learning.enums.OrderStatus;

/**
 * @author caiqibin
 * @date 2017年8月15日
 * @introduce:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StatusMachineSupportTest {
	@Autowired
	private StateMachine<OrderStatus, OrderEvent> stateMachine;

	@Test
	public void test() {
		stateMachine.start();
		stateMachine.sendEvent(OrderEvent.PAY);
		stateMachine.sendEvent(OrderEvent.RECEIVE);
	}
}
