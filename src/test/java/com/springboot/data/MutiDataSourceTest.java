package com.springboot.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MutiDataSourceTest {
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate primaryJdbcTemplate;

	@Autowired
	@Qualifier("secondJdbcTemplate")
	private JdbcTemplate secondJdbcTemplate;

	// 先清空，避免原先数据对测试的影响
	@Before
	public void setUp() {
		primaryJdbcTemplate.update("DELETE  FROM  USER ");
		secondJdbcTemplate.update("DELETE  FROM  USER ");
	}

	@Test
	public void test() throws Exception {
		// 往主数据源中插入两条数据
		primaryJdbcTemplate.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
		primaryJdbcTemplate.update("insert into user(id,name,age) values(?, ?, ?)", 2, "bbb", 30);
		// 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
		secondJdbcTemplate.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 21);
		// 查一下主数据源中是否有两条数据，验证插入是否成功
		Assert.assertEquals("2", primaryJdbcTemplate.queryForObject("select count(1) from user", String.class));
		// 查一下第二个数据源中是否有一条数据，验证插入是否成功
		Assert.assertEquals("1", secondJdbcTemplate.queryForObject("select count(1) from user", String.class));
	}
}
