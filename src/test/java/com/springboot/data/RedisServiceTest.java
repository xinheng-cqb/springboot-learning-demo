package com.springboot.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;
import com.springboot.learning.entity.RedisTestEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisServiceTest {
	// 自带的String类型对象
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, RedisTestEntity> redisTestEntityTemplate;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("test", "value 123");
		Assert.assertEquals("value 123", stringRedisTemplate.opsForValue().get("test"));

		RedisTestEntity first = new RedisTestEntity(23L, "test23");
		redisTestEntityTemplate.opsForValue().set("first", first);
		RedisTestEntity second = new RedisTestEntity(52L, "52Test");
		redisTestEntityTemplate.opsForValue().set("second", second);
		Assert.assertEquals(23L, redisTestEntityTemplate.opsForValue().get("first").getId().longValue());
		Assert.assertEquals("52Test", redisTestEntityTemplate.opsForValue().get("second").getName());
	}

}
