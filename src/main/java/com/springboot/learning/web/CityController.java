package com.springboot.learning.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.learning.entity.City;

@RestController
// 通过这里配置使下面的映射都在/city下
@RequestMapping("/city")
public class CityController {
	// 创建线程安全的Map
	private static Map<Long, City> cityMap = Collections.synchronizedMap(new HashMap<Long, City>(10));

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<City> getCityList() {
		// 处理"/city/"的GET请求，用来获取城市列表
		List<City> cityList = new ArrayList<City>(cityMap.values());
		return cityList;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postCity(@ModelAttribute City city) {
		// 处理"/city/"的POST请求，用来创建City
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		cityMap.put(city.getId(), city);
		return "SUCCESS";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public City getCity(@PathVariable(value = "id") Long id) {
		// 处理"/city/{id}"的GET请求，用来获取url中id值的City信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return cityMap.get(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putCity(@PathVariable(value = "id") Long id, @ModelAttribute City city) {
		// 处理"/city/{id}"的PUT请求，用来更新City信息
		City originCity = cityMap.get(id);
		originCity.setName(city.getName());
		originCity.setScore(city.getScore());
		cityMap.put(id, originCity);
		return "SUCCESS";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable(value = "id") Long id) {
		// 处理"/city/{id}"的DELETE请求，用来删除City
		cityMap.remove(id);
		return "SUCCESS";
	}
}
