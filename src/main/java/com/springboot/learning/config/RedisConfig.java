package com.springboot.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.springboot.learning.cache.RedisObjectSerializer;
import com.springboot.learning.entity.RedisTestEntity;

/**
 * @author caiqibin
 * @date 2017年7月16日
 * @introduce:reids配置中添加针对RedisTestEntity的RedisTemplate实例，如有别的对象要存放类似
 */
@Configuration
public class RedisConfig {

	@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	/**
	 * @introduce:注入RedisTestEntity的RedisTemplate实例
	 * @param factory
	 * @return RedisTemplate<String,RedisTestEntity>
	 */
	@Bean
	public RedisTemplate<String, RedisTestEntity> redisTestEntityTemplate(RedisConnectionFactory factory) {
		return getObjectTemplate();
	}

	private RedisTemplate<String, RedisTestEntity> getObjectTemplate() {
		RedisTemplate<String, RedisTestEntity> redisTemplate = new RedisTemplate<String, RedisTestEntity>();
		redisTemplate.setConnectionFactory(getJedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new RedisObjectSerializer());
		return redisTemplate;
	}
}
