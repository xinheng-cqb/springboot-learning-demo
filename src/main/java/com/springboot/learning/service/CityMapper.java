package com.springboot.learning.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.springboot.learning.entity.City;

/**
 * @author caiqibin
 * @date 2017年7月17日
 * @introduce:创建City映射的操作CityMapper，实现各种对数据库的操作
 */
// 缓存模块相关的注解，可以先注释掉
@CacheConfig
@Mapper
public interface CityMapper {
	// 缓存模块相关的注解，要求与ehcache.xml中的名字对应，可以先注释掉
	@Cacheable(cacheNames = "cityMapperFindName")
	// @Param中定义的name对应了SQL中的#{name}
	@Select("select * from t_city_info where name = #{name}")
	City findCityByName(@Param("name") String name);

	// @Result中的property属性对应City对象中的成员名，column对应SELECT出的字段名。
	@Results({ @Result(property = "id", column = "id"), @Result(property = "name", column = "name"), @Result(property = "score", column = "score") })
	@Select("select * from t_city_info")
	List<City> finaAll();

	@Insert("insert into t_city_info(name,score) values(#{name},#{score})")
	void insertCity(@Param("name") String name, @Param("score") Integer score);

	// 需要在paramMap中填入同名的内容
	@Insert("insert into  t_city_info(name,score) values(#{name,jdbcType=VARCHAR}, #{score,jdbcType=INTEGER})")
	void insertCityWithMap(Map<String, Object> paramMap);

	// 保证数据在更新到数据库中时，同步更新redis缓存中的数据
	@CachePut(key = "#p0.name")
	// 语句中的#{name}、#{age}就分别对应了City对象中的name和age属性
	@Insert("insert into t_city_info(name,score) values(#{name},#{score})")
	void insertCityWithEntity(City city);

	@Delete("delete from t_city_info")
	void deleteAll();
}
