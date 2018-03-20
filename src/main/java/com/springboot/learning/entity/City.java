package com.springboot.learning.entity;

public class City {
	private Long id;
	private String name;
	private Integer score;

	public City() {

	}

	public City(Long id, String name, Integer score) {
		this.id = id;
		this.name = name;
		this.score = score;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return this.id + "_" + this.name + "_" + this.score;
	}

}
