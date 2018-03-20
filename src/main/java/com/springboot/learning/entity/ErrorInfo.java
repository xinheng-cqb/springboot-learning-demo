package com.springboot.learning.entity;

/**
 * @author caiqibin
 * @date 2017年7月10日
 * @introduce:统一的JSON返回对象
 */
public class ErrorInfo<T> {
	public static final Integer OK = 200;
	public static final Integer ERROR = 400;

	private Integer code; // 消息类型
	private String message; // 消息内容
	private String url; // 请求的链接
	private T data; // 请求的数据

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
