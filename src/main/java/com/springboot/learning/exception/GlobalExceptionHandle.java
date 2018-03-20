package com.springboot.learning.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.learning.entity.ErrorInfo;

/**
 * @author caiqibin
 * @date 2017年7月10日
 * @introduce:通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
 * 
 */
@ControllerAdvice
public class GlobalExceptionHandle {
	public static final String DEFAULT_ERROR_VIEW = "error";

	// @ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
	@ExceptionHandler
	public ModelAndView defaultErrorHandle(HttpServletRequest request, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("requestUrl", request.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	/**
	 * @introduce:为MyException异常创建对应的处理
	 * @param request
	 * @param e
	 * @throws Exception
	 * @return ErrorInfo<String>
	 */
	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	public ErrorInfo<String> myErrorHandle(HttpServletRequest request, MyException e) throws Exception {
		ErrorInfo<String> errorInfo = new ErrorInfo<String>();
		errorInfo.setCode(ErrorInfo.ERROR);
		errorInfo.setData("error useless data");
		errorInfo.setMessage(e.getMessage());
		errorInfo.setUrl(request.getRequestURL().toString());
		return errorInfo;
	}

}
