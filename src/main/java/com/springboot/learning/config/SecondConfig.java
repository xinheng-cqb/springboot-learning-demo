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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author caiqibin
 * @date 2017年7月13日
 * @introduce:参考PrimaryConfig配置说明
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactorySecond", transactionManagerRef = "transactionManagerSecond", basePackages = { "com.springboot.learning.repository.second" })
public class SecondConfig {
	@Autowired
	@Qualifier("secondDataSource")
	private DataSource secondDataSource;

	@Bean(name = "entityManagerSecond")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactorySecondary(builder).getObject().createEntityManager();
	}

	@Bean(name = "entityManagerFactorySecond")
	public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(secondDataSource).properties(getVendorProperties(secondDataSource))
				.packages("com.springboot.learning.entity.second") // 设置实体类所在位置
				.persistenceUnit("secondaryPersistenceUnit").build();
	}

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	@Bean(name = "transactionManagerSecond")
	PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
	}
}
