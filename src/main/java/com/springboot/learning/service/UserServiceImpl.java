package com.springboot.learning.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.springboot.learning.entity.User;

/**
 * @author caiqibin
 * @date 2017年7月11日
 * @introduce:用户数据操作实现
 */
@Service
public class UserServiceImpl implements UserService {

	// Spring的JdbcTemplate是自动配置的，但数据源是可以直接使用，不用添加@Qualifier，这里添加是因为后面用了多数据源
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createUser(String name, int age) {
		jdbcTemplate.update("insert into user(name, age) values(?, ?)", name, age);

	}

	@Override
	public User getUserByName(final String name) {
		List<User> userList = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "select * from user  where name = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				return ps;
			}

		}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				return user;
			}
		});
		return userList.get(0);
	}

	@Override
	public int countAll() {
		return jdbcTemplate.queryForObject("select count(1) from user", Integer.class);
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update("DELETE  FROM  USER ");

	}
}
