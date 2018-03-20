package com.springboot.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;
import com.springboot.learning.entity.User;
import com.springboot.learning.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
	@Autowired
	private UserService userService;

	// 先清空，避免原先数据对测试的影响
	@Before
	public void setUp() {
		userService.deleteAll();
	}

	@Test
	public void userAction() throws Exception {
		int count = userService.countAll();
		Assert.assertEquals(0, count);

		userService.createUser("Li", 23);

		User user = userService.getUserByName("Li");
		Assert.assertEquals(23, user.getAge().intValue());
	}

}
