package com.springboot.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;
import com.springboot.learning.entity.City;
import com.springboot.learning.service.CityMapper;
import com.springboot.learning.service.CityUseXMLMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MyBatisSupportTest {
	// 使用注解配置
	@Autowired
	private CityMapper cityMapper;

	// 使用xml文件配置
	@Autowired
	private CityUseXMLMapper cityUseXMLMapper;

	@Before
	public void setUp() throws Exception {
		cityMapper.deleteAll();
	}

	@Test
	public void test() throws Exception {
		City city = new City(null, "bj", 82);
		cityMapper.insertCityWithEntity(city);
		Map<String, Object> paramMap = new HashMap<String, Object>(2);
		paramMap.put("name", "sh");
		paramMap.put("score", 84);
		cityMapper.insertCityWithMap(paramMap);
		cityMapper.insertCity("hz", 86);
		city = cityMapper.findCityByName("hz");
		Assert.assertEquals(86, city.getScore().intValue());
		List<City> cityList = cityMapper.finaAll();
		Assert.assertEquals(3, cityList.size());
	}

	@Test
	public void testXML() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>(2);
		paramMap.put("name", "wz");
		paramMap.put("score", 80);
		cityUseXMLMapper.insertCity(paramMap);
		City city = cityMapper.findCityByName("wz");
		Assert.assertEquals(80, city.getScore().intValue());
	}
}
