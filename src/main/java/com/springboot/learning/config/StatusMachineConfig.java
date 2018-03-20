package com.springboot.learning.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.springboot.learning.enums.OrderEvent;
import com.springboot.learning.enums.OrderStatus;

/**
 * @author caiqibin
 * @date 2017年8月15日
 * @introduce: 状态机配置类
 */
@Configuration
// 启用Spring StateMachine状态机功能
@EnableStateMachine
public class StatusMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEvent> {

	// 初始化当前状态机拥有哪些状态，其中initial(States.UNPAID)定义了初始状态为UNPAID，states(EnumSet.allOf(States.class))则指定了类中定义的所有状态作为该状态机的状态定义
	@Override
	public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states) throws Exception {
		states.withStates().initial(OrderStatus.UNPAID).states(EnumSet.allOf(OrderStatus.class));
	}

	// 用来初始化当前状态机有哪些状态迁移动作，其中命名中我们很容易理解每一个迁移动作，都有来源状态source，目标状态target以及触发事件event
	@Override
	public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions) throws Exception {
		transitions.withExternal().source(OrderStatus.UNPAID).target(OrderStatus.WAITING_FOR_RECEIVE).event(OrderEvent.PAY).and().withExternal()
				.source(OrderStatus.WAITING_FOR_RECEIVE).target(OrderStatus.DONE).event(OrderEvent.RECEIVE);
	}

}
