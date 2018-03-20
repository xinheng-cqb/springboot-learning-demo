package com.springboot.learning.service;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.learning.entity.UserSecurityInfo;

@Mapper
public interface UserSecurityMapper {
	UserSecurityInfo getInfoByName(String username);
}
