package com.springboot.learning.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

	@Scheduled(initialDelay = 300000, fixedRate = 5000)
	public void timerWork() {
		System.out.println("running ....");
	}
}
