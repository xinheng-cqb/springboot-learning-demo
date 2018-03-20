package com.springboot.learning.service;

import com.springboot.learning.entity.User;

/**
 * @author caiqibin
 * @date 2017年7月11日
 * @introduce:用户数据操作接口
 */
public interface UserService {

	void createUser(String name, int age);

	User getUserByName(String name);

	int countAll();

	void deleteAll();

}
