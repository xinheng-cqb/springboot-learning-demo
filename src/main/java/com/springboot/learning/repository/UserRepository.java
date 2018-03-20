package com.springboot.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.learning.entity.User;

/**
 * @author caiqibin
 * @date 2017年7月12日
 * @introduce:针对User实体创建对应的Repository接口实现对该实体的数据访问，泛型的第一个参数是要访问的实体类型，第二个参数是主键类型。 而且不需要再编写具体的实现了，
 * JpaRepository接口本身已经实现了创建
 * （save）、更新（save）、删除（delete）、查询（findAll、findOne）等基本操作的函数，因此对于这些基础操作的数据访问就不需要再自己定义。
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	// 通过解析方法名创建查询
	User findByName(String name);

	// 通过使用@Query 注解来创建查询，只需要编写JPQL语句，并通过类似“:name”来映射@Param指定的参数
	@Query("from User u where u.name=:name")
	User findUser(@Param("name") String name);
}
