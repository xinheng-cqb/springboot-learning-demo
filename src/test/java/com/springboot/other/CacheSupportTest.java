package com.springboot.other;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;
import com.springboot.learning.entity.City;
import com.springboot.learning.service.CityMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CacheSupportTest {
	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private CacheManager cacheManager;

	@Before
	public void before() {
		cityMapper.insertCity("cache", 100);
	}

	@Test
	public void test() {
		City city1 = cityMapper.findCityByName("cache");
		System.out.println("第一次查询：" + city1.getScore());

		City city2 = cityMapper.findCityByName("cache");
		System.out.println("第二次查询：" + city2.getScore());
	}
}
