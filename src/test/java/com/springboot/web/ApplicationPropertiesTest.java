package com.springboot.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationPropertiesTest {
	@Value("${com.springboot.learning.name}")
	private String name;

	/**
	 * @introduce:自定义属性测试
	 * @throws Exception
	 * @return void
	 */
	@Test
	public void param() throws Exception {
		Assert.assertEquals(name, "springboot_learning_demo");
	}
}
