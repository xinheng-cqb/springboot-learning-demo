package com.springboot.learning.swagger;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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

/**
 * @author caiqibin
 * @date 2017年7月10日
 * @introduce:该类代码基于CityController进行改动，通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
 */
@RestController
// 通过这里配置使下面的映射都在/swagger下
@RequestMapping("/swagger")
public class Swagger2DemoController {
	// 创建线程安全的Map
	private static Map<Long, City> cityMap = Collections.synchronizedMap(new HashMap<Long, City>(10));

	@ApiOperation(value = "获取城市列表", notes = "")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<City> getCityList() {
		// 处理"/swagger/"的GET请求，用来获取城市列表
		List<City> cityList = new ArrayList<City>(cityMap.values());
		return cityList;
	}

	@ApiOperation(value = "创建城市", notes = "根据City实体来创建")
	@ApiImplicitParam(name = "city", value = "城市信息实体City", required = true, dataType = "City")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postCity(@ModelAttribute City city) {
		// 处理"/swagger/"的POST请求，用来创建City
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		cityMap.put(city.getId(), city);
		return "SUCCESS";
	}

	@ApiOperation(value = "获取城市详细信息", notes = "根据url的id来获取城市详细信息")
	@ApiImplicitParam(name = "id", value = "城市ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public City getCity(@PathVariable Long id) {
		// 处理"/swagger/{id}"的GET请求，用来获取url中id值的City信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return cityMap.get(id);
	}

	@ApiOperation(value = "更新城市详细信息", notes = "根据url的id来指定更新对象，并根据传过来的city信息来更新城市详细信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "城市ID", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "city", value = "城市详细实体City", required = true, dataType = "City") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putCity(@PathVariable(value = "id") Long id, @ModelAttribute City city) {
		// 处理"/swagger/{id}"的PUT请求，用来更新City信息
		City originCity = cityMap.get(id);
		originCity.setName(city.getName());
		originCity.setScore(city.getScore());
		cityMap.put(id, originCity);
		return "SUCCESS";
	}

	@ApiOperation(value = "删除城市", notes = "根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "城市ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		// 处理"/swagger/{id}"的DELETE请求，用来删除City
		cityMap.remove(id);
		return "SUCCESS";
	}
}