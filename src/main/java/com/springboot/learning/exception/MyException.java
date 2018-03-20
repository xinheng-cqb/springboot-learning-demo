package com.springboot.learning.exception;

/**
 * @author caiqibin
 * @date 2017年7月10日
 * @introduce:自定义异常
 */
public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException(String message) {
		super(message);
	}

}
