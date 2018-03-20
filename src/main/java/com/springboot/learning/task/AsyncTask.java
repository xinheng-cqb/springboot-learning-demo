package com.springboot.learning.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

	public void firstTask() {
		System.out.println("---------同步调用1-------");
	}

	@Async
	public void secondTask() {
		System.out.println("---------异步调用2-------");
	}

	@Async
	public void thirdTask() {
		System.out.println("---------异步调用3-------");
	}
}
