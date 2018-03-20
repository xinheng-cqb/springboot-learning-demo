package com.springboot.learning.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author caiqibin
 * @date 2017年7月17日
 * @introduce:MyBatis信息配置（可配置多数据源）
 */
@Configuration
public class MyBatisConfig {

	@Autowired
	@Qualifier("primaryDataSource")
	private DataSource dataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		// 显式指定数据源
		bean.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// 指定xml文件位置
		bean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
		return bean.getObject();
	}
}
