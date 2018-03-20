package com.springboot.learning.service;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.learning.entity.City;

@Mapper
public interface CityUseXMLMapper {
	City findCityByName(@Param("name") String name);

	void insertCity(Map<String, Object> paramMap);

	void deleteAll();
}
