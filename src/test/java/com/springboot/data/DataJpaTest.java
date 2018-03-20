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
import com.springboot.learning.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DataJpaTest {
	@Autowired
	private UserRepository userRepository;

	// 先清空，避免原先数据对测试的影响
	@Before
	public void setUp() {
		userRepository.deleteAll();
	}

	@Test
	public void dataJpaTest() throws Exception {
		userRepository.save(new User("aaa", 1));
		userRepository.save(new User("bbb", 2));
		userRepository.save(new User("ccc", 3));
		userRepository.save(new User("ddd", 4));
		userRepository.save(new User("eee", 5));
		Long count = userRepository.count();
		Assert.assertEquals(5, count.intValue());

		User user = userRepository.findByName("bbb");
		Assert.assertEquals(2, user.getAge().intValue());

		user = userRepository.findUser("eee");
		Assert.assertEquals(5, user.getAge().intValue());

		userRepository.delete(user);
		int size = userRepository.findAll().size();
		Assert.assertEquals(4, size);
	}
}
