package com.springboot.learning.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author caiqibin
 * @date 2017年7月13日
 * @introduce:第一数据源的JPA配置，用@Primary区分主数据源。类上的basePackges指定数据源对应Repository定义位置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary", transactionManagerRef = "transactionManagerPrimary", basePackages = { "com.springboot.learning.repository.primary" })
public class PrimaryConfig {
	@Autowired
	@Qualifier("primaryDataSource")
	private DataSource primaryDataSource;

	@Primary
	@Bean(name = "entityManagerPrimary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
	}

	@Primary
	@Bean(name = "entityManagerFactoryPrimary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
		// packages 指定数据源对应entity定义位置
		return builder.dataSource(primaryDataSource).properties(getVendorProperties(primaryDataSource))
				.packages("com.springboot.learning.entity.primary").persistenceUnit("primaryPersistenceUnit").build();
	}

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	@Primary
	@Bean(name = "transactionManagerPrimary")
	public PlatformTransactionManager getVendorProperties(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
	}
}
