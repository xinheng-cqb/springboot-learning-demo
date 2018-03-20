package com.springboot.learning.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author caiqibin
 * @date 2017年7月26日
 * @introduce:实现web请求日志打印的切面类
 */
@Aspect
@Order(3)
@Component
public class WebLogAspect {
	private Log logger = LogFactory.getLog(getClass());

	// 用来存放每个请求的时间
	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	// 通过@Pointcut定义的切入点为com.springboot.learning.web包下的所有函数
	@Pointcut("execution(public * com.springboot.learning.web..*.*(..))")
	public void webLog() {

	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Exception {
		// 将请求开始时间存入
		startTime.set(System.currentTimeMillis());
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
		logger.info("ALL_COST : " + (System.currentTimeMillis() - startTime.get()));
	}

}
