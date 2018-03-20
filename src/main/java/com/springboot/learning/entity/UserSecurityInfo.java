package com.springboot.learning.entity;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurityInfo implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username; // 字段名必须是这个，才能和对应的get方法对应上
	private String password; // 字段名必须是这个，才能和对应的get方法对应上

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 统一赋值为READER权限
		return Arrays.asList(new SimpleGrantedAuthority("READER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// 不过期
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 不加锁
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 不禁用
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
