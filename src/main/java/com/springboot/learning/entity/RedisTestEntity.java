package com.springboot.learning.entity;

import java.io.Serializable;

/**
 * @author caiqibin
 * @date 2017年7月16日
 * @introduce:要保证实现了可序列化接口才能存储到redis中
 */
public class RedisTestEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public RedisTestEntity() {

	}

	public RedisTestEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
